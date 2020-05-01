package com.soft1851.music.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.domain.dto.LoginDto;
import com.soft1851.music.admin.domain.dto.UpdateDto;
import com.soft1851.music.admin.domain.entity.SysAdmin;
import com.soft1851.music.admin.domain.entity.SysRole;
import com.soft1851.music.admin.exception.CustomException;
import com.soft1851.music.admin.mapper.SysAdminMapper;
import com.soft1851.music.admin.service.RedisService;
import com.soft1851.music.admin.service.SysAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.music.admin.util.AliOssUtil;
import com.soft1851.music.admin.util.JwtTokenUtil;
import com.soft1851.music.admin.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yhChen
 * @since 2020-04-21
 */
@Service
@Slf4j
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {

    @Resource
    private SysAdminMapper sysAdminMapper;
    @Resource
    private RedisService redisService;

    @Override
    public Map<String, Object> login(LoginDto loginDto) {
        //根据查到基础信息，主要是要用密码来判定
        SysAdmin admin = sysAdminMapper.getSysAdminByName(loginDto.getName());
        if (admin != null) {
            //客户端密码加密后和数据库的比对
            String pass = Md5Util.getMd5(loginDto.getPassword(), true, 32);
            if (admin.getPassword().equals(pass)) {
                //登录成功，取得admin的完整信息（包含所有角色）
                SysAdmin admin1 = sysAdminMapper.selectByName(loginDto.getName());
                //roles是个list，可能会是多个
                List<SysRole> roles = admin1.getRoles();
                String roleString = JSONObject.toJSONString(roles);
                log.info("管理员角色列表：" + roleString);
                //通过该管理员的id、roles、私钥、指定过期时间生成token
                String token = JwtTokenUtil.getToken(admin.getId(), JSONObject.toJSONString(roles), admin.getSalt(), new Date(System.currentTimeMillis() + 6000L * 1000L));
                //将私钥存入redis，在后面JWT拦截器中可以取出来对客户端请求头中的token解密
                redisService.set(admin1.getId(), admin1.getSalt(), 100L);
                Map<String, Object> map = new TreeMap<>();
                map.put("admin", admin1);
                map.put("token", token);
                return map;
            } else {
                log.error("密码错误");
                throw new CustomException("密码错误", ResultCode.USER_PASSWORD_ERROR);
            }
        } else {
            log.error("用户名不存在");
            throw new CustomException("用户名不存在", ResultCode.USER_NOT_FOUND);
        }
    }

    @Override
    public SysAdmin getAdminAndRolesByName(String name) {
        return sysAdminMapper.selectByName(name);
    }

    @Override
    public String getToken(String adminId, String roles, String secrect, Date expiresAt) {
        return JwtTokenUtil.getToken(adminId, roles, secrect, expiresAt);
    }

    @Override
    public ResponseResult updateSysAdmin(File file, UpdateDto updateDto) {
        SysAdmin sysAdmin1 = sysAdminMapper.getSysAdminById(updateDto.getId());

        String pass = Md5Util.getMd5(updateDto.getPassword(),true,32);

        String url = AliOssUtil.ossUpload(file);

        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        sysAdmin1.setName(updateDto.getName());
        sysAdmin1.setPassword(pass);
        sysAdmin1.setAvatar(url);
        sysAdmin1.setUpdateTime(localDateTime);
        sysAdminMapper.updateById(sysAdmin1);
        return ResponseResult.success(sysAdmin1);
    }


}

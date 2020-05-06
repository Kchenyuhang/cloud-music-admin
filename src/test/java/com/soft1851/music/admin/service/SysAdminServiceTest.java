package com.soft1851.music.admin.service;

import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.dto.LoginDto;
import com.soft1851.music.admin.domain.dto.UpdateDto;
import com.soft1851.music.admin.domain.entity.SysAdmin;
import com.soft1851.music.admin.mapper.SysAdminMapper;
import com.soft1851.music.admin.util.AliOssUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/4/21
 */
@SpringBootTest
class SysAdminServiceTest {
    @Resource
    private SysAdminService sysAdminService;

    @Test
    void login() {
        LoginDto loginDto = LoginDto.builder().name("yhChen").password("123456").build();
        System.out.println(sysAdminService.login(loginDto));
    }

    @Test
    void selectByName() {
        System.out.println(sysAdminService.getAdminAndRolesByName("yhChen"));
    }
}
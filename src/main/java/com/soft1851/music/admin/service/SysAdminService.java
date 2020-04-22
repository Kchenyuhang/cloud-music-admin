package com.soft1851.music.admin.service;

import com.soft1851.music.admin.dto.LoginDto;
import com.soft1851.music.admin.entity.SysAdmin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yhChen
 * @since 2020-04-21
 */
public interface SysAdminService extends IService<SysAdmin> {

    /**
     * 登录
     *
     * @param loginDto
     * @return
     */
    boolean login(LoginDto loginDto);

    /**
     * 根据name查询Admin
     * @param name
     * @return
     */
    SysAdmin getAdmin(String name);
}

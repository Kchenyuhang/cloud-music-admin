package com.soft1851.music.admin.service;

import com.soft1851.music.admin.domain.dto.LoginDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

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
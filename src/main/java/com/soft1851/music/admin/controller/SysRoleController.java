package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.entity.SysRole;
import com.soft1851.music.admin.service.SysRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yhChen
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    @GetMapping()
    public Map getRoleById(@Param("roleId") String roleId) {
        return sysRoleService.selectRoleById(Integer.parseInt(roleId));
    }

    @GetMapping("/list")
    public List<SysRole> selectAll() {
        return sysRoleService.list();
    }

    @PostMapping()
    public ResponseResult addRole(@RequestBody SysRole sysRole) {
        sysRoleService.save(sysRole);
        return ResponseResult.success();
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteRoleById(@PathVariable int id) {
        sysRoleService.removeById(id);
        return ResponseResult.success();
    }
}
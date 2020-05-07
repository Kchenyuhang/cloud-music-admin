package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.entity.SysMenu;
import com.soft1851.music.admin.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yhChen
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/sysMenu")
@Slf4j
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;

    @GetMapping(value = "/list")
    public List<SysMenu> selectAll() {

        return sysMenuService.list();
    }

    @PostMapping()
    public ResponseResult addMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return ResponseResult.success();
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteMenuById(@PathVariable int id) {
        sysMenuService.removeById(id);
        return ResponseResult.success();
    }

}

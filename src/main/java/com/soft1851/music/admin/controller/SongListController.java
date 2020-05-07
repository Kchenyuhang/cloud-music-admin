package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.entity.SongList;
import com.soft1851.music.admin.service.SongListService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequestMapping("/songList")
public class SongListController {
    @Resource
    private SongListService songListService;

    @GetMapping("/list")
    public List<Map<String, Object>> selectAll() {
        return songListService.selectAll();
    }

    @GetMapping("/page")
    public List<SongList> getByPage(@Param("currentPage") int currentPage, @Param("size") int size) {
        return songListService.getByPage(currentPage, size);
    }

    @GetMapping("/blur")
    public List<SongList> blurSelectSongList(@Param("field") String field) {
        return songListService.blurSelect(field);
    }

    @GetMapping("/type")
    public List<Map<String, Object>> getByType() {
        return songListService.getByType();
    }

    @DeleteMapping("/delete")
    public ResponseResult deleteById(@Param("songListId") String songListId) {
        songListService.removeById(songListId);
        return ResponseResult.success();
    }

    @GetMapping(value = "/all")
    public List<SongList> getSongList() {
        return songListService.getTopSongList();
    }

    @GetMapping(value = "/export")
    public void export() {
        songListService.exportData();
    }
}

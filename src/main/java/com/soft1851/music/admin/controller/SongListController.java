package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.dto.PageDto;
import com.soft1851.music.admin.entity.SongList;
import com.soft1851.music.admin.mapper.SongListMapper;
import com.soft1851.music.admin.service.SongListService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
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

    @GetMapping("/type")
    public List<Map<String, Object>> getByType(){
        return songListService.getByType();
    }

    @GetMapping("/blur")
    public List<SongList> blurSelectSongList(@Param("field") String field) {
        return songListService.blurSelect(field);
    }

    @GetMapping("/list")
    public List<SongList> selectAll() {
        return songListService.selectAll();
    }

    @PostMapping("/page")
    public List<SongList> getSongListByPage(@RequestBody PageDto pageDto){
        return songListService.getByPage(pageDto);
    }

    @DeleteMapping("/delete")
    ResponseResult deleteSongListById(@Param("id") String id){
        return songListService.deleteSongList(id);
    }

    @PutMapping("/update")
    ResponseResult updateSongList(@RequestBody SongList songList){
        return songListService.updateSongList(songList);
    }

    @DeleteMapping("/batchDelete")
    ResponseResult batchDeleteById(@Param("ids") String ids){
        return songListService.batchDeleteById(ids);
    }
}

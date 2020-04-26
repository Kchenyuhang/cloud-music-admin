package com.soft1851.music.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft1851.music.admin.CloudMusicAdminApplication;
import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.dto.PageDto;
import com.soft1851.music.admin.entity.SongList;
import com.soft1851.music.admin.mapper.SongListMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/4/24
 */
@SpringBootTest(classes = CloudMusicAdminApplication.class)
class SongListServiceTest {
    @Resource
    private SongListService songListService;
    @Resource
    private SongListMapper songListMapper;

    @Test
    void getByType() {
        List<Map<String,Object>> songs = songListService.getByType();
        songs.forEach(System.out::println);
    }

    @Test
    void blurSelect() {
        String field = "CLANNAD";
        String field1 = "0";
        List<SongList> songs = songListService.blurSelect(field);
        List<SongList> songs1 = songListService.blurSelect(field1);
        System.out.println("*******根据song_list_name查询*******");
        System.out.println(songs);
        System.out.println("*******根据type查询*******");
        songs1.forEach(System.out::println);
    }

    @Test
    void selectAll() {
        List<SongList> songs = songListMapper.selectAll();
        songs.forEach(System.out::println);
    }

    /**
     * 需要加分页的插件
     */
    @Test
    void getByPage() {
        Page<SongList> page = new Page<>(1, 5, 10);
        QueryWrapper<SongList> wrapper = new QueryWrapper<>(null);
        IPage<SongList> page1 = songListService.page(page, wrapper);
        page1.getRecords().forEach(System.out::println);
    }

    @Test
    void searchSongList() {
        PageDto pageDto = PageDto.builder()
                .currentPage(1)
                .pageSize(3)
                .field("0")
                .build();
        ResponseResult result = songListService.searchSongList(pageDto);
        System.out.println(result);

    }

    /**
     * 批量删除
     */
    @Test
    void batchDeleteById() {
        String id = "100305590,101928470";
        songListService.batchDeleteById(id);
    }
}
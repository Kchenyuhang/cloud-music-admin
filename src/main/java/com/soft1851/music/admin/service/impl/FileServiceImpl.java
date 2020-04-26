package com.soft1851.music.admin.service.impl;

import com.soft1851.music.admin.service.FileService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/4/24
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public Map<String, String> exportSongList() {
        Map<String, String> map = new LinkedHashMap<>();
        String[] names = {"歌手id","歌单名称","封面","播放数量","类型","歌曲数量","创建时间"};
        String[] field = {"song_list_id","song_list_name","thumbnail","play_counts","type","song_count","create_time"};
        int len = names.length;
        for (int i = 0; i < len; i++) {
            map.put(field[i],names[i]);
        }
        return map;
    }
}

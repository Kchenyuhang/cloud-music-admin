package com.soft1851.music.admin.service;

import java.util.Map;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/4/24
 */
public interface FileService {
    /**
     * 歌单数据导出Excel
     * @return
     */
    Map<String,String> exportSongList();
}

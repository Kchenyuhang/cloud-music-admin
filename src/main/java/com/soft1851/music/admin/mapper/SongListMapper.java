package com.soft1851.music.admin.mapper;

import com.soft1851.music.admin.dto.PageDto;
import com.soft1851.music.admin.entity.SongList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yhChen
 * @since 2020-04-21
 */
public interface SongListMapper extends BaseMapper<SongList> {
    /**
     * 查询所有歌单
     * @return
     */
    List<Map<String, Object>> selectAll();
}

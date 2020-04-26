package com.soft1851.music.admin.mapper;

import com.soft1851.music.admin.dto.TimeDto;
import com.soft1851.music.admin.entity.Song;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yhChen
 * @since 2020-04-21
 */
public interface SongMapper extends BaseMapper<Song> {
    /**
     * 根据时间段查询数据
     * @return
     */
    List<Song> getSongByTimeParagraph(@Param("timeDto") TimeDto timeDto);
}

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
     * 根据类型查询歌曲
     *
     * @return
     */
    List<Map<String, Object>> getByType();

    /**
     * 根据（name，type）属性进行模糊查询
     *
     * @param field
     * @return
     */
    List<SongList> blurSelect(String field);

    /**
     * 查询所有
     * @return
     */
    @Select("SELECT * FROM song_list")
    List<SongList> selectAll();

    /***
     * 模糊查询+分页查询
     * @param pageDto
     * @return
     * @throws SQLException
     */
    @Select("SELECT * FROM song_list " +
            "WHERE song_list_name LIKE CONCAT('%',#{pageDto.field},'%') OR type LIKE CONCAT('%',#{pageDto.field},'%') " +
            "LIMIT ${pageDto.pageSize*(pageDto.currentPage-1)},#{pageDto.pageSize}")
    List<SongList> searchSongList(@Param("pageDto") PageDto pageDto) throws SQLException;
}

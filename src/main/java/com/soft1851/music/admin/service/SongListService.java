package com.soft1851.music.admin.service;

import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.dto.PageDto;
import com.soft1851.music.admin.entity.SongList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yhChen
 * @since 2020-04-21
 */
public interface SongListService extends IService<SongList> {
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
    List<SongList> selectAll();

    /**
     * 分页查询所有歌单
     * @param pageDto
     * @return
     */
    ResponseResult searchSongList(PageDto pageDto);

    /**
     * 分页查询.
     * @param pageDto
     * @return
     */
    List<SongList> getByPage(PageDto pageDto);

    /**
     * 根据id删除歌单
     * @param id
     * @return
     */
    ResponseResult deleteSongList(String id);

    /**
     *修改歌单数据
     * @param songList
     * @return
     */
    ResponseResult updateSongList(SongList songList);

    /**
     * 批量删除
     * @param idLists
     * @return
     */
    ResponseResult batchDeleteById(String idLists);
}

package com.soft1851.music.admin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/5/6
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysAdminDto {
    private String id;
    private String name;
    private String password;
    private String avatar;
}

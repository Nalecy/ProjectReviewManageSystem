package com.nalecy.www.project.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Nalecy
 */
@Accessors(chain = true)
@Data
public class LoginVo {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 性别 0-男 1-女
     */
    private Integer sex;

    /**
     * 角色
     */
    private String role;
}

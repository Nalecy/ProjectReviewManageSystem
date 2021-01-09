package com.nalecy.www.project.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 用户查询vo
 * @author Nalecy
 */
@Data
@Accessors(chain = true)
public class UserQueryVo {

    /**
     * 当前页面
     */
    private int current = 1;

    /**
     * 页面大小
     */
    private int size = 10;

    /**
     * 姓名 模糊查询
     */
    private String name;

    /**
     * 性别 0-男 1-女
     */
    private Integer sex;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 手机号 模糊查询
     */
    private String phone;

    /**
     * 邮箱 模糊查询
     */
    private String email;

    /**
     * 用户列表（ps.不是界面传的）
     */
    private List<Integer> userIds;
}

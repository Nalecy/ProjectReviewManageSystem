package com.nalecy.www.project.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ApplyQueryVo {

    private int current = 1;

    private int size = 10;

    /**
     * 项目名 模糊搜索
     */
    private String projectName;

    /**
     * 评委名 模糊搜索
     */
    private String userName;

    /**
     * 项目ids（ps.界面不用传）
     */
    private List<Integer> projectIds;

    private List<Integer> userIds;
}

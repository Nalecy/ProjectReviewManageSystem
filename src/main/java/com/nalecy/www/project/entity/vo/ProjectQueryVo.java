package com.nalecy.www.project.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProjectQueryVo {

    private int current = 1;

    private int size = 10;

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 项目负责人名字
     */
    private String name;

    /**
     * 指导老师
     */
    private String teacher;

    /**
     * 状态 0-待审核 1-审核通过 2-审核不通过
     */
    private Integer status;

    /**
     * 项目类型 0-人工智能 1-智能硬件与智能制造 2-互联网 3-物联网 4-文化创意 5-其它
     */
    private Integer type;

    /**
     * 用户id（ps.界面不用传）
     */
    private List<Integer> userIds;
}

package com.nalecy.www.project.entity.vo;

import com.nalecy.www.project.entity.po.Project;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ProjectVo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 成员
     */
    private String member;

    /**
     * 项目简介
     */
    private String introduction;

    /**
     * 项目的知识产权
     */
    private String intellectual;

    /**
     * 项目类型 0-人工智能 1-智能硬件与智能制造 2-互联网 3-物联网 4-文化创意 5-其它
     */
    private Integer type;

    /**
     * 用户id
     */
    private Integer userId;

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
     * 创建时间
     */
    private LocalDateTime createTime;

    public static ProjectVo convert(Project project){
        ProjectVo projectVo = new ProjectVo();
        BeanUtils.copyProperties(project, projectVo);
        return projectVo;
    }
}

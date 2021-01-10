package com.nalecy.www.project.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode
@Accessors(chain = true)
@ToString
@TableName(value = "project")
public class Project {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目名
     */
    @TableField(value = "project_name")
    private String projectName;

    /**
     * 成员
     */
    @TableField(value = "member")
    private String member;

    /**
     * 项目简介
     */
    @TableField(value = "introduction")
    private String introduction;

    /**
     * 项目的知识产权
     */
    @TableField(value = "intellectual")
    private String intellectual;

    /**
     * 项目类型 0-人工智能 1-智能硬件与智能制造 2-互联网 3-物联网 4-文化创意 5-其它
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 指导老师
     */
    @TableField(value = "teacher")
    private String teacher;

    /**
     * 状态 0-待审核 1-审核通过 2-审核不通过
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
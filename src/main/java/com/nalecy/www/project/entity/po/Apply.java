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
@TableName(value = "apply")
public class Apply {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目id
     */
    @TableField(value = "project_id")
    private Integer projectId;

    /**
     * 申请理由
     */
    @TableField(value = "reason")
    private String reason;

    /**
     * 状态 0-待评审 1-通过 2-不通过
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 评委id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;
}
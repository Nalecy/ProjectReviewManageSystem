package com.nalecy.www.project.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@TableName(value = "review")
public class Review {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 评审内容
     */
    @TableField(value = "review_content")
    private String reviewContent;

    /**
     * 评审时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;

    /**
     * 申请id
     */
    @TableField(value = "apply_id")
    private Integer applyId;
}
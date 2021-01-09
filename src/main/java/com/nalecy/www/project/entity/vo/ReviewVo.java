package com.nalecy.www.project.entity.vo;


import com.baomidou.mybatisplus.extension.api.R;
import com.nalecy.www.project.entity.po.Review;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ReviewVo {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 评审内容
     */
    private String reviewContent;

    /**
     * 评审时间
     */
    private LocalDateTime createTime;

    /**
     * 申请id
     */
    private Integer applyId;

    /**
     * 评审人
     */
    private String userName;

    public static ReviewVo convert(Review review){
        ReviewVo reviewVo = new ReviewVo();
        BeanUtils.copyProperties(review, reviewVo);
        return reviewVo;
    }
}

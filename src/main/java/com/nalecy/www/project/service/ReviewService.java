package com.nalecy.www.project.service;

import com.nalecy.www.project.entity.po.Review;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nalecy.www.project.entity.vo.ReviewVo;

public interface ReviewService extends IService<Review> {

    /**
     * 查看某项目的评审情况
     *
     * @param projectId 项目id
     * @return ReviewVo
     */
    ReviewVo getProjectReviewDetail(Integer projectId);

    /**
     * 评审项目
     *
     * @param review 评审内容
     * @param status 是否通过 1-通过 2-不通过
     * @return boolean
     */
    boolean reviewProject(Review review, Integer status);
}

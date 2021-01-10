package com.nalecy.www.project.service.impl;

import com.nalecy.www.project.constant.ApplyStatusConstant;
import com.nalecy.www.project.dao.ApplyMapper;
import com.nalecy.www.project.dao.ProjectMapper;
import com.nalecy.www.project.dao.UserMapper;
import com.nalecy.www.project.entity.po.Apply;
import com.nalecy.www.project.entity.po.Project;
import com.nalecy.www.project.entity.vo.ReviewVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nalecy.www.project.dao.ReviewMapper;
import com.nalecy.www.project.entity.po.Review;
import com.nalecy.www.project.service.ReviewService;
import org.springframework.util.Assert;

@Service
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {

    @Resource
    private ReviewMapper reviewMapper;

    @Resource
    private ApplyMapper applyMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public ReviewVo getProjectReviewDetail(Integer projectId) {
        Assert.notNull(projectId, "项目id不能为空");

        Apply apply = applyMapper.selectFinishApply(projectId);
        if (Objects.isNull(apply)) {
            return null;
        }

        Review review = reviewMapper.selectByApplyId(apply.getId());
        ReviewVo reviewVo = ReviewVo.convert(review);
        reviewVo.setUserName(userMapper.selectById(apply.getUserId()).getName());

        return reviewVo;
    }

    @Override
    public boolean reviewProject(Review review, Integer status) {
        Assert.notNull(review, "评审内容不能为空");
        Assert.notNull(status, "评审操作不能为空");
//        Assert.notNull(review.getReviewContent(), "评审内容不能为空");
        Assert.notNull(review.getApplyId(), "申请表id不能为空");

        Assert.state(reviewMapper.insert(review) == 1, "系统错误");

        Assert.state(applyMapper.updateById(new Apply().setId(review.getApplyId()).setStatus(ApplyStatusConstant.FINISH)) == 1,
                "系统错误");

        Integer id = projectMapper.selectById(applyMapper.selectById(review.getApplyId()).getProjectId()).getId();

        Assert.state(projectMapper.updateById(new Project().setId(id).setStatus(status)) == 1,
                "系统错误");
        return true;
    }
}

package com.nalecy.www.project.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nalecy.www.project.entity.po.Review;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper extends BaseMapper<Review> {

    /**
     * 申请id搜评审内容
     *
     * @param applyId 申请id
     * @return Review
     */
    default Review selectByApplyId(Integer applyId) {
        QueryWrapper<Review> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Review::getApplyId, applyId);
        return selectOne(queryWrapper);
    }
}
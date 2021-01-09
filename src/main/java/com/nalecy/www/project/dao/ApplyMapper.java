package com.nalecy.www.project.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nalecy.www.project.entity.po.Apply;
import com.nalecy.www.project.entity.vo.ApplyQueryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Objects;

@Mapper
public interface ApplyMapper extends BaseMapper<Apply> {

    /**
     * 分页搜索
     *
     * @param applyQueryVo 条件
     * @return Page<Apply>
     */
    default Page<Apply> selectApply(ApplyQueryVo applyQueryVo) {
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .in(CollectionUtils.isNotEmpty(applyQueryVo.getUserIds()), Apply::getUserId, applyQueryVo.getUserIds())
                .in(CollectionUtils.isNotEmpty(applyQueryVo.getProjectIds()), Apply::getProjectId, applyQueryVo.getProjectIds())
                .orderByDesc(Apply::getCreateTime);
        Page<Apply> page = new Page<>(applyQueryVo.getCurrent(), applyQueryVo.getSize());
        return selectPage(page, queryWrapper);
    }
}
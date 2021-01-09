package com.nalecy.www.project.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nalecy.www.project.entity.po.Project;
import com.nalecy.www.project.entity.vo.ProjectQueryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

    /**
     * 搜索项目
     *
     * @param projectQueryVo 条件
     * @return Page<Project>
     */
    default Page<Project> selectProject(ProjectQueryVo projectQueryVo) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(Project::getId, Project::getCreateTime, Project::getIntellectual, Project::getIntroduction,
                Project::getMember, Project::getProjectName, Project::getStatus, Project::getTeacher, Project::getType,
                Project::getUserId)
                .like(Objects.nonNull(projectQueryVo.getProjectName()), Project::getProjectName, projectQueryVo.getProjectName())
                .like(Objects.nonNull(projectQueryVo.getTeacher()), Project::getTeacher, projectQueryVo.getTeacher())
                .eq(Objects.nonNull(projectQueryVo.getStatus()), Project::getStatus, projectQueryVo.getStatus())
                .eq(Objects.nonNull(projectQueryVo.getType()), Project::getType, projectQueryVo.getType())
                .in(CollectionUtils.isNotEmpty(projectQueryVo.getUserIds()), Project::getUserId, projectQueryVo.getUserIds())
                .orderByDesc(Project::getCreateTime);
        Page<Project> page = new Page<>(projectQueryVo.getCurrent(), projectQueryVo.getSize());
        return selectPage(page, queryWrapper);
    }

    /**
     * 项目名模糊搜索项目id
     *
     * @param name 项目名
     * @return List<Integer>
     */
    default List<Integer> selectByName(String name) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(Project::getId)
                .like(Project::getProjectName, name);
        return selectList(queryWrapper)
                .stream()
                .map(Project::getId)
                .collect(Collectors.toList());
    }

}
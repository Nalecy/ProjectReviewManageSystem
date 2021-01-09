package com.nalecy.www.project.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nalecy.www.project.entity.po.Project;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {
}
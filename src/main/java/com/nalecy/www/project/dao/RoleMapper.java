package com.nalecy.www.project.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nalecy.www.project.entity.po.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
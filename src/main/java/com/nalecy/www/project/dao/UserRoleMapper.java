package com.nalecy.www.project.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nalecy.www.project.entity.po.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 搜索用户对应角色id
     *
     * @param userId 用户id
     * @return UserRole
     */
    default UserRole selectByUserId(Integer userId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(UserRole::getId, UserRole::getRoleId)
                .eq(UserRole::getUserId, userId);
        return selectOne(queryWrapper);
    }

    /**
     * 角色id搜用户id
     *
     * @param roleId 角色id
     * @return List<Integer>
     */
    default List<Integer> selectUserIdByRoleId(Integer roleId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(UserRole::getUserId)
                .eq(UserRole::getRoleId, roleId);
        return selectList(queryWrapper)
                .stream()
                .map(UserRole::getUserId)
                .collect(Collectors.toList());
    }
}
package com.nalecy.www.project.service.impl;

import com.nalecy.www.project.dao.RoleMapper;
import com.nalecy.www.project.dao.UserMapper;
import com.nalecy.www.project.entity.po.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nalecy.www.project.dao.UserRoleMapper;
import com.nalecy.www.project.entity.po.UserRole;
import com.nalecy.www.project.service.UserRoleService;
import org.springframework.util.Assert;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public boolean updateRole(Integer userId, Integer roleId) {
        Assert.notNull(userId, "用户id不能为空");
        Assert.notNull(roleId, "角色id不能为空");
        Assert.notNull(userMapper.selectById(userId), "该用户不存在");
        Assert.notNull(roleMapper.selectById(roleId), "该角色不存在");

        UserRole userRole = userRoleMapper.selectByUserId(userId);
        userRole.setRoleId(roleId);

        Assert.state(userRoleMapper.updateById(userRole) == 1, "系统错误");

        return true;
    }
}

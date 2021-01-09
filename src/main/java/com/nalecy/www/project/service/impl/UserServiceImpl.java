package com.nalecy.www.project.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nalecy.www.project.constant.RoleConstant;
import com.nalecy.www.project.dao.RoleMapper;
import com.nalecy.www.project.dao.UserRoleMapper;
import com.nalecy.www.project.entity.po.Role;
import com.nalecy.www.project.entity.po.UserRole;
import com.nalecy.www.project.entity.vo.LoginVo;
import com.nalecy.www.project.entity.vo.UserQueryVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nalecy.www.project.entity.po.User;
import com.nalecy.www.project.dao.UserMapper;
import com.nalecy.www.project.service.UserService;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public LoginVo login(String account, String password) {
        Assert.notNull(account, "账号不能为空");
        Assert.notNull(password, "密码不能为空");

        // 搜用户
        User user = userMapper.selectUserByAccountAndPassword(account, password);
        Assert.notNull(user, "账号或密码错误");

        // 搜对应角色id
        UserRole userRole = userRoleMapper.selectByUserId(user.getId());
        // 搜角色
        Role role = roleMapper.selectById(userRole.getRoleId());

        return new LoginVo().setUserId(user.getId())
                .setName(user.getName())
                .setSex(user.getSex())
                .setRole(role.getRole());
    }

    @Override
    public boolean signUp(User user) {
        Assert.notNull(user, "用户信息不能为空");
        Assert.notNull(user.getName(), "姓名不能为空");
        Assert.notNull(user.getPassword(), "密码不能为空");

        // 姓名判重
        Assert.isTrue(userMapper.nameIsExist(user.getName()), "该姓名已存在");
        // 如果填了手机号，手机号判重
        if (Objects.nonNull(user.getPhone())) {
            Assert.isTrue(userMapper.phoneIsExist(user.getPhone()), "该手机号已存在");
        }

        // 插入用户
        Assert.state(userMapper.insert(user) == 1, "系统错误");
        // 插入普通用户角色
        UserRole userRole = new UserRole().setUserId(user.getId()).setRoleId(RoleConstant.USER);
        Assert.state(userRoleMapper.insert(userRole) == 1, "系统错误");

        return true;
    }

    @Override
    public User getUserInfo(Integer userId) {
        Assert.notNull(userId, "用户id不能为空");
        User user = userMapper.selectById(userId);
        Assert.notNull(user, "该用户不存在");
        // 密码置空，不返回
        user.setPassword(null);
        return user;
    }

    @Override
    public Page<User> getUserList(UserQueryVo userQueryVo) {
        // 涉及角色筛选，去user_role表搜用户id
        if (Objects.nonNull(userQueryVo.getRoleId())) {
            List<Integer> userIds = userRoleMapper.selectUserIdByRoleId(userQueryVo.getRoleId());
            // 如果用户id为空，说明该角色下没有用户，返回空列表
            if (CollectionUtils.isEmpty(userIds)) {
                return new Page<User>()
                        .setCurrent(userQueryVo.getCurrent())
                        .setSize(userQueryVo.getSize())
                        .setRecords(Collections.emptyList());
            }else {
                userQueryVo.setUserIds(userIds);
            }
        }

        // 剩下的搜
        return userMapper.selectUserPage(userQueryVo);
    }
}

package com.nalecy.www.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nalecy.www.project.entity.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nalecy.www.project.entity.vo.LoginVo;
import com.nalecy.www.project.entity.vo.UserQueryVo;

import java.util.List;

public interface UserService extends IService<User> {

    /**
     * 登录
     *
     * @param account  账号(姓名或手机号)
     * @param password 密码
     * @return LoginVo
     */
    LoginVo login(String account, String password);

    /**
     * 注册
     *
     * @param user 用户信息
     * @return boolean
     */
    boolean signUp(User user);

    /**
     * 获取用户个人信息
     *
     * @param userId 用户id
     * @return User
     */
    User getUserInfo(Integer userId);

    /**
     * 分页查询用户列表
     *
     * @param userQueryVo 查询条件
     * @return Page<User>
     */
    Page<User> getUserList(UserQueryVo userQueryVo);
}

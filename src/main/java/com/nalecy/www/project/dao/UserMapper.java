package com.nalecy.www.project.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nalecy.www.project.entity.po.User;
import com.nalecy.www.project.entity.vo.UserQueryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Objects;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return User
     */
    default User selectUserByAccountAndPassword(String account, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .select(User::getId, User::getName, User::getSex)
                .and(wrapper -> wrapper.eq(User::getName, account).or().eq(User::getPhone, account))
                .eq(User::getPassword, password);
        return selectOne(queryWrapper);
    }

    /**
     * 姓名搜索是否存在
     *
     * @param name 姓名
     * @return 是否已存在
     */
    default boolean nameIsExist(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .select(User::getId)
                .eq(User::getName, name);
        return selectOne(queryWrapper) != null;
    }

    /**
     * 手机搜索是否存在
     *
     * @param phone 手机
     * @return 是否已存在
     */
    default boolean phoneIsExist(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .select(User::getId)
                .eq(User::getPhone, phone);
        return selectOne(queryWrapper) != null;
    }

    /**
     * 搜索用户列表
     *
     * @param userQueryVo 条件
     * @return Page<User>
     */
    default Page<User> selectUserPage(UserQueryVo userQueryVo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(User::getId, User::getName, User::getSex, User::getPhone, User::getEmail)
                .like(Objects.nonNull(userQueryVo.getEmail()), User::getEmail, userQueryVo.getEmail())
                .like(Objects.nonNull(userQueryVo.getPhone()), User::getPhone, userQueryVo.getPhone())
                .like(Objects.nonNull(userQueryVo.getName()), User::getName, userQueryVo.getName())
                .eq(Objects.nonNull(userQueryVo.getSex()), User::getSex, userQueryVo.getSex())
                .in(CollectionUtils.isNotEmpty(userQueryVo.getUserIds()), User::getId, userQueryVo.getUserIds());
        Page<User> page = new Page<>(userQueryVo.getCurrent(), userQueryVo.getSize());
        return selectPage(page, queryWrapper);
    }
}
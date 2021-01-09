package com.nalecy.www.project.service;

import com.nalecy.www.project.entity.po.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserRoleService extends IService<UserRole> {

    /**
     * 修改用户的角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return boolean
     */
    boolean updateRole(Integer userId, Integer roleId);
}

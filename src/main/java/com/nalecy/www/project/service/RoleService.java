package com.nalecy.www.project.service;

import com.nalecy.www.project.entity.po.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface RoleService extends IService<Role> {

    /**
     * 获取角色列表
     *
     * @return List<Role>
     */
    List<Role> getRoleList();
}

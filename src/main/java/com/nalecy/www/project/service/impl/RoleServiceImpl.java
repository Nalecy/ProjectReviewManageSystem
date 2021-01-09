package com.nalecy.www.project.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nalecy.www.project.dao.RoleMapper;
import com.nalecy.www.project.entity.po.Role;
import com.nalecy.www.project.service.RoleService;
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{

    @Override
    public List<Role> getRoleList() {
        return baseMapper.selectList(null);
    }
}

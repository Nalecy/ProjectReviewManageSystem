package com.nalecy.www.project.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nalecy.www.project.dao.UserRoleMapper;
import com.nalecy.www.project.entity.po.UserRole;
import com.nalecy.www.project.service.UserRoleService;
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService{

}

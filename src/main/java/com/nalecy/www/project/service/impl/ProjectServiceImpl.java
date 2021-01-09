package com.nalecy.www.project.service.impl;

import com.nalecy.www.project.constant.ProjectStatusConstant;
import com.nalecy.www.project.dao.UserMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nalecy.www.project.dao.ProjectMapper;
import com.nalecy.www.project.entity.po.Project;
import com.nalecy.www.project.service.ProjectService;
import org.springframework.util.Assert;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService{

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean insertProject(Project project) {
        Assert.notNull(project, "项目信息不能为空");
        Assert.notNull(project.getProjectName(), "项目名不能为空");
        Assert.notNull(project.getMember(), "项目成员不能为空");
        Assert.notNull(project.getType(), "项目类型不能为空");

        Assert.notNull(userMapper.selectById(project.getUserId()), "该用户不存在");

        // 待审核
        project.setStatus(ProjectStatusConstant.WAIT);

        Assert.state(projectMapper.insert(project) == 1, "系统错误");

        return true;
    }


}

package com.nalecy.www.project.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nalecy.www.project.constant.ProjectStatusConstant;
import com.nalecy.www.project.dao.UserMapper;
import com.nalecy.www.project.entity.vo.ProjectQueryVo;
import com.nalecy.www.project.entity.vo.ProjectVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean deleteProject(Integer projectId) {
        Assert.notNull(projectMapper.selectById(projectId), "该项目不存在");

        Assert.state(projectMapper.deleteById(projectId) == 1, "系统错误");

        return true;
    }

    @Override
    public boolean updateProject(Project project) {
        Assert.notNull(project.getId(), "项目id不能为空");
        Assert.notNull(projectMapper.selectById(project.getId()), "该项目不存在");

        project.setUserId(null);

        Assert.state(projectMapper.updateById(project) == 1, "系统错误");

        return true;
    }

    @Override
    public Page<ProjectVo> selectProjectList(ProjectQueryVo projectQueryVo) {
        // 姓名不为空，模糊搜索
        if(Objects.nonNull(projectQueryVo.getName())){
            List<Integer> userIds = userMapper.selectUserIdByName(projectQueryVo.getName());
            if(CollectionUtils.isEmpty(userIds)){
                return new Page<ProjectVo>(projectQueryVo.getCurrent(), projectQueryVo.getSize())
                        .setRecords(Collections.emptyList());
            }else{
                projectQueryVo.setUserIds(userIds);
            }
        }

        // 搜
        Page<Project> projectPage = projectMapper.selectProject(projectQueryVo);

        // 转
        Page<ProjectVo> result = new Page<>(projectPage.getCurrent(), projectPage.getSize());
        result.setTotal(projectPage.getTotal());

        List<ProjectVo> resultList = new LinkedList<>();
        if(CollectionUtils.isNotEmpty(projectPage.getRecords())){
            projectPage.getRecords().forEach(project -> {
                ProjectVo projectVo = ProjectVo.convert(project);
                // 搜用户名
                projectVo.setName(userMapper.selectById(project.getUserId()).getName());
                resultList.add(projectVo);
            });
        }
        return result.setRecords(resultList);
    }
}

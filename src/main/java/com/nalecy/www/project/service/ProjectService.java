package com.nalecy.www.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nalecy.www.project.entity.po.Project;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nalecy.www.project.entity.vo.ProjectQueryVo;
import com.nalecy.www.project.entity.vo.ProjectVo;

public interface ProjectService extends IService<Project> {

    /**
     * 申请项目
     *
     * @param project 项目
     * @return boolean
     */
    boolean insertProject(Project project);

    /**
     * 删除项目
     *
     * @param projectId 项目id
     * @return boolean
     */
    boolean deleteProject(Integer projectId);

    /**
     * 更新项目
     *
     * @param project 项目
     * @return boolean
     */
    boolean updateProject(Project project);

    /**
     * 分页获取项目列表
     *
     * @param projectQueryVo 条件
     * @return Page<ProjectVo>
     */
    Page<ProjectVo> selectProjectList(ProjectQueryVo projectQueryVo);
}

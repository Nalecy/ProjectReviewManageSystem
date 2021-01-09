package com.nalecy.www.project.service;

import com.nalecy.www.project.entity.po.Project;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ProjectService extends IService<Project> {

    /**
     * 申请项目
     *
     * @param project 项目
     * @return boolean
     */
    boolean insertProject(Project project);


}

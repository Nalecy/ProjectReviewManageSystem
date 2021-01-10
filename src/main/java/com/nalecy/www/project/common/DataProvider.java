package com.nalecy.www.project.common;

import com.nalecy.www.project.entity.po.Project;
import com.nalecy.www.project.entity.po.User;
import com.nalecy.www.project.entity.vo.ProjectVo;

/**
 * @author nalecy
 * @since 2021/1/10
 */
public enum DataProvider {
    INSTANCE;

    private int curUserId = -1;

    public void setCurUserId(int user){
        curUserId = user;
    }

    public int getCurUserId() {
        return curUserId;
    }

    private ProjectVo chooseProject = null;

    public ProjectVo getChooseProject() {
        return chooseProject;
    }

    public void setChooseProject(ProjectVo chooseProject) {
        this.chooseProject = chooseProject;
    }
}

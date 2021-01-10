package com.nalecy.www.project.common;

import com.nalecy.www.project.entity.po.Apply;
import com.nalecy.www.project.entity.po.Project;
import com.nalecy.www.project.entity.po.User;
import com.nalecy.www.project.entity.vo.ApplyVo;
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

    private Project chooseProject = null;

    public Project getChooseProject() {
        return chooseProject;
    }

    public void setChooseProject(Project chooseProject) {
        this.chooseProject = chooseProject;
    }

    private ApplyVo chooseApply = null;

    public ApplyVo getChooseApply() {
        return chooseApply;
    }

    public void setChooseApply(ApplyVo chooseApply) {
        this.chooseApply = chooseApply;
    }
}

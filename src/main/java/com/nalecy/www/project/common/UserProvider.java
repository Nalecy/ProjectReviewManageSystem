package com.nalecy.www.project.common;

import com.nalecy.www.project.entity.po.User;

/**
 * @author nalecy
 * @since 2021/1/10
 */
public enum  UserProvider {
    INSTANCE;

    private int curUserId = -1;

    public void setCurUserId(int user){
        curUserId = user;
    }


    public int getCurUserId() {
        return curUserId;
    }
}

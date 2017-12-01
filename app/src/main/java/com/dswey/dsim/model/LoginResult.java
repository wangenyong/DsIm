package com.dswey.dsim.model;

import java.io.Serializable;

/**
 *
 * @author wangenyong
 * @date 2017/12/1
 */

public class LoginResult implements Serializable {

    private static final long serialVersionUID = 2442739309481246904L;
    private UserModel mUser;
    private boolean mSuccess;
    private String mErrorMsg;

    public LoginResult(UserModel user, boolean success) {

        mUser = user;
        mSuccess = success;
    }

    public LoginResult(boolean success, String errorMsg) {

        mSuccess = success;
        mErrorMsg = errorMsg;
    }

    public boolean isSuccess() {

        return mSuccess;
    }

    public String getErrorMsg() {

        return mErrorMsg;
    }

    public UserModel getUser() {

        return mUser;
    }
}


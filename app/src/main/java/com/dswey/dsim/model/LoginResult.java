package com.dswey.dsim.model;

/**
 *
 * @author wangenyong
 * @date 2017/12/1
 */

public class LoginResult {

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


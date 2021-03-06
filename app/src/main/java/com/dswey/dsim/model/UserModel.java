package com.dswey.dsim.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.dswey.dsim.BR;

/**
 *
 * @author wangenyong
 * @date 2017/12/1
 */

public class UserModel extends BaseObservable {
    private String mName = "";
    private String mPassword = "";
    private String mPasswordAgain = "";

    public UserModel() {}

    public UserModel(String name, String password) {
        mName = name;
        mPassword = password;
    }

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getPasswordAgain() {
        return mPasswordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        mPasswordAgain = passwordAgain;
        notifyPropertyChanged(BR.passwordAgain);
    }
}

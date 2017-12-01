package com.dswey.dsim.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dswey.dsim.R;
import com.dswey.dsim.databinding.ActivityLoginBinding;
import com.dswey.dsim.im.SmackManager;
import com.dswey.dsim.model.LoginResult;
import com.dswey.dsim.model.UserModel;
import com.dswey.dsim.util.ValueUtil;
import com.wangenyong.mvp.base.BaseActivity;
import com.wangenyong.mvp.utils.UiUtil;
import com.wangenyong.mvp.view.SimpleLoadDialog;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author wangenyong
 */
public class LoginActivity extends BaseActivity {
    private ActivityLoginBinding mBinding;
    private UserModel mUserModel;
    private SimpleLoadDialog mLoadDialog;
    public final static int REGISTER_CODE = 100;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        mLoadDialog = new SimpleLoadDialog(this, null, false);

        mUserModel = new UserModel();
        mBinding.setUser(mUserModel);
        mBinding.setPresenter(new Presenter());

    }

    public class Presenter {
        public void onLogin(View view) {
            if (ValueUtil.isEmpty(mUserModel.getName())) {
                UiUtil.makeText(LoginActivity.this, "请输入用户名");
                return;
            }
            if (ValueUtil.isEmpty(mUserModel.getPassword())) {
                UiUtil.makeText(LoginActivity.this, "请输入密码");
                return;
            }

            Observable.just(mUserModel)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(disposable -> mLoadDialog.show("正在登录..."))
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(Schedulers.io())
                    .flatMap(userModel -> {
                        LoginResult loginResult = SmackManager.getInstance().login(mUserModel.getName(), mUserModel.getPassword());
                        return Observable.just(loginResult);
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(o -> mLoadDialog.dismiss())
                    .subscribe(loginResult -> {
                        if (loginResult.isSuccess()) {
                            LoginActivity.this.finish();
                        } else {
                            UiUtil.makeText(LoginActivity.this, loginResult.getErrorMsg());
                        }
                    }, throwable -> {
                        throwable.printStackTrace();
                        UiUtil.makeText(LoginActivity.this, throwable.getMessage());
                    });



        }

        public void onRegister(View view) {
            startActivityForResult(RegisterActivity.newIntent(LoginActivity.this), REGISTER_CODE);
        }
    }
}

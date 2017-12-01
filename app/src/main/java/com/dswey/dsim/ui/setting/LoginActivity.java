package com.dswey.dsim.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dswey.dsim.R;
import com.dswey.dsim.databinding.ActivityLoginBinding;
import com.dswey.dsim.model.UserModel;
import com.wangenyong.mvp.base.BaseActivity;

/**
 * @author wangenyong
 */
public class LoginActivity extends BaseActivity {
    private ActivityLoginBinding mBinding;
    private UserModel mUserModel;
    public final static int REGISTER_CODE = 100;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mUserModel = new UserModel();
        mBinding.setUser(mUserModel);
        mBinding.setPresenter(new Presenter());

    }

    public class Presenter {
        public void onRegister(View view) {
            startActivityForResult(RegisterActivity.newIntent(LoginActivity.this), REGISTER_CODE);
        }
    }
}

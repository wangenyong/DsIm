package com.dswey.dsim.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dswey.dsim.R;
import com.dswey.dsim.databinding.ActivityLoginBinding;

/**
 * @author wangenyong
 */
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding mBinding;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }
}

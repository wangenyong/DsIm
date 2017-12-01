package com.dswey.dsim.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dswey.dsim.R;
import com.dswey.dsim.databinding.ActivityRegisterBinding;
import com.wangenyong.mvp.base.BaseActivity;

/**
 * @author wangenyong
 */
public class RegisterActivity extends BaseActivity {
    private ActivityRegisterBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        return intent;
    }

}

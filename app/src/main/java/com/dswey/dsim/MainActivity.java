package com.dswey.dsim;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.dswey.dsim.databinding.ActivityMainBinding;
import com.dswey.dsim.im.SmackManager;
import com.dswey.dsim.model.Constants;
import com.dswey.dsim.model.LoginResult;
import com.dswey.dsim.ui.contact.ContactFragment;
import com.dswey.dsim.ui.message.MessageFragment;
import com.dswey.dsim.ui.setting.LoginActivity;
import com.dswey.dsim.ui.setting.SettingFragment;
import com.ncapdevi.fragnav.FragNavController;
import com.orhanobut.hawk.Hawk;
import com.wangenyong.mvp.base.BaseActivity;
import com.wangenyong.mvp.utils.UiUtil;
import com.wangenyong.mvp.view.SimpleLoadDialog;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author wangenyong
 */
public class MainActivity extends BaseActivity {
    ActivityMainBinding mBinding;

    private boolean isExit = false;
    private SimpleLoadDialog mLoadDialog;
    private FragNavController mFragNavController;
    private ExitReceiver mExitReceiver = new ExitReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // 注册退出广播，登陆界面点击返回退出按钮时，销毁 主页面
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.EXIT_ACTION);
        registerReceiver(mExitReceiver, filter);

        initTabBar(savedInstanceState);

        mLoadDialog = new SimpleLoadDialog(this, null, false);

        if (!Hawk.contains(Constants.USER_DATA)) {
            showLoginWindow();
        } else {
            autoLogin();
        }
    }

    private void initTabBar(Bundle savedInstanceState) {
        // 设置 BottomTabBar 样式
        mBinding.bottomBar.enableAnimation(false);
        mBinding.bottomBar.enableShiftingMode(false);
        mBinding.bottomBar.enableItemShiftingMode(false);

        // 初始化三个 Tab 对应的 Fragment, 使用 FragNavController 进行管理
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(MessageFragment.newInstance());
        fragments.add(ContactFragment.newInstance());
        fragments.add(SettingFragment.newInstance());

        FragNavController.Builder builder = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.contentContainer);
        builder.rootFragments(fragments);
        mFragNavController = builder.build();

        mBinding.bottomBar.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.message) {
                mFragNavController.switchTab(FragNavController.TAB1);
                return true;
            } else if (item.getItemId() == R.id.contact) {
                mFragNavController.switchTab(FragNavController.TAB2);
                return true;
            } else if (item.getItemId() == R.id.setting) {
                mFragNavController.switchTab(FragNavController.TAB3);
                return true;
            } else {
                return false;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mFragNavController != null) {
            mFragNavController.onSaveInstanceState(outState);
        }
    }

    private void showLoginWindow() {
        Intent intent = LoginActivity.newIntent(this);
        startActivity(intent);
    }

    private void autoLogin() {
        LoginResult result = Hawk.get(Constants.USER_DATA);
        Observable.just(result.getUser())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mLoadDialog.show("正在登录..."))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .flatMap(userModel -> {
                    LoginResult loginResult = SmackManager.getInstance().login(userModel.getName(), userModel.getPassword());
                    return Observable.just(loginResult);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(o -> mLoadDialog.dismiss())
                .subscribe(loginResult -> {
                    if (!loginResult.isSuccess()) {
                        UiUtil.makeText(MainActivity.this, loginResult.getErrorMsg());
                        Hawk.delete(Constants.USER_DATA);
                        showLoginWindow();
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    UiUtil.makeText(MainActivity.this, throwable.getMessage());
                    Hawk.delete(Constants.USER_DATA);
                    showLoginWindow();
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mExitReceiver);
    }

    @Override
    public void onBackPressed() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次返回键退出应用", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> isExit = false, 2000);
        } else { // 退出
            finish();
        }
    }

    class ExitReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            MainActivity.this.finish();
        }
    }
}

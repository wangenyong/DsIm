package com.dswey.dsim;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.dswey.dsim.databinding.ActivityMainBinding;
import com.dswey.dsim.ui.contact.ContactFragment;
import com.dswey.dsim.ui.message.MessageFragment;
import com.dswey.dsim.ui.setting.SettingFragment;
import com.ncapdevi.fragnav.FragNavController;
import com.wangenyong.mvp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangenyong
 */
public class MainActivity extends BaseActivity {
    ActivityMainBinding mBinding;

    private boolean isExit = false;

    private FragNavController mFragNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initTabBar(savedInstanceState);
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
}

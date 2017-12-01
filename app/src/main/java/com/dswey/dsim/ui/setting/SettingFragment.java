package com.dswey.dsim.ui.setting;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dswey.dsim.MainActivity;
import com.dswey.dsim.R;
import com.dswey.dsim.databinding.FragmentSettingBinding;
import com.dswey.dsim.im.SmackManager;
import com.dswey.dsim.model.Constants;
import com.dswey.dsim.ui.message.MessageFragment;
import com.orhanobut.hawk.Hawk;
import com.wangenyong.mvp.utils.UiUtil;

/**
 * A simple {@link Fragment} subclass.
 * @author wangenyong
 */
public class SettingFragment extends Fragment {
    private FragmentSettingBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBinding.toolbar.setTitle(getString(R.string.tab_setting));
        mBinding.btnLogout.setOnClickListener(view -> {
            if (SmackManager.getInstance().logout()) {
                UiUtil.makeText(getActivity(), "注销成功");
                Hawk.delete(Constants.USER_DATA);
                Intent intent = LoginActivity.newIntent(getActivity());
                getActivity().startActivity(intent);
            } else {
                UiUtil.makeText(getActivity(), "注销失败");
            }


        });
    }

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }
}

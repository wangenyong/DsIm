package com.dswey.dsim.ui.setting;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dswey.dsim.R;
import com.dswey.dsim.databinding.FragmentSettingBinding;
import com.dswey.dsim.ui.message.MessageFragment;

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
    }

    public SettingFragment() {
        // Required empty public constructor
    }


    public static SettingFragment newInstance() {
        return new SettingFragment();
    }
}

package com.dswey.dsim.ui.setting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dswey.dsim.R;
import com.dswey.dsim.ui.message.MessageFragment;

/**
 * A simple {@link Fragment} subclass.
 * @author wangenyong
 */
public class SettingFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    public SettingFragment() {
        // Required empty public constructor
    }


    public static SettingFragment newInstance() {
        return new SettingFragment();
    }
}

package com.dswey.dsim.ui.message;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dswey.dsim.R;
import com.dswey.dsim.databinding.FragmentMessageBinding;

/**
 * A simple {@link Fragment} subclass.
 * @author wangenyong
 */
public class MessageFragment extends Fragment {
    private FragmentMessageBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_message, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBinding.toolbar.setTitle(getString(R.string.tab_message));
    }

    public MessageFragment() {
        // Required empty public constructor
    }


    public static MessageFragment newInstance() {
        return new MessageFragment();
    }
}

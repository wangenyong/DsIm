package com.dswey.dsim.ui.contact;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dswey.dsim.R;
import com.dswey.dsim.databinding.FragmentContactBinding;

/**
 * A simple {@link Fragment} subclass.
 * @author wangenyong
 */
public class ContactFragment extends Fragment {
    private FragmentContactBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact, container, false);
        View view = mBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBinding.toolbar.setTitle(getString(R.string.tab_contact));
    }

    public ContactFragment() {
        // Required empty public constructor
    }


    public static ContactFragment newInstance() {
        return new ContactFragment();
    }
}

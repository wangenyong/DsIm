package com.dswey.dsim.ui.contact;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dswey.dsim.R;

/**
 * A simple {@link Fragment} subclass.
 * @author wangenyong
 */
public class ContactFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    public ContactFragment() {
        // Required empty public constructor
    }


    public static ContactFragment newInstance() {
        return new ContactFragment();
    }
}

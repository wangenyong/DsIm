package com.dswey.dsim.ui.message;


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
public class MessageFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    public MessageFragment() {
        // Required empty public constructor
    }


    public static MessageFragment newInstance() {
        return new MessageFragment();
    }
}

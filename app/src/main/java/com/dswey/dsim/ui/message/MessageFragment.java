package com.dswey.dsim.ui.message;



import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dswey.dsim.R;
import com.dswey.dsim.databinding.FragmentMessageBinding;
import com.dswey.dsim.model.Dialog;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

/**
 * A simple {@link Fragment} subclass.
 * @author wangenyong
 */
public class MessageFragment extends Fragment {
    private FragmentMessageBinding mBinding;
    private ImageLoader mImageLoader;
    private DialogsListAdapter<Dialog> mDialogsListAdapter;

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
        mImageLoader = (imageView, url) -> Glide.with(MessageFragment.this).load(url).into(imageView);
        initAdapter();
    }

    private void initAdapter() {
        mDialogsListAdapter = new DialogsListAdapter<>(mImageLoader);
        mBinding.dialogsList.setAdapter(mDialogsListAdapter);
    }


    public MessageFragment() {
        // Required empty public constructor
    }


    public static MessageFragment newInstance() {
        return new MessageFragment();
    }
}

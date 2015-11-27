package com.allen.loltool.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.loltool.R;
import com.allen.loltool.base.BaseFragment;
import com.allen.loltool.message.fragment.MessageFragment;

/**
 * Created by Allen on 2015/11/27.
 */
public class MineFragment extends BaseFragment {
    public static Fragment newInstance() {
        MineFragment mineFragment = new MineFragment();
        return mineFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        return view;
    }

    @Override
    protected void lazyLoad() {

    }
}

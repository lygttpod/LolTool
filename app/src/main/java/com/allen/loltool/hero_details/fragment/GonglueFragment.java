package com.allen.loltool.hero_details.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.loltool.R;
import com.allen.loltool.hero_details.bean.HeroDetailsBean;

/**
 * Created by Allen on 2015/11/18.
 */
public class GonglueFragment extends Fragment {
    private HeroDetailsBean heroDetailsBean;
    private static final String ARG = "arg";

    public static Fragment newInstance(HeroDetailsBean arg) {
        GonglueFragment fragment = new GonglueFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG, arg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gonglue, container, false);
        return view;
    }
}

package com.allen.loltool.message.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.allen.loltool.R;
import com.allen.loltool.base.BaseFragment;
import com.allen.loltool.hero.fragment.HeroFragment;
import com.allen.loltool.user.activity.LoginActivity;

/**
 * Created by Allen on 2015/11/27.
 */
public class MessageFragment extends BaseFragment {
    private RelativeLayout user_infor_rl;

    public static MessageFragment newInstance() {
        MessageFragment messageFragment = new MessageFragment();
        return messageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        user_infor_rl = ((RelativeLayout) view.findViewById(R.id.user_information));
        user_infor_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        return view;
    }

    @Override
    protected void lazyLoad() {

    }
}

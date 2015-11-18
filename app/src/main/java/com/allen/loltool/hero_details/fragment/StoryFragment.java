package com.allen.loltool.hero_details.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allen.loltool.R;
import com.allen.loltool.hero_details.bean.HeroDetailsBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen on 2015/11/18.
 */
public class StoryFragment extends Fragment {

    @Bind(R.id.partner_reason1)
    TextView partnerReason1;
    @Bind(R.id.partner_reason2)
    TextView partnerReason2;
    @Bind(R.id.opponent_reason1)
    TextView opponentReason1;
    @Bind(R.id.opponent_reason2)
    TextView opponentReason2;
    @Bind(R.id.story_bg_TV)
    TextView storyBgTV;
    private HeroDetailsBean heroDetailsBean;
    private static final String ARG = "arg";

    public static Fragment newInstance(HeroDetailsBean arg) {
        StoryFragment fragment = new StoryFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG, arg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        heroDetailsBean = (HeroDetailsBean) getArguments().getSerializable(ARG);

        partnerReason1.setText(heroDetailsBean.getPartner_reason1().toString().trim());
        partnerReason2.setText(heroDetailsBean.getPartner_reason2().toString().trim());
        opponentReason1.setText(heroDetailsBean.getOpponent_reason1().toString().trim());
        opponentReason2.setText(heroDetailsBean.getOpponent_reason2().toString().trim());
        storyBgTV.setText(heroDetailsBean.getStory().toString().trim());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

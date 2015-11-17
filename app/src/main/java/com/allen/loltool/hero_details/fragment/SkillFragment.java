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
 * Created by Allen on 2015/11/17.
 */
public class SkillFragment extends Fragment {
    @Bind(R.id.op_skill_TV)
    TextView opSkillTV;
    @Bind(R.id.teamwork_TV)
    TextView teamworkTV;
    @Bind(R.id.use_skill_TV)
    TextView useSkillTV;
    @Bind(R.id.ag_skill_TV)
    TextView agSkillTV;

    private HeroDetailsBean heroDetailsBean;
    private static final String ARG = "arg";

    public static Fragment newInstance(HeroDetailsBean arg) {
        SkillFragment fragment = new SkillFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG, arg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skill, container, false);
        ButterKnife.bind(this, view);

        initData();
        return view;
    }

    private void initData() {
        heroDetailsBean = (HeroDetailsBean) getArguments().getSerializable(ARG);

        opSkillTV.setText(heroDetailsBean.getOp_skill().toString().trim());
        teamworkTV.setText(heroDetailsBean.getTeamwork().toString().trim());
        useSkillTV.setText(heroDetailsBean.getUse_skill1().toString().trim() + "\n" + heroDetailsBean.getUse_skill2().toString().trim() + "\n" + heroDetailsBean.getUse_skill3().toString().trim());
        agSkillTV.setText(heroDetailsBean.getAg_skill1().toString().trim() + "\n" + heroDetailsBean.getAg_skill2().toString().trim() + "\n" + heroDetailsBean.getAg_skill3().toString().trim());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

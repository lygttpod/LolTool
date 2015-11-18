package com.allen.loltool.hero_details.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.loltool.R;
import com.allen.loltool.common.UrlAddress;
import com.allen.loltool.hero_details.bean.HeroDetailsBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

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
    @Bind(R.id.skill1_img)
    ImageView skill1Img;
    @Bind(R.id.skill2_img)
    ImageView skill2Img;
    @Bind(R.id.skill3_img)
    ImageView skill3Img;
    @Bind(R.id.skill4_img)
    ImageView skill4Img;
    @Bind(R.id.skill5_img)
    ImageView skill5Img;
    @Bind(R.id.skill_name_TV)
    TextView skillNameTV;
    @Bind(R.id.skill_des_TV)
    TextView skillDesTV;

    private HeroDetailsBean heroDetailsBean;
    private static final String ARG = "arg";

    String skill1[];
    String skill2[];
    String skill3[];
    String skill4[];
    String skill5[];

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
        heroDetailsBean = (HeroDetailsBean) getArguments().getSerializable(ARG);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {

        opSkillTV.setText(heroDetailsBean.getOp_skill().toString().trim());
        teamworkTV.setText(heroDetailsBean.getTeamwork().toString().trim());
        useSkillTV.setText(heroDetailsBean.getUse_skill1().toString().trim() + "\n" + heroDetailsBean.getUse_skill2().toString().trim() + "\n" + heroDetailsBean.getUse_skill3().toString().trim());
        agSkillTV.setText(heroDetailsBean.getAg_skill1().toString().trim() + "\n" + heroDetailsBean.getAg_skill2().toString().trim() + "\n" + heroDetailsBean.getAg_skill3().toString().trim());
        skillDesTV.setText(heroDetailsBean.getSkill1_desc());
        skill1 = heroDetailsBean.getSkill1().split("[|]");
        skill2 = heroDetailsBean.getSkill2().split("[|]");
        skill3 = heroDetailsBean.getSkill3().split("[|]");
        skill4 = heroDetailsBean.getSkill4().split("[|]");
        skill5 = heroDetailsBean.getSkill5().split("[|]");
        skillNameTV.setText(skill1[1]);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.skill)
                .showImageOnFail(R.mipmap.skill).cacheInMemory(true)
                .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();

        ImageLoader.getInstance().displayImage(UrlAddress.base_url + skill1[0], skill1Img, options);
        ImageLoader.getInstance().displayImage(UrlAddress.base_url + skill2[0], skill2Img, options);
        ImageLoader.getInstance().displayImage(UrlAddress.base_url + skill3[0], skill3Img, options);
        ImageLoader.getInstance().displayImage(UrlAddress.base_url + skill4[0], skill4Img, options);
        ImageLoader.getInstance().displayImage(UrlAddress.base_url + skill5[0], skill5Img, options);


        skill1Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skillNameTV.setText(skill1[1]);
                skillDesTV.setText(heroDetailsBean.getSkill1_desc());
            }
        });
        skill2Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skillNameTV.setText(skill2[1]);
                skillDesTV.setText(heroDetailsBean.getSkill2_desc() + "\n" + heroDetailsBean.getSkill2_cooling() + "\n" + heroDetailsBean.getSkill2_expend());
            }
        });

        skill3Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skillNameTV.setText(skill3[1]);
                skillDesTV.setText(heroDetailsBean.getSkill3_desc() + "\n" + heroDetailsBean.getSkill3_cooling() + "\n" + heroDetailsBean.getSkill3_expend());
            }
        });

        skill4Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skillNameTV.setText(skill4[1]);
                skillDesTV.setText(heroDetailsBean.getSkill4_desc() + "\n" + heroDetailsBean.getSkill4_cooling() + "\n" + heroDetailsBean.getSkill4_expend());
            }
        });

        skill5Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skillNameTV.setText(skill5[1]);
                skillDesTV.setText(heroDetailsBean.getSkill5_desc() + "\n" + heroDetailsBean.getSkill5_cooling() + "\n" + heroDetailsBean.getSkill5_expend());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

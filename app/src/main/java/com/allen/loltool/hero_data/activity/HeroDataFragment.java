package com.allen.loltool.hero_data.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.loltool.R;
import com.allen.loltool.base.BaseFragment;
import com.allen.loltool.hero_data.adapter.HeroDataFragmentAdapter;
import com.allen.loltool.hero_data.fragment.AllHeroFragment;
import com.allen.loltool.hero_data.fragment.FreeHeroFragment;
import com.allen.loltool.hero_data.fragment.SummonerFragment;
import com.allen.loltool.news.fragment.NewsFragment;
import com.allen.loltool.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen on 2015/11/24.
 */
public class HeroDataFragment extends BaseFragment {

    @Bind(R.id.hero_data_tablayout)
    TabLayout heroDataTablayout;
    @Bind(R.id.hero_data_viewpager)
    ViewPager heroDataViewpager;

    private HeroDataFragmentAdapter adapter;
    private List<String> hero_titles;
    private List<Fragment> fragments;

    public static Fragment newInstance() {
        HeroDataFragment heroDataFragment = new HeroDataFragment();
        return heroDataFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hero_data, container, false);
        ButterKnife.bind(this, view);
        setupViewPager();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        hero_titles = new ArrayList<>();
        fragments = new ArrayList<>();

    }

    private void initData() {
        hero_titles.add("周免英雄");
        hero_titles.add("全部英雄");
        hero_titles.add("召唤师技能");

    }

    private void setupViewPager() {
        if (hero_titles.size()<=0){
            initData();
        }
        heroDataTablayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < hero_titles.size(); i++) {
            heroDataTablayout.addTab(heroDataTablayout.newTab().setText(hero_titles.get(i)));
            LogUtil.e("HeroDataFragment", "titles.size()=" + hero_titles.size());
        }
        fragments.add(new FreeHeroFragment().newInstance());
        fragments.add(new AllHeroFragment().newInstance());
        fragments.add(new SummonerFragment().newInstance());

        adapter =
                new HeroDataFragmentAdapter(getActivity().getSupportFragmentManager(), fragments, hero_titles);
        heroDataViewpager.setAdapter(adapter);
        heroDataViewpager.setOffscreenPageLimit(3);
        heroDataTablayout.setupWithViewPager(heroDataViewpager);
        heroDataTablayout.setTabsFromPagerAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    protected void lazyLoad() {

    }
}

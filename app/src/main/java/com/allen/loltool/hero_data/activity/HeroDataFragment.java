package com.allen.loltool.hero_data.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.allen.loltool.R;
import com.allen.loltool.base.BaseFragment;
import com.allen.loltool.hero_data.adapter.HeroDataFragmentAdapter;
import com.allen.loltool.hero_data.fragment.AllHeroFragment;
import com.allen.loltool.hero_data.fragment.FreeHeroFragment;
import com.allen.loltool.hero_data.fragment.SummonerFragment;

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

    private void setupViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("周免英雄");
        titles.add("全部英雄");
        titles.add("召唤师技能");
        heroDataTablayout.setTabMode(TabLayout.MODE_FIXED);
        heroDataTablayout.addTab(heroDataTablayout.newTab().setText(titles.get(0)));
        heroDataTablayout.addTab(heroDataTablayout.newTab().setText(titles.get(1)));
        heroDataTablayout.addTab(heroDataTablayout.newTab().setText(titles.get(2)));
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FreeHeroFragment().newInstance());
        fragments.add(new AllHeroFragment().newInstance());
        fragments.add(new SummonerFragment().newInstance());
        adapter =
                new HeroDataFragmentAdapter(getActivity().getSupportFragmentManager(), fragments, titles);
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

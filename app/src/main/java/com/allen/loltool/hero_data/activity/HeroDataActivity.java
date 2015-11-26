package com.allen.loltool.hero_data.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.allen.loltool.R;
import com.allen.loltool.hero_data.adapter.HeroDataFragmentAdapter;
import com.allen.loltool.hero_data.fragment.AllHeroFragment;
import com.allen.loltool.hero_data.fragment.FreeHeroFragment;
import com.allen.loltool.summoner.fragment.SummonerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen on 2015/11/20.
 */
public class HeroDataActivity extends AppCompatActivity {

    @Bind(R.id.hero_data_tablayout)
    TabLayout heroDataTablayout;
    @Bind(R.id.hero_data_viewpager)
    ViewPager heroDataViewpager;

    private HeroDataFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_data);
        ButterKnife.bind(this);
        setupViewPager();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setupViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("周免英雄");
        titles.add("全部英雄");
        heroDataTablayout.setTabMode(TabLayout.MODE_FIXED);
        heroDataTablayout.addTab(heroDataTablayout.newTab().setText(titles.get(0)));
        heroDataTablayout.addTab(heroDataTablayout.newTab().setText(titles.get(1)));
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FreeHeroFragment().newInstance());
        fragments.add(new AllHeroFragment().newInstance());
        adapter =
                new HeroDataFragmentAdapter(getSupportFragmentManager(), fragments, titles);
        heroDataViewpager.setAdapter(adapter);
        heroDataViewpager.setOffscreenPageLimit(2);
        heroDataTablayout.setupWithViewPager(heroDataViewpager);
        heroDataTablayout.setTabsFromPagerAdapter(adapter);
    }
}

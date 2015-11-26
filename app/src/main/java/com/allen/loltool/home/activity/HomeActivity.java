package com.allen.loltool.home.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.allen.loltool.R;
import com.allen.loltool.hero.fragment.HeroFragment;
import com.allen.loltool.summoner.fragment.SummonerFragment;
import com.allen.loltool.home.adapter.HomeFragmentAdapter;
import com.allen.loltool.news.fragment.NewsHomeFragment;
import com.allen.loltool.server_list.fragment.ServerListFragment;
import com.allen.loltool.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen on 2015/11/24.
 */
public class HomeActivity extends AppCompatActivity {
    @Bind(R.id.home_viewpager)
    ViewPager homeViewpager;
    @Bind(R.id.home_tablayout)
    TabLayout homeTablayout;


    private HomeFragmentAdapter homeFragmentAdapter;

    private Fragment[] fragment;
    private List<Fragment> fragments;
    private List<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        fragment = new Fragment[4];
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        setupViewPager();

    }



    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.e("HomeActivity", "onStart");
        initFragments();
        if (titles.size() <= 0) {
            initTab();
        }
    }

    private void initFragments() {
        LogUtil.e("HomeActivity", "initFragments");
        fragment[0] = new NewsHomeFragment().newInstance();
        fragment[1] = new HeroFragment().newInstance();
        fragment[2] = new ServerListFragment().newInstance();
        fragment[3] = new SummonerFragment().newInstance();

        for (int i = 0; i < fragment.length; i++) {
            fragments.add(fragment[i]);
        }
        homeFragmentAdapter.notifyDataSetChanged();
    }

    private void initTab() {

        titles.add("资讯");
        titles.add("英雄");
        titles.add("消息");
        titles.add("我的");

        homeTablayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < titles.size(); i++) {
            homeTablayout.addTab(homeTablayout.newTab().setText(titles.get(i)));
        }
        LogUtil.e("HomeActivity", "titles.size()=" + titles.size());
    }

    private void setupViewPager() {
        homeFragmentAdapter =
                new HomeFragmentAdapter(getSupportFragmentManager(), fragments, titles, HomeActivity.this);
        homeViewpager.setAdapter(homeFragmentAdapter);
        homeViewpager.setOffscreenPageLimit(4);
        homeTablayout.setupWithViewPager(homeViewpager);
        homeTablayout.setTabsFromPagerAdapter(homeFragmentAdapter);

//        for (int i = 0; i < homeTablayout.getTabCount(); i++) {
//            TabLayout.Tab tab = homeTablayout.getTabAt(i);
//            if (tab != null) {
//                tab.setCustomView(homeFragmentAdapter.getTabView(i));
//            }
//        }
    }


}

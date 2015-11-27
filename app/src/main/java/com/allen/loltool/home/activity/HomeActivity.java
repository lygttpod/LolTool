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
import com.allen.loltool.message.fragment.MessageFragment;
import com.allen.loltool.mine.fragment.MineFragment;
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

    private Fragment[] fragment = {new NewsHomeFragment().newInstance(),
            new HeroFragment().newInstance(),
            new MessageFragment().newInstance(),
            new MineFragment().newInstance()};
    private List<Fragment> fragments;
    private List<String> titles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
        initFragments();
        setupViewPager();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initFragments() {
        for (int i = 0; i < fragment.length; i++) {
            fragments.add(fragment[i]);
        }

    }

    private void setupViewPager() {
        homeFragmentAdapter =
                new HomeFragmentAdapter(getSupportFragmentManager(), fragments, HomeActivity.this);
        homeViewpager.setAdapter(homeFragmentAdapter);
        homeViewpager.setOffscreenPageLimit(4);
        homeTablayout.setTabMode(TabLayout.MODE_FIXED);
        homeTablayout.setupWithViewPager(homeViewpager);
        homeTablayout.setTabsFromPagerAdapter(homeFragmentAdapter);

        for (int i = 0; i < 4; i++) {
            TabLayout.Tab tab = homeTablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(homeFragmentAdapter.getTabView(i));
            }
        }
    }


}

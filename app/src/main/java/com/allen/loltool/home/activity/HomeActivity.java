package com.allen.loltool.home.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.loltool.R;
import com.allen.loltool.hero_data.activity.HeroDataFragment;
import com.allen.loltool.hero_data.adapter.HeroDataFragmentAdapter;
import com.allen.loltool.hero_data.fragment.AllHeroFragment;
import com.allen.loltool.hero_data.fragment.FreeHeroFragment;
import com.allen.loltool.hero_data.fragment.SummonerFragment;
import com.allen.loltool.home.adapter.HomeFragmentAdapter;
import com.allen.loltool.home.fragment.NewsHomeFragment;
import com.allen.loltool.server_list.fragment.ServerListFragment;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setupViewPager();
    }

    private void setupViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("看资讯");
        titles.add("查英雄");
        titles.add("服务器");
        homeTablayout.setTabMode(TabLayout.MODE_FIXED);
        homeTablayout.addTab(homeTablayout.newTab().setText(titles.get(0)));
        homeTablayout.addTab(homeTablayout.newTab().setText(titles.get(1)));
        homeTablayout.addTab(homeTablayout.newTab().setText(titles.get(2)));
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new NewsHomeFragment().newInstance());
        fragments.add(new HeroDataFragment().newInstance());
        fragments.add(new ServerListFragment().newInstance());
        homeFragmentAdapter =
                new HomeFragmentAdapter(getSupportFragmentManager(), fragments, titles, HomeActivity.this);
        homeViewpager.setAdapter(homeFragmentAdapter);
        homeViewpager.setOffscreenPageLimit(3);
        homeTablayout.setupWithViewPager(homeViewpager);
        homeTablayout.setTabsFromPagerAdapter(homeFragmentAdapter);

        for (int i = 0; i < homeTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = homeTablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(homeFragmentAdapter.getTabView(i));
            }
        }
    }


}

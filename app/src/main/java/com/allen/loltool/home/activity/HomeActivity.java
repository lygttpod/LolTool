package com.allen.loltool.home.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.allen.loltool.R;
import com.allen.loltool.hero_data.activity.HeroDataFragment;
import com.allen.loltool.hero_data.fragment.SummonerFragment;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupViewPager();

    }

    private void setupViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("资讯");
        titles.add("英雄");
        titles.add("消息");
        titles.add("我的");
        homeTablayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < titles.size(); i++) {
            homeTablayout.addTab(homeTablayout.newTab().setText(titles.get(i)));
            LogUtil.e("HomeActivity","titles.size()="+titles.size());
        }

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new NewsHomeFragment().newInstance());
        fragments.add(new HeroDataFragment().newInstance());
        fragments.add(new ServerListFragment().newInstance());
        fragments.add(new SummonerFragment().newInstance());

        homeFragmentAdapter =
                new HomeFragmentAdapter(getSupportFragmentManager(), fragments, titles, HomeActivity.this);
        homeViewpager.setAdapter(homeFragmentAdapter);
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

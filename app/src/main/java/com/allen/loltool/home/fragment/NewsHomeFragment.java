package com.allen.loltool.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.loltool.R;
import com.allen.loltool.home.adapter.HomeFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen on 2015/11/24.
 */
public class NewsHomeFragment extends Fragment {

    @Bind(R.id.news_home_tablayout)
    TabLayout newsHomeTablayout;
    @Bind(R.id.news_home_viewpager)
    ViewPager newsHomeViewpager;

    private HomeFragmentAdapter homeFragmentAdapter;


    public static Fragment newInstance() {
        NewsHomeFragment newsHomeFragment = new NewsHomeFragment();
        return newsHomeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_home, container, false);
        ButterKnife.bind(this, view);
        setupViewPager();
        return view;
    }

    private void setupViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("最新");
        titles.add("赛事");
        titles.add("视频");
        titles.add("娱乐");
        titles.add("攻略");

        List<String> urls = new ArrayList<>();
        urls.add("c12_list_1.shtml");
        urls.add("c73_list_1.shtml");
        urls.add("http://lol.qq.com/m/act/a20150319lolapp/video.htm");
        urls.add("c18_list_1.shtml");
        urls.add("c10_list_1.shtml");

        newsHomeTablayout.setTabMode(TabLayout.MODE_FIXED);

        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            if (titles.get(i).toString().equals("视频")){
//                newsHomeTablayout.addTab(newsHomeTablayout.newTab().setText(titles.get(i)));
//                fragments.add(new VideoFragment().newInstance(urls.get(i)));
            }else {
                newsHomeTablayout.addTab(newsHomeTablayout.newTab().setText(titles.get(i)));
                fragments.add(new NewsFragment().newInstance(urls.get(i)));
            }

        }

        homeFragmentAdapter =
                new HomeFragmentAdapter(getActivity().getSupportFragmentManager(), fragments, titles, getActivity());
        newsHomeViewpager.setAdapter(homeFragmentAdapter);
        //newsHomeViewpager.setOffscreenPageLimit(3);
        newsHomeTablayout.setupWithViewPager(newsHomeViewpager);
        newsHomeTablayout.setTabsFromPagerAdapter(homeFragmentAdapter);

        for (int i = 0; i < newsHomeTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = newsHomeTablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(homeFragmentAdapter.getTabView(i));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

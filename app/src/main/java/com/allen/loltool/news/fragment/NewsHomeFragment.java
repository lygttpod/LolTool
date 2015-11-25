package com.allen.loltool.news.fragment;

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
import com.allen.loltool.news.adapter.NewsFragmentAdapter;
import com.allen.loltool.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen on 2015/11/24.
 */
public class NewsHomeFragment extends BaseFragment {

    @Bind(R.id.news_home_tablayout)
    TabLayout newsHomeTablayout;
    @Bind(R.id.news_home_viewpager)
    ViewPager newsHomeViewpager;

    private NewsFragmentAdapter newsFragmentAdapter;
    private List<String> titles;
    private List<String> urls;

    public static Fragment newInstance() {
        NewsHomeFragment newsHomeFragment = new NewsHomeFragment();
        return newsHomeFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        titles = new ArrayList<>();
        urls = new ArrayList<>();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_home, container, false);
        ButterKnife.bind(this, view);
        setupViewPager();
        return view;
    }

    private void initData() {
        titles.add("最新");
        titles.add("赛事");
        titles.add("娱乐");
        titles.add("攻略");

        urls.add("c12_list_1.shtml");
        urls.add("c73_list_1.shtml");
        //urls.add("http://lol.qq.com/m/act/a20150319lolapp/video.htm");
        urls.add("c18_list_1.shtml");
        urls.add("c10_list_1.shtml");
    }

    private void setupViewPager() {
        if (titles.size() <= 0) {
            initData();
        }
        newsHomeTablayout.setTabMode(TabLayout.MODE_FIXED);

        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            newsHomeTablayout.addTab(newsHomeTablayout.newTab().setText(titles.get(i)));
            fragments.add(new NewsFragment().newInstance(urls.get(i)));
            LogUtil.e("NewsHomeFragment", "titles.size()=" + titles.size());
        }

        newsFragmentAdapter =
                new NewsFragmentAdapter(getActivity().getSupportFragmentManager(), fragments, titles, getActivity());
        newsHomeViewpager.setAdapter(newsFragmentAdapter);
        //newsHomeViewpager.setOffscreenPageLimit(3);
        newsHomeTablayout.setupWithViewPager(newsHomeViewpager);
        newsHomeTablayout.setTabsFromPagerAdapter(newsFragmentAdapter);

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

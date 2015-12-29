package com.allen.loltool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.allen.loltool.bean.Tab;
import com.allen.loltool.hero.fragment.HeroFragment;
import com.allen.loltool.message.fragment.MessageFragment;
import com.allen.loltool.mine.fragment.MineFragment;
import com.allen.loltool.news.fragment.NewsHomeFragment;
import com.allen.loltool.news.fragment.VideoFragment;
import com.allen.loltool.utils.ToastUtils;
import com.allen.loltool.widget.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;
    private List<Tab> tabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTab();

    }

    private void initTab() {
        Tab home = new Tab(R.string.home, R.drawable.home_bottom_tab_information_selector, NewsHomeFragment.class);
        Tab hero = new Tab(R.string.hero, R.drawable.home_bottom_tab_hero_selector, HeroFragment.class);
        Tab video = new Tab(R.string.video, R.drawable.home_bottom_tab_video_selector, VideoFragment.class);
        Tab msg = new Tab(R.string.message, R.drawable.home_bottom_tab_community_selector, MessageFragment.class);
        Tab mine = new Tab(R.string.mine, R.drawable.home_bottom_tab_more_selector, MineFragment.class);
        tabs.add(home);
        tabs.add(hero);
        tabs.add(video);
        tabs.add(msg);
        tabs.add(mine);

        mInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(R.id.main_tabhost);

        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);

        for (Tab tab : tabs) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(tab.getTitle())).setIndicator(initBuildIndicator(tab));
            mTabHost.addTab(tabSpec, tab.getFragment(), null);
        }
        mTabHost.setCurrentTab(0);

    }

    //构建tahhost的Indicator
    private View initBuildIndicator(Tab tab) {
        View view = mInflater.inflate(R.layout.item_tab, null);
        ImageView icon = (ImageView) view.findViewById(R.id.item_tab_icon);

        icon.setBackgroundResource(tab.getIcon());
        return view;
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 1000) {
                ToastUtils.showShort(this, "连按两次退出系统");
                exitTime = System.currentTimeMillis();
            } else {
                this.finish();
            }
        }
        return false;
    }
}

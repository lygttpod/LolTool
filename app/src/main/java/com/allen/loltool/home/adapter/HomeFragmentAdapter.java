package com.allen.loltool.home.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.loltool.R;

import java.util.List;

/**
 * Created by Allen on 2015/11/24.
 */
public class HomeFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private Context mContext;

    private String[] tabs = {"资讯", "英雄", "消息", "我的"};
    private int[] imgId = {R.mipmap.tab_news_checked, R.mipmap.tab_friend_checked, R.mipmap.tab_discovery_checked, R.mipmap.tab_me_checked};

    public HomeFragmentAdapter(FragmentManager fm, List<Fragment> mFragments, Context mContext) {
        super(fm);
        this.mFragments = mFragments;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return tabs.length;
    }


    public View getTabView(int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.home_tab_item, null);
        TextView tv = (TextView) v.findViewById(R.id.tab_title);
        tv.setText(tabs[position]);
        ImageView img = (ImageView) v.findViewById(R.id.tab_image);
        img.setImageResource(imgId[position]);
        return v;
    }
}

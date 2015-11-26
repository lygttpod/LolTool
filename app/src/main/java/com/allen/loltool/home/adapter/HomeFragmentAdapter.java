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
    private List<String> mTitles;
    private Context mContext;

    public HomeFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public HomeFragmentAdapter(FragmentManager fm, List<Fragment> mFragments, List<String> mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    public HomeFragmentAdapter(FragmentManager fm, List<Fragment> mFragments, List<String> mTitles, Context mContext) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    public View getTabView(int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.home_tab_item, null);
        TextView tv = (TextView) v.findViewById(R.id.tab_title);
        tv.setText(mTitles.get(position));
        ImageView img = (ImageView) v.findViewById(R.id.tab_image);
        img.setImageResource(R.mipmap.list_img);
        return v;
    }
}

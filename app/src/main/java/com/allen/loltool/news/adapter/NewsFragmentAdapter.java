package com.allen.loltool.news.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.loltool.R;
import com.allen.loltool.utils.LogUtil;

import java.util.List;

/**
 * Created by Allen on 2015/11/24.
 */
public class NewsFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mTitles;
    private Context mContext;

    public NewsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public NewsFragmentAdapter(FragmentManager fm, List<Fragment> mFragments, List<String> mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    public NewsFragmentAdapter(FragmentManager fm, List<Fragment> mFragments, List<String> mTitles, Context mContext) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        LogUtil.e("NewsFragmentAdapter", "getItem=" + position);
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

}

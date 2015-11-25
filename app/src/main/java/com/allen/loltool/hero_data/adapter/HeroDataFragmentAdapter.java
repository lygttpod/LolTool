package com.allen.loltool.hero_data.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.allen.loltool.utils.LogUtil;

import java.util.List;

/**
 * Created by Allen on 2015/11/17.
 */
public class HeroDataFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mTitles;

    public HeroDataFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public HeroDataFragmentAdapter(FragmentManager fm, List<Fragment> mFragments, List<String> mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        LogUtil.e("HeroDataFragmentAdapter","getItem="+position);
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

package com.allen.loltool.hero.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.allen.loltool.R;
import com.allen.loltool.base.BaseFragment;
import com.allen.loltool.hero.adapter.FragmentHeroAdapter;
import com.allen.loltool.hero_data.activity.HeroDataActivity;
import com.allen.loltool.server_list.activity.ServerListActivity;
import com.allen.loltool.summoner.activity.SummonerActivity;
import com.allen.loltool.utils.DisplayUtil;
import com.allen.loltool.widget.bannnerpager.BannerPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen on 2015/11/26.
 */
public class HeroFragment extends BaseFragment {

    @Bind(R.id.fragment_hero_listview)
    ListView fragmentHeroListview;
    @Bind(R.id.bannerpager)
    BannerPager mBannerPager;
    @Bind(R.id.bannerpager_ll_adtip)
    LinearLayout mAdTipLayout;
    private FragmentHeroAdapter fragmentHeroAdapter;

    private String[] imgurls = {
            "http://www.baidu.com/img/bdlogo.png",
            "http://b.hiphotos.baidu.com/news/q%3D100/sign=32bf684cf203918fd1d139ca613c264b/3b87e950352ac65c41a059dffef2b21192138af0.jpg",
            "http://www.baidu.com/img/bdlogo.png"};


    public static Fragment newInstance() {
        HeroFragment heroFragment = new HeroFragment();
        return heroFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hero, container, false);
        ButterKnife.bind(this, view);
        initToolbar(view);
        showAdvertise(imgurls);
        init();
        return view;
    }

    private void initToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("英雄");
    }

    private void init() {
        fragmentHeroAdapter = new FragmentHeroAdapter(getActivity());
        fragmentHeroListview.setAdapter(fragmentHeroAdapter);
        fragmentHeroListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(getActivity(), HeroDataActivity.class);
                        break;
                    case 1:
                        intent.setClass(getActivity(), SummonerActivity.class);
                        break;
                    case 2:
                        intent.setClass(getActivity(), ServerListActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void showAdvertise(String[] imgurls) {
        if (imgurls == null || imgurls.length == 0) {
            return;
        }
        // 设置mGallery的大小
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mBannerPager
                .getLayoutParams();

        params.width = DisplayUtil.getDisplayWidth(getActivity());
        params.height = params.width * 9 / 16;
        mBannerPager.setLayoutParams(params);
        mAdTipLayout.removeAllViews();
        List<String> urls = new ArrayList<String>();
        for (int i = 0; i < imgurls.length && i < 6; i++) {

            ImageView view = new ImageView(getActivity());
            view.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            view.setClickable(false);
            view.setFocusableInTouchMode(false);
            view.setFocusable(false);
            view.setImageResource(R.mipmap.ad_tip_normal);
            view.setPadding(8, 0, 8, 0);
            mAdTipLayout.addView(view);
            urls.add(imgurls[i]);
        }

        if (mAdTipLayout.getChildAt(0) != null) {
            ((ImageView) mAdTipLayout.getChildAt(0))
                    .setImageResource(R.mipmap.ad_tip_selected);
        }
        mBannerPager.setUrls(urls);

        mBannerPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageSelected(int position) {
                int length = mAdTipLayout.getChildCount();
                for (int i = 0; i < length; i++) {
                    ((ImageView) mAdTipLayout.getChildAt(i))
                            .setImageResource(R.mipmap.ad_tip_normal);
                }
                position = position % length;
                ((ImageView) mAdTipLayout.getChildAt(position))
                        .setImageResource(R.mipmap.ad_tip_selected);
            }

        });
    }
}

package com.allen.loltool.hero_details.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.allen.loltool.R;
import com.allen.loltool.common.UrlAddress;
import com.allen.loltool.hero_details.adapter.FragmentAdapter;
import com.allen.loltool.hero_details.bean.HeroDetailsBean;
import com.allen.loltool.hero_details.fragment.EquipmentFragment;
import com.allen.loltool.hero_details.fragment.GonglueFragment;
import com.allen.loltool.hero_details.fragment.SkillFragment;
import com.allen.loltool.hero_details.fragment.StoryFragment;
import com.allen.loltool.utils.JsonUtils;
import com.allen.loltool.utils.LogUtil;
import com.allen.loltool.utils.ToastUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;


/**
 * Created by Allen on 2015/11/16.
 */
public class HeroDetailsActivity extends AppCompatActivity {


    @Bind(R.id.backdrop)
    ImageView backdrop;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
//    @Bind(R.id.actionBtn)
//    FloatingActionButton actionBtn;

    private String hid = "";
    private String hname;
    private AsyncHttpClient asyncHttpClient;
    private Context context;
    private HeroDetailsBean heroDetailsBean;
    private FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_details);
        context = this;
        heroDetailsBean = null;
        Bundle bundle = getIntent().getExtras();
        hid = bundle.getString("id");
        hname = bundle.getString("name").substring(0, bundle.getString("name").indexOf(" "));
        ButterKnife.bind(this);
        getHeroDetails(hid);
        initToolbar();

    }

    private void initToolbar() {
        toolbar.setTitle(hname);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.titlebar_leftarrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("技能");
        titles.add("出装");
        titles.add("故事");
        titles.add("攻略");
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.addTab(tablayout.newTab().setText(titles.get(0)));
        tablayout.addTab(tablayout.newTab().setText(titles.get(1)));
        tablayout.addTab(tablayout.newTab().setText(titles.get(2)));
        tablayout.addTab(tablayout.newTab().setText(titles.get(3)));
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SkillFragment().newInstance(heroDetailsBean));
        fragments.add(new EquipmentFragment().newInstance(heroDetailsBean));
        fragments.add(new StoryFragment().newInstance(heroDetailsBean));
        fragments.add(new GonglueFragment().newInstance(heroDetailsBean));
        adapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
        tablayout.setTabsFromPagerAdapter(adapter);
    }

    private void getHeroDetails(String hid) {
        LogUtil.d("hero_details_url===========", UrlAddress.hero_details_base_url + hid);
        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(context, UrlAddress.hero_details_base_url + hid, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                heroDetailsBean = JsonUtils.getObject(response, HeroDetailsBean.class);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                ToastUtils.showShort(context, "数据加载失败，请稍后再试！");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (heroDetailsBean != null) {
                    initTopBgd();
                    setupViewPager();
                } else {
                    ToastUtils.showShort(context, "数据加载失败，请稍后再试！");

                }
            }
        });
    }

    private void initTopBgd() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.hero_detail_header)
                .showImageOnFail(R.mipmap.hero_detail_header).cacheInMemory(true)
                .cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();

        ImageLoader.getInstance()
                .displayImage(
                        UrlAddress.base_url + heroDetailsBean.getBg_img(),
                        backdrop, options);
    }
}

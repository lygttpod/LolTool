package com.allen.loltool.free_hero.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.allen.loltool.R;
import com.allen.loltool.common.UrlAddress;
import com.allen.loltool.free_hero.adapter.FreeHeroAdapter;
import com.allen.loltool.free_hero.bean.FreeHeroBean;
import com.allen.loltool.hero_details.activity.HeroDetailsActivity;
import com.allen.loltool.utils.JsonUtils;
import com.allen.loltool.utils.ToastUtils;
import com.allen.loltool.widget.loading.AVLoadingIndicatorView;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by hardy on 2015/11/13.
 */
public class FreeHeroActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.free_hero_gridview)
    PullToRefreshGridView freeHeroGridview;
    @Bind(R.id.loadingView)
    AVLoadingIndicatorView loadingView;

    private FreeHeroAdapter freeHeroAdapter;
    private AsyncHttpClient asyncHttpClient;
    private Context context;
    private List<FreeHeroBean.DataEntity> dataEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_hero);
        initToolbar();
        ButterKnife.bind(this);
        context = this;
        dataEntities = new ArrayList<>();
        initGridview();
        getFreeHeroList();
    }

    private void initGridview() {
        freeHeroAdapter = new FreeHeroAdapter(context, dataEntities);
        freeHeroGridview.getRefreshableView().setAdapter(freeHeroAdapter);
        freeHeroGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showShort(context, dataEntities.get(position).getName() + position);
                String heroId = dataEntities.get(position).getId();
                String heroName = dataEntities.get(position).getName();
                Intent intent = new Intent(context, HeroDetailsActivity.class);
                intent.putExtra("id",heroId);
                intent.putExtra("name", heroName);
                startActivity(intent);
            }
        });

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("周免英雄");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.titlebar_leftarrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getFreeHeroList() {
        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(context, UrlAddress.free_hero_url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                loadingView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                FreeHeroBean freeHeroBean = JsonUtils.getObject(response, FreeHeroBean.class);

                for (FreeHeroBean.DataEntity data : freeHeroBean.getData()
                        ) {
                    dataEntities.add(data);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                ToastUtils.showShort(context, "数据加载失败，请稍后再试！");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                loadingView.setVisibility(View.GONE);
                freeHeroAdapter.notifyDataSetChanged();
            }
        });
    }
}

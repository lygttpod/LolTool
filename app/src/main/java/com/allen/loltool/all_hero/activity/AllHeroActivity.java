package com.allen.loltool.all_hero.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.allen.loltool.R;
import com.allen.loltool.common.UrlAddress;
import com.allen.loltool.all_hero.adapter.AllHeroAdapter;
import com.allen.loltool.all_hero.bean.AllHeroBean;
import com.allen.loltool.utils.JsonUtils;
import com.allen.loltool.widget.loading.AVLoadingIndicatorView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by hardy on 2015/11/13.
 */
public class AllHeroActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.free_hero_gridview)
    PullToRefreshGridView freeHeroGridview;
    @Bind(R.id.loadingView)
    AVLoadingIndicatorView loadingView;

    private AllHeroAdapter allHeroAdapter;
    private AsyncHttpClient asyncHttpClient;
    private Context context;
    private static int count = 1;
    private AllHeroBean allHeroBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_hero);
        initToolbar();
        ButterKnife.bind(this);
        context = this;
        getAllHeroList(UrlAddress.all_hero_url);
        initGridview();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("所有英雄");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.titlebar_leftarrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void getAllHeroList(String url) {
        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(context, url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                loadingView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                allHeroBean = JsonUtils.getObject(response, AllHeroBean.class);
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onFinish() {
                super.onFinish();
                loadingView.setVisibility(View.GONE);
                freeHeroGridview.onRefreshComplete();
            }
        });
    }

    private void initGridview() {
        freeHeroGridview.setMode(PullToRefreshBase.Mode.BOTH);

        freeHeroGridview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                count = 1;
                getAllHeroList(UrlAddress.all_hero_url);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                count++;
                if (count <= allHeroBean.getTotal_pages()) {
                    String all_hero_url = "http://spenly.com/lol/search?kws=&cp=" + count + "&limit=20";
                    getAllHeroList(all_hero_url);
                } else {
                    Toast.makeText(context, "全部加载完毕。", Toast.LENGTH_SHORT).show();
                    freeHeroGridview.onRefreshComplete();
                }

            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            allHeroAdapter = new AllHeroAdapter(context, allHeroBean);
            freeHeroGridview.getRefreshableView().setAdapter(allHeroAdapter);
            allHeroAdapter.notifyDataSetChanged();

        }
    };
}

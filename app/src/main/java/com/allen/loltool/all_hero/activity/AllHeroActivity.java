package com.allen.loltool.all_hero.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.allen.loltool.R;
import com.allen.loltool.common.UrlAddress;
import com.allen.loltool.all_hero.adapter.AllHeroAdapter;
import com.allen.loltool.all_hero.bean.AllHeroBean;
import com.allen.loltool.hero_details.activity.HeroDetailsActivity;
import com.allen.loltool.utils.JsonUtils;
import com.allen.loltool.utils.ToastUtils;
import com.allen.loltool.widget.loading.AVLoadingIndicatorView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
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
    private List<AllHeroBean.DataEntity> dataEntities;
    public static Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_hero);
        initToolbar();
        ButterKnife.bind(this);
        initHandler();
        context = AllHeroActivity.this;
        dataEntities = new ArrayList<>();
        initGridview();
        getAllHeroList(UrlAddress.all_hero_url);

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
                //dataEntities = allHeroBean.getData();
                for (AllHeroBean.DataEntity data : allHeroBean.getData()
                        ) {
                    dataEntities.add(data);
                }
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                ToastUtils.showShort(context, "数据加载失败，请稍后再试！");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                Log.d("aaa", "datasize=" + dataEntities.size());
                loadingView.setVisibility(View.GONE);
                freeHeroGridview.onRefreshComplete();


            }
        });
    }

    private void initGridview() {
        allHeroAdapter = new AllHeroAdapter(context, dataEntities);

        freeHeroGridview.setMode(PullToRefreshBase.Mode.BOTH);
        freeHeroGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showShort(context, dataEntities.get(position).getName() + dataEntities.get(position).getId());
                String heroId = dataEntities.get(position).getId();
                String heroName = dataEntities.get(position).getName();
                Intent intent = new Intent(context, HeroDetailsActivity.class);
                intent.putExtra("id",heroId);
                intent.putExtra("name",heroName);
                startActivity(intent);
            }
        });
        freeHeroGridview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                count = 1;
                dataEntities.clear();
                getAllHeroList(UrlAddress.all_hero_url);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                count++;
                if (count <= allHeroBean.getTotal_pages()) {
                    String all_hero_url = "http://spenly.com/lol/heros?kws=&cp=" + count + "&limit=20";
                    getAllHeroList(all_hero_url);
                } else {
                    Toast.makeText(context, "全部加载完毕。", Toast.LENGTH_SHORT).show();
                    freeHeroGridview.onRefreshComplete();
                }

            }
        });
        freeHeroGridview.getRefreshableView().setAdapter(allHeroAdapter);

    }

    private void initHandler() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                allHeroAdapter.notifyDataSetChanged();

            }
        };
    }

}

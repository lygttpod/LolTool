package com.allen.loltool.summoner.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.allen.loltool.R;
import com.allen.loltool.common.UrlAddress;
import com.allen.loltool.summoner.adapter.SummonerAdapter;
import com.allen.loltool.summoner.bean.SummonerBean;
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
 * Created by Allen on 15/11/15.
 */
public class SummonerActivity extends AppCompatActivity {

    @Bind(R.id.free_hero_gridview)
    PullToRefreshGridView freeHeroGridview;
    @Bind(R.id.loadingView)
    AVLoadingIndicatorView loadingView;

    private Context context;

    private SummonerAdapter summonerAdapter;
    private List<SummonerBean.DataEntity> dataEntities;
    private AsyncHttpClient asyncHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_hero);
        ButterKnife.bind(this);
        context = this;
        dataEntities = new ArrayList<>();
        initToolbar();
        initGridview();
        getSummonerList();

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("召唤师技能");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.titlebar_leftarrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initGridview() {
        summonerAdapter = new SummonerAdapter(context, dataEntities);
        freeHeroGridview.getRefreshableView().setAdapter(summonerAdapter);
        freeHeroGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowDespDialog(dataEntities.get(position).getName(), dataEntities
                        .get(position).getDesp());
            }
        });

    }

    /**
     * 获取技能列表数据
     */
    private void getSummonerList() {
        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(context, UrlAddress.summoner_url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                loadingView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                SummonerBean summonerBean = JsonUtils.getObject(response, SummonerBean.class);

                for (SummonerBean.DataEntity data : summonerBean.getData()
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
                summonerAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 显示英雄技能描述
     *
     * @param name
     * @param desp
     */
    private void ShowDespDialog(String name, String desp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(name);
        builder.setMessage(desp);
        builder.setCancelable(false);
        builder.setPositiveButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }
}

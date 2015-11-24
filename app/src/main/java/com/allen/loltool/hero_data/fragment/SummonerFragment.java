package com.allen.loltool.hero_data.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.allen.loltool.R;
import com.allen.loltool.common.UrlAddress;

import com.allen.loltool.hero_data.adapter.SummonerAdapter;
import com.allen.loltool.hero_data.bean.SummonerBean;
import com.allen.loltool.hero_data.bean.SummonerBean.DataEntity;
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
 * Created by Allen on 2015/11/20.
 */
public class SummonerFragment extends Fragment {

    @Bind(R.id.free_hero_gridview)
    PullToRefreshGridView freeHeroGridview;
    @Bind(R.id.loadingView)
    AVLoadingIndicatorView loadingView;
    private Context context;

    private SummonerAdapter summonerAdapter;
    private List<DataEntity> dataEntities;
    private AsyncHttpClient asyncHttpClient;


    public static Fragment newInstance() {
        SummonerFragment summonerFragment = new SummonerFragment();
        return summonerFragment;
    }

    protected boolean isVisible;//判断是否显示

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            if (dataEntities.size() <= 0) {
                getSummonerList();

            }
        } else {
            isVisible = false;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataEntities = new ArrayList<>();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_free_hero, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();
        dataEntities = new ArrayList<>();

        initGridview();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

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

        freeHeroGridview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<GridView>() {
            @Override
            public void onRefresh(PullToRefreshBase<GridView> refreshView) {
                getSummonerList();
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
                dataEntities.clear();
                SummonerBean summonerBean = JsonUtils.getObject(response, SummonerBean.class);

                for (DataEntity data : summonerBean.getData()
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
                freeHeroGridview.onRefreshComplete();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

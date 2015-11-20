package com.allen.loltool.hero_data.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.allen.loltool.R;
import com.allen.loltool.common.UrlAddress;
import com.allen.loltool.hero_data.adapter.FreeHeroAdapter;
import com.allen.loltool.hero_data.bean.FreeHeroBean;
import com.allen.loltool.hero_details.activity.HeroDetailsActivity;
import com.allen.loltool.utils.JsonUtils;
import com.allen.loltool.utils.LogUtil;
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
public class FreeHeroFragment extends Fragment {


    @Bind(R.id.free_hero_gridview)
    PullToRefreshGridView freeHeroGridview;
    @Bind(R.id.loadingView)
    AVLoadingIndicatorView loadingView;


    private FreeHeroAdapter freeHeroAdapter;
    private AsyncHttpClient asyncHttpClient;
    private Context context;
    private List<FreeHeroBean.DataEntity> dataEntities;


    protected boolean isVisible;//判断fragment是否显示

    public static Fragment newInstance() {
        FreeHeroFragment freeHeroFragment = new FreeHeroFragment();
        return freeHeroFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUserVisibleHint(true);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            if (dataEntities.size() <= 0) {
                getFreeHeroList();

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
        initGridview();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initGridview() {
        freeHeroAdapter = new FreeHeroAdapter(context, dataEntities);
        freeHeroGridview.getRefreshableView().setAdapter(freeHeroAdapter);
        freeHeroGridview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<GridView>() {
            @Override
            public void onRefresh(PullToRefreshBase<GridView> refreshView) {
                getFreeHeroList();

            }
        });
        freeHeroGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showShort(context, dataEntities.get(position).getName() + position);
                String heroId = dataEntities.get(position).getId();
                String heroName = dataEntities.get(position).getName();
                Intent intent = new Intent(context, HeroDetailsActivity.class);
                intent.putExtra("id", heroId);
                intent.putExtra("name", heroName);
                startActivity(intent);
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
                dataEntities.clear();
                for (FreeHeroBean.DataEntity data : freeHeroBean.getData()
                        ) {
                    dataEntities.add(data);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.d("spenly--------->", new String(responseBody));
                ToastUtils.showShort(context, "数据加载失败，请稍后再试！");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                loadingView.setVisibility(View.GONE);
                freeHeroGridview.onRefreshComplete();
                freeHeroAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

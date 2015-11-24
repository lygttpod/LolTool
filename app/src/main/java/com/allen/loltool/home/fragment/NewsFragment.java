package com.allen.loltool.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.loltool.R;
import com.allen.loltool.base.BaseFragment;
import com.allen.loltool.common.UrlAddress;
import com.allen.loltool.home.adapter.NewsAdapter;
import com.allen.loltool.home.bean.NewsBean;
import com.allen.loltool.utils.JsonUtils;
import com.allen.loltool.utils.LogUtil;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Allen on 2015/11/24.
 */
public class NewsFragment extends BaseFragment {
    @Bind(R.id.fragment_news_listview)
    PullToRefreshListView fragmentNewsListview;
    private List<NewsBean.ListEntity> listEntities;
    private NewsAdapter newsAdapter;
    private Context context;
    private String url;
    private static final String ARG = "url";

    public static Fragment newInstance(String url) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG, url);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listEntities = new ArrayList<>();
        url = getArguments().getString(ARG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();
        initListview();
        return view;
    }

    private void initListview() {
        newsAdapter = new NewsAdapter(context, listEntities);
        fragmentNewsListview.setAdapter(newsAdapter);
    }

    @Override
    protected void lazyLoad() {
        getNewsList(url);
    }

    private void getNewsList(String url) {
        AsyncHttpClient asyncHttpClient;

        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(getActivity(), UrlAddress.newsbase_url + url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                NewsBean newsBean = JsonUtils.getObject(response, NewsBean.class);
                for (NewsBean.ListEntity list : newsBean.getList()
                        ) {
                    listEntities.add(list);
                }
                LogUtil.d("news = ", response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.d("lol_news_url_onFailure--------->", new String(responseBody));
            }

            @Override
            public void onFinish() {
                super.onFinish();
                newsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

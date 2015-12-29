package com.allen.loltool.news.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.allen.loltool.R;
import com.allen.loltool.common.UrlAddress;
import com.allen.loltool.news.activity.NewsDetailsActivity;
import com.allen.loltool.widget.loading.AVLoadingIndicatorView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen on 2015/11/24.
 */
public class VideoFragment extends Fragment {

    @Bind(R.id.news_video_webview)
    WebView newsVideoWebview;
    @Bind(R.id.loadingView)
    AVLoadingIndicatorView loadingView;
    private Context context;
    private String url = UrlAddress.video_url;
    private static final String ARG = "url";

    public static Fragment newInstance(String url) {
        VideoFragment newsFragment = new VideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG, url);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();
        newsVideoWebview.getSettings().setJavaScriptEnabled(true);
        newsVideoWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //qtpage://news_detail?url=http://lol.qq.com/m/act/a20150319lolapp/exp_3.htm?iVideoId=20225
                String base = "qtpage://news_detail?url=";
                String video_url = url.substring(base.length());
                //view.loadUrl(video_url);
                Intent intent = new Intent();
                intent.setClass(getActivity(), NewsDetailsActivity.class);
                intent.putExtra("article_url", video_url);
                startActivity(intent);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loadingView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loadingView.setVisibility(View.GONE);
            }
        });
        newsVideoWebview.loadUrl(url);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

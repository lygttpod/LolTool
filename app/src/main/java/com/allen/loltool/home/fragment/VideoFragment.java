package com.allen.loltool.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.allen.loltool.R;
import com.allen.loltool.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen on 2015/11/24.
 */
public class VideoFragment extends BaseFragment {

    @Bind(R.id.news_video_webview)
    WebView newsVideoWebview;
    private Context context;
    private String url;
    private static final String ARG = "url";

    public static Fragment newInstance(String url) {
        VideoFragment newsFragment = new VideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG, url);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        url = getArguments().getString(ARG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();
        return view;
    }


    @Override
    protected void lazyLoad() {
        newsVideoWebview.loadUrl(url);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

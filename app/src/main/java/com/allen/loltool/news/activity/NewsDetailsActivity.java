package com.allen.loltool.news.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.allen.loltool.R;
import com.allen.loltool.widget.loading.AVLoadingIndicatorView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen on 2015/11/27.
 */
public class NewsDetailsActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.news_datails_webview)
    WebView newsDatailsWebview;
    @Bind(R.id.loadingView)
    AVLoadingIndicatorView loadingView;

    private String article_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this);
        initToolbar();
        article_url = getIntent().getStringExtra("article_url");
        newsDatailsWebview.getSettings().setJavaScriptEnabled(true);

        newsDatailsWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
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

        if (article_url.startsWith("http")) {
            newsDatailsWebview.loadUrl(article_url);
        } else {
            newsDatailsWebview.loadUrl("http://qt.qq.com/static/pages/news/phone/" + article_url);
        }
    }

    private void initToolbar() {

        toolbar.setTitle("资讯详情");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.titlebar_leftarrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

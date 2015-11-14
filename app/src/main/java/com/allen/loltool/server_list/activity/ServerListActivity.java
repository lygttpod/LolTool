package com.allen.loltool.server_list.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.allen.loltool.R;
import com.allen.loltool.common.UrlAddress;
import com.allen.loltool.server_list.adapter.ServerListAdapter;
import com.allen.loltool.server_list.bean.ServerListBean;
import com.allen.loltool.utils.JsonUtils;
import com.allen.loltool.widget.loading.AVLoadingIndicatorView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;


/**
 * Created by hardy on 2015/11/12.
 */
public class ServerListActivity extends AppCompatActivity {

    @Bind(R.id.server_list_listview)
    PullToRefreshExpandableListView serverListListview;
    @Bind(R.id.loadingView)
    AVLoadingIndicatorView loadingView;

    private AsyncHttpClient asyncHttpClient;
    private Context context;

    private ServerListAdapter serverListAdapter;
    private ServerListBean serverListBean;
    private List<ServerListBean.WTEntity> wtEntities;
    private List<ServerListBean.DXEntity> dxEntities;
    private List<ServerListBean.OtherEntity> otherEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_list);
        initToolbar();
        wtEntities = new LinkedList<>();
        dxEntities = new LinkedList<>();
        otherEntities = new LinkedList<>();
        context = ServerListActivity.this;
        ButterKnife.bind(this);
        initListview();
        getServerList();

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("服务器列表");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.titlebar_leftarrow_back);
        toolbar.setOnMenuItemClickListener(menuItemClickListener);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initListview() {
        serverListListview.getRefreshableView().setGroupIndicator(null);

        serverListAdapter = new ServerListAdapter(context, wtEntities, dxEntities, otherEntities);
        serverListListview.getRefreshableView().setAdapter(serverListAdapter);
        for (int i = 0; i < 3; i++) {
            serverListListview.getRefreshableView().expandGroup(i);

        }
        serverListListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ExpandableListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
                wtEntities.clear();
                dxEntities.clear();
                otherEntities.clear();
                getServerList();
            }
        });


    }

    private void getServerList() {
        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(context, UrlAddress.server_list_url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                loadingView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                serverListBean = JsonUtils.getObject(response, ServerListBean.class);
                for (ServerListBean.WTEntity wt:serverListBean.get网通()) {
                    wtEntities.add(wt);
                }
                for (ServerListBean.DXEntity dx :serverListBean.get电信()
                        ) {
                    dxEntities.add(dx);
                }
                for (ServerListBean.OtherEntity other :serverListBean.get其他()
                        ) {
                    otherEntities.add(other);
                }
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onFinish() {
                super.onFinish();
                loadingView.setVisibility(View.GONE);
                serverListListview.onRefreshComplete();
            }
        });
    }

    private Toolbar.OnMenuItemClickListener menuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.action_server_tips:
                    showTipsDialog();
                    break;
            }
            return true;
        }
    };

    private void showTipsDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(getString(R.string.serverlist_status_title));
        builder.setCancelable(false);
        builder.setMessage(getString(R.string.serverlist_status_tip));
        builder.setPositiveButton("知道啦", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            serverListAdapter.notifyDataSetChanged();
        }
    };
}

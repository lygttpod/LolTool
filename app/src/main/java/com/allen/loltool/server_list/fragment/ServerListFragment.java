package com.allen.loltool.server_list.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.allen.loltool.R;
import com.allen.loltool.base.BaseFragment;
import com.allen.loltool.common.UrlAddress;
import com.allen.loltool.server_list.adapter.ServerListAdapter;
import com.allen.loltool.server_list.bean.ServerListBean;
import com.allen.loltool.utils.JsonUtils;
import com.allen.loltool.utils.ToastUtils;
import com.allen.loltool.widget.loading.AVLoadingIndicatorView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
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
public class ServerListFragment extends BaseFragment {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.loadingView)
    AVLoadingIndicatorView loadingView;
    @Bind(R.id.server_list_listview)
    PullToRefreshExpandableListView serverListListview;

    private AsyncHttpClient asyncHttpClient;
    private Context context;

    private ServerListAdapter serverListAdapter;
    private ServerListBean serverListBean;
    private List<ServerListBean.DataEntity> dataEntities;

    public static Fragment newInstance() {
        ServerListFragment serverListFragment = new ServerListFragment();
        return serverListFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataEntities = new ArrayList<>();
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_server_list, container, false);
        ButterKnife.bind(this, view);
        initListview();
        return view;
    }

    private void initListview() {
        serverListListview.getRefreshableView().setGroupIndicator(null);

        serverListAdapter = new ServerListAdapter(context, dataEntities);
        serverListListview.getRefreshableView().setAdapter(serverListAdapter);
        serverListListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ExpandableListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
                dataEntities.clear();
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
                dataEntities.clear();
                serverListBean = JsonUtils.getObject(response, ServerListBean.class);
                for (ServerListBean.DataEntity data : serverListBean.getData()
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
                loadingView.setVisibility(View.GONE);
                serverListListview.onRefreshComplete();
            }
        });
    }

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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            for (int i = 0; i < dataEntities.size(); i++) {
                serverListListview.getRefreshableView().expandGroup(i);

            }
            serverListAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void lazyLoad() {
        getServerList();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}

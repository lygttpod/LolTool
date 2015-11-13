package com.allen.loltool;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.allen.loltool.all_hero.activity.AllHeroActivity;
import com.allen.loltool.free_hero.activity.FreeHeroActivity;
import com.allen.loltool.server_list.activity.ServerListActivity;
import com.allen.loltool.server_list.bean.ServerListBean;
import com.allen.loltool.utils.JsonUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.button_serverlist)
    Button buttonServerlist;
    @Bind(R.id.button_freehero)
    Button buttonFreehero;
    @Bind(R.id.button_allhero)
    Button buttonAllhero;
    private AsyncHttpClient asyncHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ServerListActivity.class);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        buttonServerlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServerListActivity.class);
                startActivity(intent);
            }
        });
        buttonFreehero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FreeHeroActivity.class);
                startActivity(intent);
            }
        });
        buttonAllhero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllHeroActivity.class);
                startActivity(intent);
            }
        });
    }


    private void getServerList() {
        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(MainActivity.this, "http://spenly.com/lol/server", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                ServerListBean serverListBean = JsonUtils.getObject(response, ServerListBean.class);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}

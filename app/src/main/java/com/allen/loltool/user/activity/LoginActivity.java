package com.allen.loltool.user.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.allen.loltool.R;
import com.easemob.EMCallBack;
import com.easemob.EMError;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.easemob.exceptions.EaseMobException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Allen on 2016/1/6.
 */
public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.username_editText)
    EditText username_et;
    @Bind(R.id.psd_editText)
    EditText psd_et;
    @Bind(R.id.confirm_psd_editText)
    EditText confirmPsd_et;
    @Bind(R.id.login_btn)
    Button loginBtn;
    @Bind(R.id.register_btn)
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(username_et.getText().toString().trim(), psd_et.getText().toString().trim());
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(username_et.getText().toString().trim(), psd_et.getText().toString().trim());
            }
        });
    }

    private void register(final String username, final String pwd) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 调用sdk注册方法
                    EMChatManager.getInstance().createAccountOnServer(username, pwd);
                } catch (EaseMobException e) {
                    e.printStackTrace();
                    int errorCode = e.getErrorCode();
                    if (errorCode ==EMError.NONETWORK_ERROR){
                        Toast.makeText(getApplicationContext(), "网络异常，请检查网络！", Toast.LENGTH_SHORT).show();
                    }else if (errorCode ==EMError.USER_ALREADY_EXISTS){
                        Toast.makeText(getApplicationContext(), "用户已存在！", Toast.LENGTH_SHORT).show();
                    }else if (errorCode ==EMError.UNAUTHORIZED){
                        Toast.makeText(getApplicationContext(), "注册失败，无权限！", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "注册失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }).start();


        }

    /**
     * 登录环信
     * @param username
     * @param pwd
     */
    private void login(final String username, final String pwd) {
        EMChatManager.getInstance().login(username,pwd,new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        EMGroupManager.getInstance().loadAllGroups();
                        EMChatManager.getInstance().loadAllConversations();
                        Log.d("main", "登陆聊天服务器成功！");
                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登陆聊天服务器失败！");
            }
        });

    }
}

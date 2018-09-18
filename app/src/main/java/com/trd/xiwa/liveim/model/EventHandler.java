package com.trd.xiwa.liveim.model;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class EventHandler {
    public static final String USER ="USER123123123";
    public static final String PASSWORD = "123123123";
    private Context contex;

    public EventHandler(Context context) {
        this.contex = context;
    }


    public void onRegist(View view){
        try {
            EMClient.getInstance().createAccount(USER, PASSWORD);//同步方法
        } catch (HyphenateException e) {
            e.printStackTrace();
            Toast.makeText(contex,e.getMessage(),Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(contex,"注册成功,用户名为:"+USER+",密码:"+PASSWORD,Toast.LENGTH_SHORT).show();
    }


    public void onLogin(View view){

        EMClient.getInstance().login(USER,PASSWORD,new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Toast.makeText(contex,"登录聊天服务器成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登录聊天服务器失败！");
                Toast.makeText(contex,"登录聊天服务器成功",Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(contex,"login",Toast.LENGTH_SHORT).show();

    }
}

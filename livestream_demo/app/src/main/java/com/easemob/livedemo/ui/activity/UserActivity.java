package com.easemob.livedemo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.easemob.livedemo.DemoApplication;
import com.easemob.livedemo.R;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        String user = DemoApplication.getInstance().getCurrentUserName();
        Button butn = (Button) findViewById(R.id.btn_click);
        if (user.startsWith("A")) {
            butn.setText("请求直播");
        } else if (user.startsWith("B")) {
            butn.setText("上线");
        } else if (user.startsWith("C")) {
            butn.setText("上线");
        } else if (user.startsWith("E")) {
            butn.setText("上线");
        } else {
            butn.setText("上线");
        }
        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = DemoApplication.getInstance().getCurrentUserName();
                if (user.startsWith("A")) {
                    startLive();
                } else {
                    onlive();
                }
            }
        });
    }

    private void onlive() {

    }

    private void startLive() {

    }
}

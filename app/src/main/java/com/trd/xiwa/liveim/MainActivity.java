package com.trd.xiwa.liveim;

import android.databinding.DataBindingUtil;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trd.xiwa.liveim.databinding.ActivityMainBinding;
import com.trd.xiwa.liveim.model.EventHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        EventHandler handler = new EventHandler(this);
        activityMainBinding.setHandler(handler);
    }
}

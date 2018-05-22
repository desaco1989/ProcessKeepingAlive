package com.jiangdg.aidl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jiangdg.keepappalive.R;

/**
 * https://github.com/hejunlin2013/MultiMediaSample
 */
public class MainServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //com.jiangdg.aidl.MainServiceActivity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_aidl);

        // 启动两个守护服务
        startService(new Intent(this, ServiceA.class));
        startService(new Intent(this, ServiceB.class));
    }
}

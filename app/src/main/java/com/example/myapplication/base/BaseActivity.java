package com.example.myapplication.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.example.myapplication.utils.setStatusBarUtils;

/**
 * author: 小川
 * Date: 2019/5/13
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //透明状态栏
        setStatusBarUtils.newInstance(this).setStatusBarFullTransparent();
//        setStatusBarUtils.newInstance(this).setFitSystemWindow(true);
        setStatusBarUtils.newInstance(this).setDrawerLayoutFitSystemWindow();

        setContentView(initView());
        loadData();
    }

    protected abstract void loadData();

    protected abstract int initView();
}

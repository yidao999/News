package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: 小川
 * Date: 2019/5/19
 * Description:
 */
public class AboutActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Unbinder bind;

    @Override
    protected void loadData() {
        bind = ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("关于作者");
            ab.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int initView() {
        return R.layout.layout_about;
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }
}

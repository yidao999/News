package com.example.myapplication.mvp.mvp_photo.view;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: 小川
 * Date: 2019/5/20
 * Description:
 */
public class PhotoDetailsActivity extends BaseActivity {
    @BindView(R.id.photo_view)
    PhotoView photoView;
    private Unbinder bind;
    private String img_url;

    @Override
    protected void loadData() {
        bind = ButterKnife.bind(this);
        initVariables();
        Glide.with(this)
                .load(img_url)
                .into(photoView);
    }

    private void initVariables() {
        Intent intent = getIntent();
        if (intent.getStringExtra("img_url") != null) {
            img_url = intent.getStringExtra("img_url");
        }
    }

    @Override
    protected int initView() {
        return R.layout.layout_photodetails;
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }
}

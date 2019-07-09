package com.example.myapplication.mvp.mvp_cardtop.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CardTopDetailActivity extends BaseActivity {

    @BindView(R.id.iv_background)
    ImageView iv_background;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.webView)
    WebView webView;
    private String imgurl;
    private String title;
    private String weburl;
    private Unbinder bind;

    @Override
    protected void loadData() {
        bind = ButterKnife.bind(this);
        initVariables();
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
            ab.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        Glide.with(this).load(imgurl).asBitmap()
                .placeholder(R.drawable.android)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .error(R.drawable.android)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv_background);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.getSettings().setJavaScriptEnabled(false);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);
        //   webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.getSettings().setDefaultFontSize(111);
        webView.loadUrl(weburl);

    }

    private void initVariables() {
        Intent intent = getIntent();
        imgurl = intent.getStringExtra("top_pic");
        title = intent.getStringExtra("top_title");
        weburl = intent.getStringExtra("top_weburl");
    }

    @Override
    protected int initView() {
        return R.layout.activity_card_top_detail;
    }


    @OnClick(R.id.fab)
    void OnClick() {
        fab_share();
    }

    private void fab_share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "娱乐TOP");
        intent.putExtra(Intent.EXTRA_TEXT, weburl);
        startActivity(Intent.createChooser(intent, getTitle()));
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
    protected void onDestroy() {
        bind.unbind();
        super.onDestroy();
    }
}

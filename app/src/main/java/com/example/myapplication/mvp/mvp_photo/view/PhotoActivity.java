package com.example.myapplication.mvp.mvp_photo.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.adapter.PhotoAdapter;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.mvp.mvp_photo.model.PhotoModel;
import com.example.myapplication.mvp.mvp_photo.presenter.PhotoPresenter;
import com.example.myapplication.utils.DividerItemDecoration;
import com.melnykov.fab.FloatingActionButton;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author: 小川
 * Date: 2019/5/20
 * Description:
 */
public class PhotoActivity extends BaseActivity implements PhotoView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private Unbinder bind;
    private List<PhotoModel.DataBean> photoList = new ArrayList<>();
    private PhotoPresenter photoPresenter = new PhotoPresenter(this);
    private PhotoAdapter adapter;
    private int mCount = 0;
    private String[] arr = {"长腿", "素颜", "气质", "诱惑", "唯美"};
    private RecyclerView recyclerView;

    @Override
    protected void loadData() {
        bind = ButterKnife.bind(this);
        initToolbar();
        initRecyclerView();

        photoPresenter.loadPhotoData(0, 20, "美女", "性感");
    }

    private void initRecyclerView() {
        recyclerView = pullLoadMoreRecyclerView.getRecyclerView();
        fab.attachToRecyclerView(recyclerView);
        recyclerView.setVerticalScrollBarEnabled(true);
        pullLoadMoreRecyclerView.setStaggeredGridLayout(3);
        pullLoadMoreRecyclerView.setRefreshing(false);
        pullLoadMoreRecyclerView.setPullRefreshEnable(true);
        pullLoadMoreRecyclerView.setFooterViewText("loading");
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        adapter = new PhotoAdapter(this);
        pullLoadMoreRecyclerView.setAdapter(adapter);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("图片");
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }
    }

    @Override
    protected int initView() {
        return R.layout.photo_activity;
    }

    @OnClick(R.id.fab)
    void onclick() {
        pullLoadMoreRecyclerView.scrollToTop();
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

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {

        @Override
        public void onRefresh() {
            setRefresh();
            photoPresenter.loadPhotoData(0, 20, "美女", "性感");
        }

        @Override
        public void onLoadMore() {
            if (mCount < 4) {
                photoPresenter.loadPhotoData(0, 20, "美女", arr[mCount]);
                mCount += 1;
            } else {
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                Snackbar.make(recyclerView.getRootView(), "无数据加载...", Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    private void setRefresh() {
        adapter.clearPhotoData();
        mCount = 0;
    }

    @Override
    public void getPhotoSuccess(PhotoModel photoModel) {
        if (photoModel.getData() != null) {
            photoList.clear();
            photoList.addAll(photoModel.getData());
            adapter.addAllPhotoData(photoList);
            pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        }

    }

    @Override
    public void getPhotoFailure(Throwable e) {
        pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    protected void onDestroy() {
        bind.unbind();
        photoPresenter.detachView();
        super.onDestroy();
    }

}
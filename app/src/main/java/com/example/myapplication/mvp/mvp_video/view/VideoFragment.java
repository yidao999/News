package com.example.myapplication.mvp.mvp_video.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.VideoAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.mvp.mvp_video.model.VideoModel;
import com.example.myapplication.mvp.mvp_video.presenter.VideoPresenter;
import com.example.myapplication.utils.DividerItemDecoration;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: 小川
 * Date: 2019/5/16
 * Description:
 */
public class VideoFragment extends BaseFragment implements VideoView {

    @BindView(R.id.Video_pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView VideoPullLoadMoreRecyclerView;
    private Unbinder bind;
    private RecyclerView recyclerView;
    private VideoPresenter videoPresenter = new VideoPresenter(this);
    private VideoAdapter adapter;

    public static VideoFragment newInstance(String title) {
        VideoFragment fragment = new VideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("msg", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            videoPresenter.loadVideoData();
        }
    }

    @Override
    protected View iniView() {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_video, null, false);
        return view;
    }

    @Override
    protected void iniData() {
        bind = ButterKnife.bind(this, view);

        recyclerView = VideoPullLoadMoreRecyclerView.getRecyclerView();
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setVerticalScrollBarEnabled(true);

        VideoPullLoadMoreRecyclerView.setRefreshing(true);
        VideoPullLoadMoreRecyclerView.setFooterViewText("loading");
        VideoPullLoadMoreRecyclerView.setLinearLayout();
        VideoPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        adapter = new VideoAdapter(context);
        VideoPullLoadMoreRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onGetSuccessVideo(VideoModel videoModel) {
        adapter.addAllData(videoModel.getTrailers());
        VideoPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void onGetErrorVideo(Throwable e) {
        Log.e("tag",e.toString()+"");
        Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
        VideoPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @Override
    public void onDestroy() {
        videoPresenter.detachView();
        super.onDestroy();
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener{

        @Override
        public void onRefresh() {
            setRefresh();
        }

        @Override
        public void onLoadMore() {
            videoPresenter.loadVideoData();
        }
    }

    private void setRefresh() {
        videoPresenter.loadVideoData();
    }

}

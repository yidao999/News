package com.example.myapplication.adapter;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.mvp.mvp_video.model.VideoModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * author: 小川
 * Date: 2019/5/16
 * Description:
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {


    private Context mContext;
    private List<VideoModel.TrailersBean> mVideoList = new ArrayList<>();

    public VideoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addAllData(List<VideoModel.TrailersBean> mVideoList) {
        this.mVideoList.addAll(mVideoList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video, viewGroup, false);
        VideoViewHolder holder = new VideoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder videoViewHolder, int i) {
        JCVideoPlayerStandard jcVideoPlayerStandard = videoViewHolder.jcVideoPlayerStandard;
        VideoModel.TrailersBean data = mVideoList.get(i);
        jcVideoPlayerStandard.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;   //横向
        jcVideoPlayerStandard.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;  //纵向
        jcVideoPlayerStandard.setUp(data.getUrl(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL
                , data.getVideoTitle());
        Glide.with(mContext).load(data.getCoverImg())
                .centerCrop()
                .into(jcVideoPlayerStandard.thumbImageView);

    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.jz_video)
        JCVideoPlayerStandard jcVideoPlayerStandard;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package com.example.myapplication.mvp.mvp_video.view;

import com.example.myapplication.mvp.mvp_video.model.VideoModel;

/**
 * author: 小川
 * Date: 2019/5/16
 * Description:
 */
public interface VideoView {
    void onGetSuccessVideo(VideoModel videoModel);
    void onGetErrorVideo(Throwable e);
}

package com.example.myapplication.mvp.mvp_video.presenter;


import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.mvp.mvp_video.model.VideoModel;
import com.example.myapplication.mvp.mvp_video.view.VideoFragment;
import com.example.myapplication.mvp.mvp_video.view.VideoView;
import com.example.myapplication.retrofit.ApiClient;
import com.example.myapplication.retrofit.ApiService;
import com.example.myapplication.retrofit.HostType;

import rx.Subscriber;

/**
 * author: 小川
 * Date: 2019/5/16
 * Description:
 */
public class VideoPresenter extends BasePresenter<VideoFragment> {

    public VideoPresenter(VideoFragment videoView) {
        attachView(videoView);
        apiStores = ApiClient.retrofit(HostType.BASE_VIDEO).create(ApiService.class);
    }

    public void loadVideoData(){
        addSubscription(apiStores.loadVideoData_RxJava(), new Subscriber<VideoModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mvpView.onGetErrorVideo(e);
            }

            @Override
            public void onNext(VideoModel videoModel) {
                mvpView.onGetSuccessVideo(videoModel);
            }
        });

    }
}

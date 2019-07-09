package com.example.myapplication.mvp.mvp_photo.presenter;

import android.content.Context;
import android.view.View;

import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.mvp.mvp_photo.model.PhotoModel;
import com.example.myapplication.mvp.mvp_photo.view.PhotoView;
import com.example.myapplication.retrofit.ApiClient;
import com.example.myapplication.retrofit.ApiService;
import com.example.myapplication.retrofit.HostType;

import rx.Subscriber;

/**
 * author: 小川
 * Date: 2019/5/20
 * Description:
 */
public class PhotoPresenter extends BasePresenter<PhotoView> {

    public PhotoPresenter(PhotoView view) {
        attachView(view);
        apiStores = ApiClient.retrofit(HostType.GIRL_PHOTO).create(ApiService.class);
    }

    public void loadPhotoData(int page, int number, String type1, String type2){
        addSubscription(apiStores.loadPhotoData_RxJava(page, number, type1, type2), new Subscriber<PhotoModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mvpView.getPhotoFailure(e);
            }

            @Override
            public void onNext(PhotoModel photoModel) {
                mvpView.getPhotoSuccess(photoModel);
            }
        });
    }
}

package com.example.myapplication.mvp.mvp_photo.view;

import com.example.myapplication.mvp.mvp_photo.model.PhotoModel;

/**
 * author: 小川
 * Date: 2019/5/20
 * Description:
 */
public interface PhotoView {
    void getPhotoSuccess(PhotoModel photoModel);
    void getPhotoFailure(Throwable e);
}

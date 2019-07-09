package com.example.myapplication.mvp.mvp_cardtop.view;

import com.example.myapplication.mvp.mvp_cardtop.model.TopModel;

/**
 * author: 小川
 * Date: 2019/5/15
 * Description:
 */
public interface CardTopView {
    void getCardTopSuccess(TopModel topModel);
    void getCardTopFailure(Throwable e);
}

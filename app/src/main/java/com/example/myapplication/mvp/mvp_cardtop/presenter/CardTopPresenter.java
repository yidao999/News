package com.example.myapplication.mvp.mvp_cardtop.presenter;

import android.view.View;

import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.mvp.mvp_cardtop.model.TopModel;
import com.example.myapplication.mvp.mvp_cardtop.view.CardTopFragment;

import rx.Subscriber;

/**
 * author: 小川
 * Date: 2019/5/15
 * Description:
 */
public class CardTopPresenter extends BasePresenter<CardTopFragment> {

    public CardTopPresenter(CardTopFragment view) {
        attachView(view);
    }

    public void loadTopData(String type, String num, int start, String key) {
        addSubscription(apiStores.LoadTopData_RxJava(type, num, start, key), new Subscriber<TopModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mvpView.getCardTopFailure(e);
            }

            @Override
            public void onNext(TopModel topModel) {
                mvpView.getCardTopSuccess(topModel);
            }
        });
    }
}

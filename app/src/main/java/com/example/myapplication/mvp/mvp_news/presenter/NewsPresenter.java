package com.example.myapplication.mvp.mvp_news.presenter;

import android.content.Context;

import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.mvp.mvp_cardtop.model.TopModel;
import com.example.myapplication.mvp.mvp_news.model.NewsModel;
import com.example.myapplication.mvp.mvp_news.view.NewsFragment;

import rx.Subscriber;

/**
 * author: 小川
 * Date: 2019/5/17
 * Description:
 */
public class NewsPresenter extends BasePresenter<NewsFragment> {

    public NewsPresenter(NewsFragment view) {
        attachView(view);
    }

    public void loadNewsData(String type, String num, int start, String key) {
        addSubscription(apiStores.LoadTopData_RxJava(type, num, start, key), new Subscriber<TopModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mvpView.getNewsFailure(e);
            }

            @Override
            public void onNext(TopModel topModel) {
                mvpView.getNewsSuccess(topModel);
            }
        });
    }

}

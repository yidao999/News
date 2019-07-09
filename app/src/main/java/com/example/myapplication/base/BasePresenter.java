package com.example.myapplication.base;



import com.example.myapplication.retrofit.ApiClient;
import com.example.myapplication.retrofit.ApiService;
import com.example.myapplication.retrofit.HostType;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 *
 *
 *
 *
 */
public class BasePresenter<V> {
    public V mvpView;

    protected ApiService apiStores;
    private CompositeSubscription mCompositeSubscription;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
        apiStores = ApiClient.retrofit(HostType.Card_Top).create(ApiService.class);
    }

    public void detachView() {
        this.mvpView = null;
        onUnsubscribe();
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }


    public <T> void addSubscription(Observable<T> observable, Subscriber<T> subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}

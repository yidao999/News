package com.example.myapplication.mvp.mvp_news.view;

import com.example.myapplication.mvp.mvp_cardtop.model.TopModel;
import com.example.myapplication.mvp.mvp_news.model.NewsModel;

/**
 * author: 小川
 * Date: 2019/5/17
 * Description:
 */
public interface NewsView {
    void getNewsSuccess(TopModel topModel);
    void getNewsFailure(Throwable e);
}

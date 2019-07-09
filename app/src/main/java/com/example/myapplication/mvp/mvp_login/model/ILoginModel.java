package com.example.myapplication.mvp.mvp_login.model;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;

/**
 * author: 小川
 * Date: 2019/5/13
 * Description:
 */
public interface ILoginModel {
    void login(String account, String pwd, LoginCallBack callBack);
}

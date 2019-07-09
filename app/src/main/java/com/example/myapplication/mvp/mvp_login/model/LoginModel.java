package com.example.myapplication.mvp.mvp_login.model;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.TextView;

import butterknife.internal.Utils;

/**
 * author: 小川
 * Date: 2019/5/13
 * Description:
 */
public class LoginModel implements ILoginModel {

    @Override
    public void login(String account, String pwd, LoginCallBack callBack) {
        if ("123".equals(account) && "123".equals(pwd)) {
            callBack.loginSuccess();
        } else {
            callBack.loginFailure();
        }
    }
}

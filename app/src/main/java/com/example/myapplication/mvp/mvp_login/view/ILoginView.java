package com.example.myapplication.mvp.mvp_login.view;


import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

/**
 * author: 小川
 * Date: 2019/5/13
 * Description:
 */
public interface ILoginView {
    String getAccount();
    String getPwd();
    TextInputLayout getTil_account();
    TextInputLayout getTil_pwd();
    void loginSuccess(String str);
    void loginFailure(String error);
}

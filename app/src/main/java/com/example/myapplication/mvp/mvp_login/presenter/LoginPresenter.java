package com.example.myapplication.mvp.mvp_login.presenter;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;

import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.mvp.mvp_home.view.HomeActivity;
import com.example.myapplication.mvp.mvp_login.model.ILoginModel;
import com.example.myapplication.mvp.mvp_login.model.LoginCallBack;
import com.example.myapplication.mvp.mvp_login.model.LoginModel;
import com.example.myapplication.mvp.mvp_login.view.LoginActivity;

import okhttp3.internal.framed.Variant;

/**
 * author: 小川
 * Date: 2019/5/13
 * Description:
 */
public class LoginPresenter extends BasePresenter<LoginActivity> {

    private ILoginModel loginModel = new LoginModel();

    public LoginPresenter(LoginActivity view) {
        attachView(view);
    }

    public void login() {
        if (isAccountEmpty()&&isPwdEmpty()) {
        }
        loginModel.login(mvpView.getAccount(), mvpView.getPwd(), new LoginCallBack() {
            @Override
            public void loginSuccess() {
                mvpView.loginSuccess("成功");
                Intent intent = new Intent(mvpView, HomeActivity.class);
                mvpView.startActivity(intent);
                mvpView.finish();
            }

            @Override
            public void loginFailure() {
                mvpView.loginFailure("失败");

            }
        });
    }

    private boolean isAccountEmpty(){
        if(TextUtils.isEmpty(mvpView.getAccount())){
            showError(mvpView.getTil_account(),"账号不能为空");
            return false;
        }
        return true;
    }

    private boolean isPwdEmpty(){
        if(TextUtils.isEmpty(mvpView.getPwd())){
            showError(mvpView.getTil_pwd(),"密码不能为空");
            return false;
        }
        return true;
    }
    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

}

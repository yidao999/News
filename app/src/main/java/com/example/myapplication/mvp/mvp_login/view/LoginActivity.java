package com.example.myapplication.mvp.mvp_login.view;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.mvp.mvp_login.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: 小川
 * Date: 2019/5/13
 * Description:
 */
public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.til_account)
    TextInputLayout til_account;
    @BindView(R.id.til_pwd)
    TextInputLayout til_pwd;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_account)
    EditText et_account;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    private LoginPresenter loginPresenter;

    @Override
    protected void loadData() {
        loginPresenter = new LoginPresenter(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setTitle("登陆");
    }

    @Override
    protected int initView() {
        return R.layout.activity_login;
    }

    @Override
    public String getAccount() {
        return et_account.getText().toString();
    }

    @Override
    public String getPwd() {
        return et_pwd.getText().toString();
    }

    @Override
    public TextInputLayout getTil_account() {
        return til_account;
    }

    @Override
    public TextInputLayout getTil_pwd() {
        return til_pwd;
    }

    @Override
    public void loginSuccess(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        loginPresenter.detachView();
        super.onDestroy();
    }

    @OnClick(R.id.btn_login)
    void onClick() {
        til_account.setErrorEnabled(false);
        til_pwd.setErrorEnabled(false);
        loginPresenter.login();
    }


}

package com.example.zuoye20200319.mvp;

import com.example.zuoye20200319.base.IBasePresenter;
import com.example.zuoye20200319.bean.LoginBean;
import com.example.zuoye20200319.bean.ShowBean;

public class LoginPresenter extends IBasePresenter<LoginContract.LoginView> implements LoginContract.LoginPresenter {

    private LoginModel loginModel;

    @Override
    public void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void login() {
        loginModel.login(new LoginContract.LoginModel.LoginCallBack() {
            @Override
            public void onSuccessJson(ShowBean showBean) {
                view.onSuccessJson(showBean);
            }
            @Override
            public void onFailureJson(String msg) {
                view.onFailureJson(msg);
            }
        });
    }
}

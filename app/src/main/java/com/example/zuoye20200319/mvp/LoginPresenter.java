package com.example.zuoye20200319.mvp;

import com.example.zuoye20200319.base.IBasePresenter;
import com.example.zuoye20200319.bean.LoginBean;

public class LoginPresenter extends IBasePresenter<LoginContract.LoginView> implements LoginContract.LoginPresenter {

    private LoginModel loginModel;

    @Override
    public void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void login(String phone, String pwd) {
        loginModel.login(phone, pwd, new LoginContract.LoginModel.LoginCallBack() {
            @Override
            public void onSuccessJson(LoginBean loginBean) {
                view.onSuccessJson(loginBean);
            }

            @Override
            public void onFailureJson(String msg) {
                view.onFailureJson(msg);
            }
        });
    }
}

package com.example.zuoye20200319.mvp;

import com.example.zuoye20200319.base.IBaseView;
import com.example.zuoye20200319.bean.LoginBean;
import com.example.zuoye20200319.bean.ShowBean;

public interface LoginContract {
    interface LoginView extends IBaseView{
        void onSuccessJson(ShowBean showBean);
        void onFailureJson(String msg);
    }
    interface LoginModel{
        void login(LoginCallBack loginCallBack);
        interface LoginCallBack{
            void onSuccessJson(ShowBean showBean);
            void onFailureJson(String msg);
        }
    }
    interface LoginPresenter{
        void login();
    }
}

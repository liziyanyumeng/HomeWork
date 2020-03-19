package com.example.zuoye20200319.mvp;

import com.example.zuoye20200319.base.IBaseView;
import com.example.zuoye20200319.bean.LoginBean;

public interface LoginContract {
    interface LoginView extends IBaseView{
        void onSuccessJson(LoginBean loginBean);
        void onFailureJson(String msg);
    }
    interface LoginModel{
        void login(String phone,String pwd,LoginCallBack loginCallBack);
        interface LoginCallBack{
            void onSuccessJson(LoginBean loginBean);
            void onFailureJson(String msg);
        }
    }
    interface LoginPresenter{
        void login(String phone,String pwd);
    }
}

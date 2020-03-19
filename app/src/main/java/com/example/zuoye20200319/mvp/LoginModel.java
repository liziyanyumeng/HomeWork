package com.example.zuoye20200319.mvp;

import com.example.zuoye20200319.bean.LoginBean;
import com.example.zuoye20200319.util.ApiService;
import com.example.zuoye20200319.util.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements LoginContract.LoginModel {
    @Override
    public void login(String phone, String pwd, final LoginCallBack loginCallBack) {
        final RetrofitUtil instance = RetrofitUtil.getInstance();
        final ApiService apiService = instance.apiService();
        final Observable<LoginBean> login = apiService.login(phone, pwd);
        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(LoginBean loginBean) {
                        loginCallBack.onSuccessJson(loginBean);
                    }
                    @Override
                    public void onError(Throwable e) {
                        loginCallBack.onFailureJson(e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}

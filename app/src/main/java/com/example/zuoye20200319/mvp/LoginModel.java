package com.example.zuoye20200319.mvp;

import android.widget.Toast;

import com.example.zuoye20200319.MainActivity;
import com.example.zuoye20200319.bean.LoginBean;
import com.example.zuoye20200319.bean.ShowBean;
import com.example.zuoye20200319.util.ApiService;
import com.example.zuoye20200319.util.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginModel implements LoginContract.LoginModel {
    @Override
    public void login(final LoginCallBack loginCallBack) {
        final RetrofitUtil instance = RetrofitUtil.getInstance();
        final ApiService apiService = instance.apiService();
        final Observable<ShowBean> show = apiService.show();
        show.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShowBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ShowBean showBean) {
                        loginCallBack.onSuccessJson(showBean);
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

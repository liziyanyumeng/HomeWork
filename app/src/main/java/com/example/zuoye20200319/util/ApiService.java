package com.example.zuoye20200319.util;

import com.example.zuoye20200319.bean.ImageBean;
import com.example.zuoye20200319.bean.LoginBean;
import com.example.zuoye20200319.bean.ShowBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface ApiService {
    @POST("user/v1/login")
    @FormUrlEncoded
    Observable<LoginBean> login(@Field("phone")String phone,@Field("pwd")String pwd);

    @GET("commodity/v1/bannerShow")
    Observable<ShowBean> show();

}

package com.example.zuoye20200319;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zuoye20200319.bean.LoginBean;
import com.example.zuoye20200319.bean.ShowBean;
import com.example.zuoye20200319.util.ApiService;
import com.example.zuoye20200319.util.RetrofitUtil;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.iwf.photopicker.PhotoPicker;

public class MainActivity extends AppCompatActivity {

    private Button btnGet;
    private Button btnPost;
    private Button btnUpImg;
    private EditText tvPwd;
    private EditText tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tvPwd = (EditText) findViewById(R.id.tv_pwd);
        tvResult = (EditText) findViewById(R.id.tv_result);
        btnGet = (Button) findViewById(R.id.btn_get);
        btnPost = (Button) findViewById(R.id.btn_post);
        btnUpImg = (Button) findViewById(R.id.btn_up_img);
        clickget();
    }

    //设置点击事件
    public void clickget() {
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onComplete() {
                            }
                        });
            }
        });
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RetrofitUtil instance = RetrofitUtil.getInstance();
                final ApiService apiService = instance.apiService();
                final String phone = tvResult.getText().toString();
                final String pwd = tvPwd.getText().toString();
                final Observable<LoginBean> login = apiService.login(phone, pwd);
                login.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<LoginBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                            }
                            @Override
                            public void onNext(LoginBean loginBean) {
                                Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onComplete() {
                            }
                        });
            }
        });
        btnUpImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(false);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
        }
    }
}

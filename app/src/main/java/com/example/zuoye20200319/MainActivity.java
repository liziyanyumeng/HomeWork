package com.example.zuoye20200319;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zuoye20200319.bean.LoginBean;
import com.example.zuoye20200319.bean.ShowBean;
import com.example.zuoye20200319.util.ApiService;
import com.example.zuoye20200319.util.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private TextView mTvResult;
    private Button mBtnGet, mBtnPost, mBtnUp;
    private TextView tvShow;
    private EditText tvResult;
    private EditText tvPwd;
    private Button btnGet;
    private Button btnPost;
    private Button btnUpImg;
    private String phone;
    private String pwcd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tvShow = (TextView) findViewById(R.id.tv_show);
        tvResult = (EditText) findViewById(R.id.tv_result);
        tvPwd = (EditText) findViewById(R.id.tv_pwd);
        btnGet = (Button) findViewById(R.id.btn_get);
        btnPost = (Button) findViewById(R.id.btn_post);
        btnUpImg = (Button) findViewById(R.id.btn_up_img);
        jisuan();
    }

    public void jisuan(){
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
                phone = tvResult.getText().toString();
                pwcd = tvPwd.getText().toString();
                final Observable<LoginBean> login = apiService.login(phone, pwcd);
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
    }
}

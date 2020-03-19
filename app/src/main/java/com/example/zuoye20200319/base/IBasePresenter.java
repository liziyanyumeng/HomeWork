package com.example.zuoye20200319.base;

public abstract class IBasePresenter<View> {
    public View view;

    public IBasePresenter() {
        initModel();
    }

    public void attachView(View view){
        this.view = view;
    }
    public void detachView(){
        if (view !=null) {
            view = null;
        }
    }
    public abstract void initModel();
}

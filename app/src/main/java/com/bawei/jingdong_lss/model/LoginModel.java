package com.bawei.jingdong_lss.model;


import com.bawei.jingdong_lss.bean.LoginBean;
import com.bawei.jingdong_lss.net.OnNetListener;
import com.bawei.jingdong_lss.net.RetrofitHelper;
import com.bawei.jingdong_lss.net.ServerApi;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginModel implements ILoginModel {
    public void getLogin(final OnNetListener<LoginBean> onNetListener, String phone, String pass){
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.login(phone,pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        onNetListener.onSuccess(loginBean);
                    }
                });
    }
}

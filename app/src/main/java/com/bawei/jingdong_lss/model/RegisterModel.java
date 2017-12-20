package com.bawei.jingdong_lss.model;


import com.bawei.jingdong_lss.bean.RegisterBean;
import com.bawei.jingdong_lss.net.OnNetListener;
import com.bawei.jingdong_lss.net.RetrofitHelper;
import com.bawei.jingdong_lss.net.ServerApi;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterModel implements IRegisterModel {
    @Override
    public void getRegister(final OnNetListener<RegisterBean> onNetListener, String phone, String pass) {
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.regist(phone,pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegisterBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        onNetListener.onSuccess(registerBean);
                    }
                });
    }
}

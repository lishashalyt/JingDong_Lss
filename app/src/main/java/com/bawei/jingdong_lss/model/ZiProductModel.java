package com.bawei.jingdong_lss.model;

import com.bawei.jingdong_lss.bean.ZiProduct;
import com.bawei.jingdong_lss.net.OnNetListener;
import com.bawei.jingdong_lss.net.RetrofitHelper;
import com.bawei.jingdong_lss.net.ServerApi;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ZiProductModel implements IZiProductModel {
    @Override
    public void getZiProduct(final OnNetListener<ZiProduct> onNetListener, String pid) {
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.ziproduct(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZiProduct>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(ZiProduct ziProduct) {
                        onNetListener.onSuccess(ziProduct);
                    }
                });
    }
}

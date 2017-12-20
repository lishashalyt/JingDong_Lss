package com.bawei.jingdong_lss.model;


import com.bawei.jingdong_lss.bean.CartBean;
import com.bawei.jingdong_lss.net.OnNetListener;
import com.bawei.jingdong_lss.net.RetrofitHelper;
import com.bawei.jingdong_lss.net.ServerApi;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by  on 2017/12/17.
 * 购物车请求数据
 */

public class GouModel implements IGouModel {
    @Override
    public void getCart(String cid, String token,final OnNetListener<CartBean> onNetListener) {
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.cart(cid,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CartBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(CartBean cartBean) {
                        onNetListener.onSuccess(cartBean);
                    }
                });
    }
}

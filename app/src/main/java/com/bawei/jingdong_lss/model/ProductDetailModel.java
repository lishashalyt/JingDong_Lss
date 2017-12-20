package com.bawei.jingdong_lss.model;


import com.bawei.jingdong_lss.bean.AddCartBean;
import com.bawei.jingdong_lss.bean.ProductDetailBean;
import com.bawei.jingdong_lss.net.OnNetListener;
import com.bawei.jingdong_lss.net.RetrofitHelper;
import com.bawei.jingdong_lss.net.ServerApi;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



public class ProductDetailModel implements IProductDetailModel {
    public void getDetail(final OnNetListener<ProductDetailBean> onNetListener, String pid){
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.detail(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProductDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(ProductDetailBean detailBean) {
                        onNetListener.onSuccess(detailBean);
                    }
                });
    }

    @Override
    public void getAddCart(String cid, String pid, final OnNetListener<AddCartBean> onNetListener) {
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.addCart(cid,pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddCartBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(AddCartBean addCartBean) {
                        onNetListener.onSuccess(addCartBean);
                    }
                });
    }
}

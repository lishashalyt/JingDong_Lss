package com.bawei.jingdong_lss.model;

import com.bawei.jingdong_lss.bean.GridData;
import com.bawei.jingdong_lss.bean.ProductBean;
import com.bawei.jingdong_lss.net.OnNetListener;
import com.bawei.jingdong_lss.net.RetrofitHelper;
import com.bawei.jingdong_lss.net.ServerApi;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



public class FenModel implements IFenModel {
    //左侧
    @Override
    public void getList(final OnNetListener<GridData> onNetListener) {
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.grid()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GridData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(GridData gridBean) {
                        onNetListener.onSuccess(gridBean);
                    }
                });
    }

    //右侧
    @Override
    public void getProduct(final OnNetListener<ProductBean> onNetListener, String cid) {
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.product(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProductBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();
                        onNetListener.onFailure((Exception) e);
                    }

                    @Override
                    public void onNext(ProductBean productBean) {
                        onNetListener.onSuccess(productBean);
                    }
                });
    }
}

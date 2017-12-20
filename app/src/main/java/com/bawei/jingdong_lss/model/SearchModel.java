package com.bawei.jingdong_lss.model;

import com.bawei.jingdong_lss.bean.SearchListBean;
import com.bawei.jingdong_lss.net.OnNetListener;
import com.bawei.jingdong_lss.net.RetrofitHelper;
import com.bawei.jingdong_lss.net.ServerApi;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class SearchModel implements ISearchModel {
    @Override
    public void onShowSearch(String name, final OnNetListener<SearchListBean> onNetlistener) {
        ServerApi stringApi = RetrofitHelper.getServerApi();
        stringApi.searchList(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchListBean searchListBean) {
                        onNetlistener.onSuccess(searchListBean);
                    }
                });
    }


}

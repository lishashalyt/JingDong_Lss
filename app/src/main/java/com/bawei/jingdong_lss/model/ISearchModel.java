package com.bawei.jingdong_lss.model;

import com.bawei.jingdong_lss.bean.SearchListBean;
import com.bawei.jingdong_lss.net.OnNetListener;

public interface ISearchModel {
    public void onShowSearch(String name, OnNetListener<SearchListBean> onNetlistener);
}

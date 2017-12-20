package com.bawei.jingdong_lss.model;


import com.bawei.jingdong_lss.bean.CartBean;
import com.bawei.jingdong_lss.net.OnNetListener;

/**
 * Created by  on 2017/12/17.
 */

public interface IGouModel {
    public void getCart(String cid, String token, OnNetListener<CartBean> onNetListener);
}
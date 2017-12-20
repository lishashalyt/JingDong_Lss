package com.bawei.jingdong_lss.model;


import com.bawei.jingdong_lss.bean.AddCartBean;
import com.bawei.jingdong_lss.bean.ProductDetailBean;
import com.bawei.jingdong_lss.net.OnNetListener;



public interface IProductDetailModel {
    public void getDetail(OnNetListener<ProductDetailBean> onNetListener, String pid);
    public void getAddCart(String cid, String pid,OnNetListener<AddCartBean> onNetListener);
}

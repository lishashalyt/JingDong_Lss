package com.bawei.jingdong_lss.model;


import com.bawei.jingdong_lss.bean.ZiProduct;
import com.bawei.jingdong_lss.net.OnNetListener;

public interface IZiProductModel {
    public void getZiProduct(OnNetListener<ZiProduct> onNetListener, String pid);
}

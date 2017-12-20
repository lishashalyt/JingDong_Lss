package com.bawei.jingdong_lss.model;

import com.bawei.jingdong_lss.bean.GridData;
import com.bawei.jingdong_lss.bean.ProductBean;
import com.bawei.jingdong_lss.net.OnNetListener;



public interface IFenModel {
    public void getList(OnNetListener<GridData> onNetListener);
    public void getProduct(OnNetListener<ProductBean> onNetListener, String cid);
}

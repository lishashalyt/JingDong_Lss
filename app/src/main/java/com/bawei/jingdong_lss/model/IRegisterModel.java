package com.bawei.jingdong_lss.model;


import com.bawei.jingdong_lss.bean.RegisterBean;
import com.bawei.jingdong_lss.net.OnNetListener;

public interface IRegisterModel {
    public void getRegister(final OnNetListener<RegisterBean> onNetListener, String phone, String pass);
}

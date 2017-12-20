package com.bawei.jingdong_lss.model;

import com.bawei.jingdong_lss.bean.LoginBean;
import com.bawei.jingdong_lss.net.OnNetListener;

/**
 * Created by love_mysunshine on 2017/12/16.
 */

public interface ILoginModel {
    public void getLogin(final OnNetListener<LoginBean> onNetListener, String phone, String pass);
}

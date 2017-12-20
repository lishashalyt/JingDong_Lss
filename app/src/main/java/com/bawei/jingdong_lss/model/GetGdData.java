package com.bawei.jingdong_lss.model;

import com.bawei.jingdong_lss.okhttp.OkHttpUtils;

import okhttp3.Callback;

/**
 * Created by love_mysunshine on 2017/12/13.
 */

public class GetGdData implements IGetGdData{
    @Override
    public void getGdData(Callback callback) {
        OkHttpUtils.getInstance().doGet("https://www.zhaoapi.cn/product/getCatagory",callback);
    }
}

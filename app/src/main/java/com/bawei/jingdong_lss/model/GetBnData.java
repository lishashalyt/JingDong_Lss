package com.bawei.jingdong_lss.model;

import com.bawei.jingdong_lss.okhttp.OkHttpUtils;

import okhttp3.Callback;

/**
 * Created by love_mysunshine on 2017/12/12.
 */

public class GetBnData implements IGetBnData{
    @Override
    public void getBNData(Callback callback) {
         /*用OkHttpUtils的doGet请求*/
        OkHttpUtils.getInstance().doGet("https://www.zhaoapi.cn/ad/getAd",callback);
    }
}

package com.bawei.jingdong_lss.utils;

import android.app.Application;
import android.content.SharedPreferences;

import com.facebook.drawee.backends.pipeline.Fresco;


public class Myapp extends Application{
    public static SharedPreferences sp;
    public static SharedPreferences.Editor edit;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        sp = getSharedPreferences("user", MODE_PRIVATE);
        edit = sp.edit();
    }
}

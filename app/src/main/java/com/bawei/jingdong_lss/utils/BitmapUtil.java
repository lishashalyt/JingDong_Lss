package com.bawei.jingdong_lss.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/*展示所有图片类*/
public class BitmapUtil {
    String uri;
    ImageView ls_iv;
    public void getpic(String uri,ImageView ls_iv){
        this.uri=uri;
        this.ls_iv=ls_iv;
        new Thread(runnable).start();
    }
    Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            ls_iv.setImageBitmap((Bitmap) msg.obj);
        };
    };
    Runnable runnable=new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub

            try {
                URL url = new URL(uri);
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                InputStream is = connection.getInputStream();
                Bitmap bitmap= BitmapFactory.decodeStream(is);
                Message msg = new Message();
                msg.obj=bitmap;
                msg.what=0;
                handler.sendMessage(msg);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    };
}



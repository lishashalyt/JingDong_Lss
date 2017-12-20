package com.bawei.jingdong_lss;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mWelcome;

    private ImageView mwelcome;
    private Handler handler;
    int time=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        /*倒计时*/
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                time--;
                if(time==0){
                    Intent intent = new Intent(MainActivity.this,TowActivity.class);
                    startActivity(intent);
                    return;
                }
                handler.postDelayed(this,1000);
            }
        },1000);
    }

    private void initView() {
        mWelcome = (ImageView) findViewById(R.id.welcome);
    }
}

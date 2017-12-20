package com.bawei.jingdong_lss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private ImageView mJieTu;
    private TextView mJieZi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();

    }

    private void initView() {
        mJieTu = (ImageView) findViewById(R.id.jie_tu);
        mJieZi = (TextView) findViewById(R.id.jie_zi);
    }
}

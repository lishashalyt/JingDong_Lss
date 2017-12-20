package com.bawei.jingdong_lss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView mBack;
    private EditText mEdName;
    /**
     * 搜索
     */
    private TextView mSousuo;

    private ListView mLv;
    /**
     * 清空历史记录
     */
    private Button mBtn;
    private LinearLayout mLsjl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEdName.getText().toString().trim();
                if(name==null||name.length()==0){
                    Toast.makeText(SearchActivity.this,"输入搜索内容",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent3 = new Intent(SearchActivity.this,SearachListActivity.class);
                intent3.putExtra("name", name);
                startActivity(intent3);
            }
        });
    }


    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mEdName = (EditText) findViewById(R.id.ed_name);
        mSousuo = (TextView) findViewById(R.id.sousuo);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}

package com.bawei.jingdong_lss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.jingdong_lss.adapter.MySearchAdapter;
import com.bawei.jingdong_lss.bean.SearchListBean;
import com.bawei.jingdong_lss.presenter.SearchPresenter;
import com.bawei.jingdong_lss.view.SearchView;

import java.util.List;

public class SearachListActivity extends AppCompatActivity implements SearchView{

    private ImageView mBack;
    private EditText mImg;
    private ImageView mQiehuan;
    private RecyclerView mRv;

    public SearchPresenter searchPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searach_list);
        initView();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        searchPresenter = new SearchPresenter(this);
        searchPresenter.OnShowSearch(name);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mImg = (EditText) findViewById(R.id.img);
        mQiehuan = (ImageView) findViewById(R.id.qiehuan);
        mRv = (RecyclerView) findViewById(R.id.rv);
    }

    @Override
    public void onShowSearch(List<SearchListBean.DataBean> listBeans) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(linearLayoutManager);
        MySearchAdapter mySearchAdapter = new MySearchAdapter(listBeans, this);
        mRv.setAdapter(mySearchAdapter);

    }
}

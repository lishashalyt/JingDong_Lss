package com.bawei.jingdong_lss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bawei.jingdong_lss.adapter.ZiProductAdapter;
import com.bawei.jingdong_lss.bean.ZiProduct;
import com.bawei.jingdong_lss.presenter.ZiProductPresenter;
import com.bawei.jingdong_lss.view.IZiProductActivity;

import java.util.List;

public class ZiProductActivity extends AppCompatActivity implements IZiProductActivity {
    private RecyclerView mPdRv;
    private ZiProductPresenter ziProductPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zi_product);
        initView();
        ActionBar bar = getSupportActionBar();
        bar.hide();
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        Log.e("PID",pid);
        ziProductPresenter = new ZiProductPresenter(this);
        ziProductPresenter.setZiProduct(pid);
        mPdRv.setLayoutManager(new LinearLayoutManager(this));
    }
    private void initView() {
        mPdRv = findViewById(R.id.zi_rv);
    }
    @Override
    public void shouZIProduct(ZiProduct ziProduct) {
        List<ZiProduct.DataBean> data = ziProduct.getData();
        ZiProductAdapter adapter = new ZiProductAdapter(this, data);
        mPdRv.setAdapter(adapter);
    }
}

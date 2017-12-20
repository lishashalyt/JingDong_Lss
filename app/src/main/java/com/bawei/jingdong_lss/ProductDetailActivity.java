package com.bawei.jingdong_lss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.jingdong_lss.adapter.DetailAdapter;
import com.bawei.jingdong_lss.bean.AddCartBean;
import com.bawei.jingdong_lss.bean.ProductDetailBean;
import com.bawei.jingdong_lss.presenter.ProductDetailPresenter;
import com.bawei.jingdong_lss.utils.Myapp;
import com.bawei.jingdong_lss.view.IProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

/*
* 商品详情
* */
public class ProductDetailActivity extends AppCompatActivity implements IProductDetailActivity {

    private ProductDetailPresenter productDetailPresenter;
    private RecyclerView mPdRv;
    private Button mAddcar;
    private List<ProductDetailBean.DataBean>  list;
    private TextView add;
    private int uid;
    private String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initView();
        ActionBar bar = getSupportActionBar();
        bar.hide();
        productDetailPresenter = new ProductDetailPresenter(this);
        list = new ArrayList<>();
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        Log.e("PID",pid+"");
        productDetailPresenter.setDetail(pid);
        mPdRv.setLayoutManager(new LinearLayoutManager(this));
        uid = Myapp.sp.getInt("uid", 0);
        productDetailPresenter.setAddCart(uid+"",pid);
        mAddcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent();
                intent4.setClass(ProductDetailActivity.this, TowActivity.class);
                intent4.putExtra("tag",3);
                startActivity(intent4);
            }
        });
    }

    private void initView() {
        mPdRv = findViewById(R.id.de_rv);
        mAddcar = findViewById(R.id.addcar);

    }
    @Override
    public void shouDetail(ProductDetailBean detailBean) {
        ProductDetailBean.DataBean data = detailBean.getData();
        list.add(data);
        DetailAdapter adapter = new DetailAdapter(this, list);
        mPdRv.setAdapter(adapter);
    }

    @Override
    public void shouAddCart(final AddCartBean addCartBean) {
        final String code = addCartBean.getCode();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (code.equals("0")){
                    Toast.makeText(ProductDetailActivity.this,addCartBean.getMsg()+"",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProductDetailActivity.this,addCartBean.getMsg()+"",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

package com.bawei.jingdong_lss.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.jingdong_lss.R;
import com.bawei.jingdong_lss.bean.ProductDetailBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;



public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ProductDetailBean.DataBean>  list;

    public DetailAdapter(Context context, List<ProductDetailBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.detail, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        ProductDetailBean.DataBean bean = list.get(position);
        String images = bean.getImages();
        String[] split = images.split("!");
        holder1.sdv.setImageURI(split[0]);
        holder1.name.setText(bean.getSubhead());
        holder1.price.setText("￥"+bean.getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView sdv;
        TextView name;
        TextView price;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.de_sdv);
            name = itemView.findViewById(R.id.de_tv1);
            price = itemView.findViewById(R.id.de_price);
        }
    }
}

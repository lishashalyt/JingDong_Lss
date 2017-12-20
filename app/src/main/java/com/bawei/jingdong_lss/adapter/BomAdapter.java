package com.bawei.jingdong_lss.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.jingdong_lss.R;
import com.bawei.jingdong_lss.ZiProductActivity;
import com.bawei.jingdong_lss.bean.ProductBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class BomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ProductBean.DataBean.ListBean> list;

    public BomAdapter(Context context, List<ProductBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.right_rv_item02, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder1 = (MyViewHolder) holder;
        final ProductBean.DataBean.ListBean listBean = list.get(position);
        holder1.sdv.setImageURI(listBean.getIcon());
        holder1.tv.setText(listBean.getName());
        holder1.sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ZiProductActivity.class);
                intent.putExtra("pid",list.get(position).getPcid()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list== null) {
            return 0;
        }
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView sdv;
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            sdv = itemView.findViewById(R.id.tu);
            tv = itemView.findViewById(R.id.zi);
        }
    }
}

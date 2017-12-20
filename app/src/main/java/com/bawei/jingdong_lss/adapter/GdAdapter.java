package com.bawei.jingdong_lss.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.jingdong_lss.R;
import com.bawei.jingdong_lss.bean.GridData;
import com.bawei.jingdong_lss.utils.BitmapUtil;

import java.util.List;

/**
 * Created by love_mysunshine on 2017/12/13.
 */

public class GdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<GridData.DataBean> gdlist;

    public GdAdapter(Context context, List<GridData.DataBean> gdlist) {
        this.context = context;
        this.gdlist = gdlist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View gdview=View.inflate(context, R.layout.gd_item,null);
        RecyclerView.ViewHolder viewholder=new RvGdViewHolder(gdview);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RvGdViewHolder gridholder= (RvGdViewHolder) holder;
        new BitmapUtil().getpic(gdlist.get(position).getIcon(),gridholder.gd_iv);
        gridholder.gd_tv.setText(gdlist.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return gdlist.size();
    }
    class RvGdViewHolder extends RecyclerView.ViewHolder{
        public ImageView gd_iv;
        public TextView gd_tv;

        public RvGdViewHolder(View itemView) {
            super(itemView);
            gd_iv=itemView.findViewById(R.id.gd_iv);
            gd_tv=itemView.findViewById(R.id.gd_tv);
        }
    }
}

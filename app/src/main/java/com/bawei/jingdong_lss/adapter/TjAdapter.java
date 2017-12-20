package com.bawei.jingdong_lss.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.jingdong_lss.R;
import com.bawei.jingdong_lss.bean.BnData;
import com.bawei.jingdong_lss.utils.BitmapUtil;

import java.util.List;

/**
 * Created by love_mysunshine on 2017/12/13.
 */

public class TjAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<BnData.TuijianBean.ListBean> tjlist;

    public TjAdapter(Context context, List<BnData.TuijianBean.ListBean> tjlist) {
        this.context = context;
        this.tjlist = tjlist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View tjview=View.inflate(context, R.layout.tj_item,null);
        RecyclerView.ViewHolder viewholder=new RvHaohuoViewHolder(tjview);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        RvHaohuoViewHolder gridholder= (RvHaohuoViewHolder) holder;
        new BitmapUtil().getpic(tjlist.get(position).getImages(),gridholder.haohuo_iv);
        gridholder.haohuo_tv.setText(tjlist.get(position).getSubhead());

    }

    @Override
    public int getItemCount() {
        return tjlist.size();
    }
    class RvHaohuoViewHolder extends RecyclerView.ViewHolder{
        public ImageView haohuo_iv;
        public TextView haohuo_tv;

        public RvHaohuoViewHolder(View itemView) {
            super(itemView);
            haohuo_iv=itemView.findViewById(R.id.tj_iv);
            haohuo_tv=itemView.findViewById(R.id.tj_tv);
        }
    }
}

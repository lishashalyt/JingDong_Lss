package com.bawei.jingdong_lss.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bawei.jingdong_lss.R;
import com.bawei.jingdong_lss.bean.GridData;

import java.util.List;



public class LeftAdapter extends BaseAdapter {
    private Context context;
    private List<GridData.DataBean> list;

    public LeftAdapter(Context context, List<GridData.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder;
        if (view == null){
            holder = new MyViewHolder();
            view = View.inflate(context, R.layout.left_item,null);
            holder.tv = view.findViewById(R.id.left_tv);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        GridData.DataBean dataBean = list.get(i);
        holder.tv.setText(dataBean.getName());
        return view;
    }
    class MyViewHolder{
        TextView tv;
    }
}

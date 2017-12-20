package com.bawei.jingdong_lss.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.jingdong_lss.R;
import com.bawei.jingdong_lss.bean.CartBean;
import com.bawei.jingdong_lss.eventbusevent.MessageEvent;
import com.bawei.jingdong_lss.eventbusevent.PriceAndCountEvent;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by love_mysunshine on 2017/12/17.
 * 购物车适配器
 */

public class CartAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<CartBean.DataBean> group;
    private List<List<CartBean.DataBean.ListBean>> child;

    public CartAdapter(Context context, List<CartBean.DataBean> group, List<List<CartBean.DataBean.ListBean>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return child.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return group.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return child.get(i).get(i);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        final GroupViewHolder holder;
        if (view == null){
            holder = new GroupViewHolder();
            view = View.inflate(context, R.layout.gou_group,null);
            holder.cb_group = view.findViewById(R.id.cb_group);
            holder.tv_dian = view.findViewById(R.id.gou_dian);
            view.setTag(holder);
        } else {
            holder = (GroupViewHolder) view.getTag();
        }
        final CartBean.DataBean bean = group.get(i);
        holder.cb_group.setChecked(bean.isCheck());
        holder.tv_dian.setText(bean.getSellerName());
        holder.cb_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bean.setCheck(holder.cb_group.isChecked());
                changeChildCbState(i,holder.cb_group.isChecked());
                EventBus.getDefault().post(computer());
                changeAllCbState(isAllGroupCbSelected());
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        final ChildViewHolder holder;
        if (view == null){
            holder = new ChildViewHolder();
            view = View.inflate(context,R.layout.gou_child,null);
            holder.cb_child = view.findViewById(R.id.cb_child);
            holder.sdv = view.findViewById(R.id.gou_sdv);
            holder.tv_tit = view.findViewById(R.id.gou_tit);
            holder.tv_info = view.findViewById(R.id.gou_info);
            holder.tv_price = view.findViewById(R.id.gou_price);
            holder.tv_jian = view.findViewById(R.id.gou_jian);
            holder.tv_num = view.findViewById(R.id.gou_num);
            holder.tv_jia = view.findViewById(R.id.gou_jia);
            holder.del = view.findViewById(R.id.del);
            view.setTag(holder);
        } else {
            holder = (ChildViewHolder) view.getTag();
        }
        final CartBean.DataBean.ListBean bean = child.get(i).get(i1);
        holder.cb_child.setChecked(bean.isCheck());
        String[] img = bean.getImages().split("\\|");
        holder.sdv.setImageURI(img[0]);
        holder.tv_tit.setText(bean.getSubhead());
        holder.tv_info.setText(bean.getTitle());
        holder.tv_price.setText("￥:"+bean.getPrice());
        holder.tv_num.setText(bean.getNum()+"");
        //给二级CheckBox设置点击事件
        holder.cb_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置该条目对象里面的CheckBox属性值
                bean.setCheck(holder.cb_child.isChecked());
                EventBus.getDefault().post(computer());
                if (holder.cb_child.isChecked()){
                    //当前CheckBox是选中状态
                    if (isAllChildCbSelected(i)){
                        changeGroupCbState(i,true);
                        changeAllCbState(isAllGroupCbSelected());
                    }
                } else {
                    //取消选中
                    changeGroupCbState(i,false);
                    changeAllCbState(isAllGroupCbSelected());
                }
                notifyDataSetChanged();
            }
        });
        //加号-数量加
        holder.tv_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = bean.getNum();
                holder.tv_num.setText(++num+"");
                bean.setNum(num);
                if (holder.cb_child.isChecked()){
                    EventBus.getDefault().post(computer());
                }
            }
        });
        //减号-数量减
        holder.tv_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = bean.getNum();
                if (num==1){
                    return;
                }
                holder.tv_num.setText(--num+"");
                bean.setNum(num);
                if (holder.cb_child.isChecked()){
                    EventBus.getDefault().post(computer());
                }
            }
        });
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CartBean.DataBean.ListBean> list = child.get(i);
                CartBean.DataBean.ListBean remove = list.remove(i1);
                if (list.size() == 0) {
                    child.remove(i);
                    group.remove(i);
                }
                EventBus.getDefault().post(computer());
                notifyDataSetChanged();
            }
        });
        return view;
    }
    //一级
    class GroupViewHolder{
        CheckBox cb_group;
        TextView tv_dian;
    }
    //二级
    class ChildViewHolder{
        CheckBox cb_child;
        SimpleDraweeView sdv;
        TextView tv_tit;
        TextView tv_info;
        TextView tv_price;
        TextView tv_jian;
        TextView tv_num;
        TextView tv_jia;
        Button del;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    //改变全选CheckBox状态
    private void changeAllCbState(boolean flag){
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setChecked(flag);
        EventBus.getDefault().post(messageEvent);
    }
    //改变二级列表CheckBox状态
    private void changeChildCbState(int groupPosition,boolean flag){
        List<CartBean.DataBean.ListBean> datasBeen = child.get(groupPosition);
        for (int i = 0; i < datasBeen.size(); i++) {
            CartBean.DataBean.ListBean datasBean = datasBeen.get(i);
            datasBean.setCheck(flag);
        }
    }
    //改变一级列表CheckBox状态
    private void changeGroupCbState(int groupPosition,boolean flag){
        CartBean.DataBean dataBean = group.get(groupPosition);
        dataBean.setCheck(flag);
    }
    //判断一级列表是否全部选中
    public boolean isAllGroupCbSelected(){
        for (int i = 0; i < group.size(); i++) {
            CartBean.DataBean dataBean = group.get(i);
            if (!dataBean.isCheck()){
                return false;
            }
        }
        return true;
    }
    //判断二级列表是否全部选中
    public boolean isAllChildCbSelected(int groupPosition){
        List<CartBean.DataBean.ListBean> datasBeen = child.get(groupPosition);
        for (int i = 0; i < datasBeen.size(); i++) {
            CartBean.DataBean.ListBean datasBean = datasBeen.get(i);
            if (!datasBean.isCheck()){
                return false;
            }
        }
        return true;
    }
    //计算列表中选中的钱和数量
    private PriceAndCountEvent computer(){
        int count = 0;
        int price = 0;
        for (int i = 0; i < child.size(); i++) {
            List<CartBean.DataBean.ListBean> datasBeen = child.get(i);
            for (int j = 0; j < datasBeen.size(); j++) {
                CartBean.DataBean.ListBean datasBean = datasBeen.get(j);
                if (datasBean.isCheck()){
                    count+=datasBean.getNum();
                    Float pr = Float.valueOf(datasBean.getPrice());
                    price+=datasBean.getNum()*pr;
                }
            }
        }
        PriceAndCountEvent priceAndCountEvent = new PriceAndCountEvent();
        priceAndCountEvent.setCount(count);
        priceAndCountEvent.setPrice(price);
        return priceAndCountEvent;
    }
    //设置全选、反选
    public void changeAllListCbState(boolean flag){
        for (int i = 0; i < group.size(); i++) {
            changeGroupCbState(i,flag);
            changeChildCbState(i,flag);
        }
        EventBus.getDefault().post(computer());
        notifyDataSetChanged();
    }
}

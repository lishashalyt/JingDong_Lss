package com.bawei.jingdong_lss.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bawei.jingdong_lss.R;
import com.bawei.jingdong_lss.adapter.CartAdapter;
import com.bawei.jingdong_lss.bean.CartBean;
import com.bawei.jingdong_lss.eventbusevent.MessageEvent;
import com.bawei.jingdong_lss.eventbusevent.PriceAndCountEvent;
import com.bawei.jingdong_lss.presenter.GouPresenter;
import com.bawei.jingdong_lss.utils.Myapp;
import com.bawei.jingdong_lss.view.IGouFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by love_mysunshine on 2017/12/12.
 */

public class Frag_car extends Fragment implements IGouFragment {
    private View view;
    private ExpandableListView mElv;
    private CheckBox mCheckAll;
    /**
     * 合计:
     */
    private TextView mTv;
    /**
     * ￥
     */
    private TextView mPrice;
    /**
     * 结算(0)
     */
    private TextView mNumber;

    private GouPresenter gouPresenter;
    private CartAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frag_car, null);
        initView(view);
         gouPresenter = new GouPresenter(this);
        int id = Myapp.sp.getInt("uid", 0);
        String token = Myapp.sp.getString("token", "");
        gouPresenter.setCart(id+"",token);
        EventBus.getDefault().register(this);
        return view;
    }

    private void initView(View view) {
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        mCheckAll = (CheckBox) view.findViewById(R.id.checkAll);
        mTv = (TextView) view.findViewById(R.id.tv);
        mPrice = (TextView) view.findViewById(R.id.price);
        mNumber = (TextView) view.findViewById(R.id.number);
        mCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.changeAllListCbState(mCheckAll.isChecked());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Override
    public void showCart(List<CartBean.DataBean> group, List<List<CartBean.DataBean.ListBean>> child) {
        mElv.setGroupIndicator(null);
        adapter = new CartAdapter(getContext(),group,child);
        mElv.setAdapter(adapter);
        for (int i = 0; i < group.size(); i++) {
            //默认二级列表展开
            mElv.expandGroup(i);
        }
    }
    @Subscribe
    public void onMessageEvent(MessageEvent event){
        mCheckAll.setChecked(event.isChecked());
    }
    @Subscribe
    public void onMessageEvent(PriceAndCountEvent event) {
        mNumber.setText("结算(" + event.getCount() + ")");
        mPrice.setText(event.getPrice() + "");
    }
}

package com.bawei.jingdong_lss.presenter;


import com.bawei.jingdong_lss.bean.CartBean;
import com.bawei.jingdong_lss.model.GouModel;
import com.bawei.jingdong_lss.model.IGouModel;
import com.bawei.jingdong_lss.net.OnNetListener;
import com.bawei.jingdong_lss.view.IGouFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by love_mysunshine on 2017/12/17.
 * 购物车
 */

public class GouPresenter {
    private IGouModel iGouModel;
    private IGouFragment iGouFragment;

    public GouPresenter(IGouFragment iGouFragment) {
        this.iGouFragment = iGouFragment;
        iGouModel = new GouModel();
    }
    public void setCart(String cid, String token){
        iGouModel.getCart( cid, token,new OnNetListener<CartBean>() {
            @Override
            public void onSuccess(CartBean cartBean) {
                List<CartBean.DataBean> data = cartBean.getData();
                List<List<CartBean.DataBean.ListBean>> child = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    List<CartBean.DataBean.ListBean> list = data.get(i).getList();
                    child.add(list);
                }
                iGouFragment.showCart(data,child);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}

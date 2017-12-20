package com.bawei.jingdong_lss.view;


import com.bawei.jingdong_lss.bean.CartBean;

import java.util.List;

/**
 * Created by  on 2017/12/17.
 */

public interface IGouFragment {
    public void showCart(List<CartBean.DataBean> group, List<List<CartBean.DataBean.ListBean>> child);
}

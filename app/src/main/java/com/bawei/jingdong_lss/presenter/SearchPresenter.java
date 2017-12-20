package com.bawei.jingdong_lss.presenter;

import com.bawei.jingdong_lss.bean.SearchListBean;
import com.bawei.jingdong_lss.model.ISearchModel;
import com.bawei.jingdong_lss.model.SearchModel;
import com.bawei.jingdong_lss.net.OnNetListener;
import com.bawei.jingdong_lss.view.SearchView;

import java.util.List;

/**
 * Created by love_mysunshine on 2017/12/17.
 */

public class SearchPresenter {
    public ISearchModel iSearchModel;
    public SearchView searchView;
    public SearchPresenter(SearchView searchView) {
        this.searchView = searchView;
        iSearchModel = new SearchModel();
    }
    //查询的参数
    public void OnShowSearch(final String name) {
        iSearchModel.onShowSearch(name, new OnNetListener<SearchListBean>() {
            @Override
            public void onSuccess(SearchListBean searchListBean) {
                List<SearchListBean.DataBean> data = searchListBean.getData();
                searchView.onShowSearch(data);

            }

            @Override
            public void onFailure(Exception e) {

            }

        });
    }
    }


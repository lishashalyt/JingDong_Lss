package com.bawei.jingdong_lss.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bawei.jingdong_lss.R;
import com.bawei.jingdong_lss.adapter.LeftAdapter;
import com.bawei.jingdong_lss.adapter.RightAdapter;
import com.bawei.jingdong_lss.bean.GridData;
import com.bawei.jingdong_lss.bean.ProductBean;
import com.bawei.jingdong_lss.presenter.FenPresenter;
import com.bawei.jingdong_lss.view.IFenFragment;

import java.util.List;

/**
 * Created by love_mysunshine on 2017/12/12.
 */

public class Frag_fl extends Fragment implements IFenFragment{

    private View view;
    private ListView mLvLeft;
    private RecyclerView mRvRight;

    private LeftAdapter adapter;
    private FenPresenter fenPresenter;
    private List<GridData.DataBean> data;
    private List<ProductBean.DataBean> rightdata;
    private RightAdapter rightAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frag_fl, null);
        mLvLeft = view.findViewById(R.id.lv_left);
        mRvRight = view.findViewById(R.id.rv_right);
        fenPresenter = new FenPresenter(this);
        fenPresenter.setList();
        mLvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GridData.DataBean dataBean = data.get(i);
                int cid = dataBean.getCid();
                fenPresenter.setProduct(cid + "");
            }
        });
        mRvRight.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private void initView(View view) {
        mLvLeft = (ListView) view.findViewById(R.id.lv_left);
        mRvRight = (RecyclerView) view.findViewById(R.id.rv_right);
    }
    @Override
    public void onResume() {
        super.onResume();
        fenPresenter.setProduct(1 + "");
    }
    @Override
    public void showView(GridData gridBean) {
        data = gridBean.getData();
        adapter = new LeftAdapter(getContext(), data);
        mLvLeft.setAdapter(adapter);
    }

    @Override
    public void showProduct(ProductBean productBean) {
        rightdata = productBean.getData();
        rightAdapter = new RightAdapter(getContext(), rightdata);
        mRvRight.setAdapter(rightAdapter);
        rightAdapter.notifyDataSetChanged();
    }
}

package com.bawei.jingdong_lss.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ViewFlipper;

import com.bawei.jingdong_lss.R;
import com.bawei.jingdong_lss.SearchActivity;
import com.bawei.jingdong_lss.adapter.GdAdapter;
import com.bawei.jingdong_lss.adapter.TjAdapter;
import com.bawei.jingdong_lss.bean.BnData;
import com.bawei.jingdong_lss.bean.GridData;
import com.bawei.jingdong_lss.presenter.Bnpresenter;
import com.bawei.jingdong_lss.presenter.Gdpresenter;
import com.bawei.jingdong_lss.presenter.Tjpersenter;
import com.bawei.jingdong_lss.utils.BnImageLoader;
import com.bawei.jingdong_lss.view.IShowBnData;
import com.bawei.jingdong_lss.view.IShowGdData;
import com.bawei.jingdong_lss.view.IShowTjData;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by love_mysunshine on 2017/12/12.
 */

public class Frag_sy extends Fragment implements IShowBnData,IShowGdData,IShowTjData{
    /**
     * 搜一搜
     */
    private EditText mEditCenter;
    private Banner mBanner;
    private RecyclerView mRvGrid;
    private ViewFlipper mVf;
    private RecyclerView mRvTj;

    View view;
    /*调用p层(p层向v层传数据,banner)*/
    Bnpresenter bnpresenter;
    /*调用p层(p层向v层传数据,tj)*/
    Tjpersenter tjpersenter;
    /*调用p层(p层向v层传数据,grid)*/
    Gdpresenter gdpresenter;
    /*banner集合*/
    List<BnData.DataBean> bnlist;
    /*tj集合*/
    List<BnData.TuijianBean.ListBean> tjlist;
    /*grid集合*/
    List<GridData.DataBean> gdlist;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= View.inflate(getActivity(), R.layout.frag_sy, null);
        initView(view);
        mEditCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        /*实例化banner集合*/
        bnlist=new ArrayList<>();
         /*实例化p层(p层向v层传数据,banner)*/
         bnpresenter = new Bnpresenter(getActivity(),this);
        /*调用p层的方法*/
        bnpresenter.ShowBNData();
        showVPData(bnlist);

        /*grid*/
        gdlist = new ArrayList<>();
        gdpresenter = new Gdpresenter(this);
        gdpresenter.showGdData();

        /*grid*/
        tjlist = new ArrayList<>();
        tjpersenter = new Tjpersenter(getActivity(),this);
        tjpersenter.showTjData();
/*跑马灯*/
        initViewFlipper();
        return view;
    }
    /*跑马灯*/
    private void initViewFlipper() {
        mVf.addView(View.inflate(getActivity(),R.layout.jingdong_text1,null));
        mVf.addView(View.inflate(getActivity(),R.layout.jingdong_text2,null));
        mVf.addView(View.inflate(getActivity(),R.layout.jingdong_text3,null));
    }
    private void initView(View view) {
        mEditCenter = (EditText) view.findViewById(R.id.edit_center);
        mBanner = (Banner) view.findViewById(R.id.banner);
        mRvGrid = (RecyclerView) view.findViewById(R.id.rv_grid);
        mVf = (ViewFlipper) view.findViewById(R.id.vf);
        mRvTj = (RecyclerView) view.findViewById(R.id.rv_tj);
    }

    @Override
    public void showVPData(List<BnData.DataBean> bnlist) {
 /*转变banner图片格式*/
        ArrayList<String> pic = new ArrayList<>();
        for (int i = 0; i < bnlist.size(); i++) {
            pic.add(bnlist.get(i).getIcon());
            Log.i("111111",bnlist.get(i).getIcon());
        }
        /*用的是glid加载banner图片*/
        mBanner.setImageLoader(new BnImageLoader());
        mBanner.setImages(pic);
        mBanner.start();
    }
    @Override
    public void showGdData(List<GridData.DataBean> gdlist) {
        GridLayoutManager gdmanager=new GridLayoutManager(getActivity(),5,GridLayoutManager.VERTICAL,false);
        mRvGrid.setLayoutManager(gdmanager);
        GdAdapter gdAdapter=new GdAdapter(getActivity(),gdlist);
        mRvGrid.setAdapter(gdAdapter);
        Log.i("111111", "onNext()" + gdlist);
    }

    @Override
    public void showTjData(List<BnData.TuijianBean.ListBean> tjlist) {
        GridLayoutManager tjmanager=new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false);
        mRvTj.setLayoutManager(tjmanager);
        TjAdapter tjAdapter=new TjAdapter(getActivity(),tjlist);
        mRvTj.setAdapter(tjAdapter);
    }
}

package com.bawei.jingdong_lss.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.jingdong_lss.R;
import com.bawei.jingdong_lss.adapter.MyAdapter;
import com.bawei.jingdong_lss.bean.BnData;
import com.bawei.jingdong_lss.presenter.Tjpersenter;
import com.bawei.jingdong_lss.view.IShowTjData;
import com.stone.card.library.CardSlidePanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by love_mysunshine on 2017/12/12.
 */

public class Frag_fx extends Fragment implements IShowTjData {
   View view;
    CardSlidePanel.CardSwitchListener cardSwitchListener;
    List<BnData.TuijianBean.ListBean> tjlist;

    CardSlidePanel slidePanel;
    Tjpersenter tjpersenter;
    MyAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.frag_fx, null);
        tjlist = new ArrayList<>();
         initView();
         tjpersenter = new Tjpersenter(getContext(),this);
         this.tjpersenter.showTjData();

        view.findViewById(R.id.notify_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidePanel.getAdapter().notifyDataSetChanged();
            }
        });
        return view;
    }

    private void initView() {
        slidePanel = (CardSlidePanel) view.findViewById(R.id.image_slide_panel);

        // 1. 左右滑动监听
        cardSwitchListener = new CardSlidePanel.CardSwitchListener() {

            @Override
            public void onShow(int index) {
                //Toast.makeText(MainActivity.this, "正在显示"+list.get(index).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCardVanish(int index, int type) {
                //Toast.makeText(MainActivity.this, "正在消失"+list.get(index).getTitle(), Toast.LENGTH_SHORT).show();
            }
        };
        slidePanel.setCardSwitchListener(cardSwitchListener);
    }


    @Override
    public void showTjData(List<BnData.TuijianBean.ListBean> tjlist) {
        adapter = new MyAdapter(getContext(), tjlist);
        slidePanel.setAdapter(adapter);
    }
}

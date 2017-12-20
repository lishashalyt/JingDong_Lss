package com.bawei.jingdong_lss.presenter;

import android.content.Context;

import com.bawei.jingdong_lss.bean.BnData;
import com.bawei.jingdong_lss.model.GetBnData;
import com.bawei.jingdong_lss.okhttp.OnUiCallback;
import com.bawei.jingdong_lss.view.IShowBnData;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by love_mysunshine on 2017/12/12.
 */

public class Bnpresenter {
    Context context;
    List<BnData.DataBean> bnlist = new ArrayList<>();
    /*m层*/
    GetBnData getBnData;
    /*V层*/
    IShowBnData iShowBnData;

    public Bnpresenter(Context context, IShowBnData vpview) {
        this.context = context;
        this.iShowBnData = vpview;
        getBnData = new GetBnData();

    }
    /*在v层调用,解析数据*/
    public void ShowBNData(){
        getBnData.getBNData(new OnUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                BnData data=gson.fromJson(result,BnData.class);
                bnlist.addAll(data.getData());
                iShowBnData.showVPData(bnlist);
            }
        });

    }
}
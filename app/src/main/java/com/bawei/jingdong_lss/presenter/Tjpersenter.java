package com.bawei.jingdong_lss.presenter;

import android.content.Context;

import com.bawei.jingdong_lss.bean.BnData;
import com.bawei.jingdong_lss.model.GetBnData;
import com.bawei.jingdong_lss.okhttp.OnUiCallback;
import com.bawei.jingdong_lss.view.IShowTjData;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by love_mysunshine on 2017/12/13.
 */

public class Tjpersenter {
    Context context;
    List<BnData.TuijianBean.ListBean> tjlist = new ArrayList<>();
    /*m层*/
    GetBnData getBnData;
    /*V层*/
    IShowTjData iShowTjData;

    public Tjpersenter(Context context, IShowTjData vpview) {
        this.context = context;
        this.iShowTjData = vpview;
        getBnData = new GetBnData();

    }
    public void showTjData(){
        getBnData.getBNData(new OnUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                BnData data=gson.fromJson(result,BnData.class);
                tjlist.addAll(data.getTuijian().getList());
                iShowTjData.showTjData(tjlist);
            }
        });

    }
}

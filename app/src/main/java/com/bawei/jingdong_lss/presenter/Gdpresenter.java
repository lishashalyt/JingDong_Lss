package com.bawei.jingdong_lss.presenter;

import android.util.Log;

import com.bawei.jingdong_lss.bean.GridData;
import com.bawei.jingdong_lss.model.GetGdData;
import com.bawei.jingdong_lss.okhttp.OnUiCallback;
import com.bawei.jingdong_lss.view.IShowGdData;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by love_mysunshine on 2017/12/13.
 */

public class Gdpresenter {
    List<GridData.DataBean> gdlist=new ArrayList<>();
    IShowGdData iShowGdData;
    GetGdData getGdData;

    public Gdpresenter(IShowGdData view) {

        this.iShowGdData = view;
        getGdData=new GetGdData();
    }

    public void showGdData(){
        getGdData.getGdData(new OnUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                GridData gridData=gson.fromJson(result,GridData.class);
                gdlist.addAll(gridData.getData());
                iShowGdData.showGdData(gdlist);
                Log.i("111111", "onSuccess()" + gdlist);
            }
        });
    }

}

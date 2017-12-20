package com.bawei.jingdong_lss.presenter;


import com.bawei.jingdong_lss.bean.ZiProduct;
import com.bawei.jingdong_lss.model.IZiProductModel;
import com.bawei.jingdong_lss.model.ZiProductModel;
import com.bawei.jingdong_lss.net.OnNetListener;
import com.bawei.jingdong_lss.view.IZiProductActivity;

public class ZiProductPresenter {
    private IZiProductModel iZiProductModel;
    private IZiProductActivity iZiProductActivity;

    public ZiProductPresenter(IZiProductActivity iZiProductActivity) {
        this.iZiProductActivity = iZiProductActivity;
        iZiProductModel = new ZiProductModel();
    }
    public void setZiProduct(String cid){
        iZiProductModel.getZiProduct(new OnNetListener<ZiProduct>() {
            @Override
            public void onSuccess(ZiProduct ziProduct) {
                iZiProductActivity.shouZIProduct(ziProduct);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, cid);
    }
}

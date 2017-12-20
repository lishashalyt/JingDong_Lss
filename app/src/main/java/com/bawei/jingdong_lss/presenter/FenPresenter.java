package com.bawei.jingdong_lss.presenter;

import com.bawei.jingdong_lss.bean.GridData;
import com.bawei.jingdong_lss.bean.ProductBean;
import com.bawei.jingdong_lss.model.FenModel;
import com.bawei.jingdong_lss.model.IFenModel;
import com.bawei.jingdong_lss.net.OnNetListener;
import com.bawei.jingdong_lss.view.IFenFragment;

public class FenPresenter {
    private IFenModel iFenModel;
    private IFenFragment iFenFragment;

    public FenPresenter(IFenFragment iFenFragment) {
        this.iFenFragment = iFenFragment;
        iFenModel = new FenModel();
    }

  public void setList(){
      iFenModel.getList(new OnNetListener<GridData>() {
          @Override
          public void onSuccess(GridData gridData) {
             iFenFragment.showView(gridData);
          }

          @Override
          public void onFailure(Exception e) {

          }
      });
  }


    public void setProduct(String cid){
        iFenModel.getProduct(new OnNetListener<ProductBean>() {
            @Override
            public void onSuccess(ProductBean productBean) {
                iFenFragment.showProduct(productBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        },cid);
    }
}

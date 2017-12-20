package com.bawei.jingdong_lss.presenter;


import com.bawei.jingdong_lss.bean.AddCartBean;
import com.bawei.jingdong_lss.bean.ProductDetailBean;
import com.bawei.jingdong_lss.model.IProductDetailModel;
import com.bawei.jingdong_lss.model.ProductDetailModel;
import com.bawei.jingdong_lss.net.OnNetListener;
import com.bawei.jingdong_lss.view.IProductDetailActivity;

public class ProductDetailPresenter {
    private IProductDetailModel iProductDetailModel;
    private IProductDetailActivity iProductDetailActivity;

    public ProductDetailPresenter(IProductDetailActivity iProductDetailActivity) {
        this.iProductDetailActivity = iProductDetailActivity;
        iProductDetailModel = new ProductDetailModel();
    }
    public void setDetail(String pid){
        iProductDetailModel.getDetail(new OnNetListener<ProductDetailBean>() {
            @Override
            public void onSuccess(ProductDetailBean detailBean) {
                iProductDetailActivity.shouDetail(detailBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        },pid);
    }
    public void setAddCart(String cid, String pid){
        iProductDetailModel.getAddCart(cid, pid, new OnNetListener<AddCartBean>() {
            @Override
            public void onSuccess(AddCartBean addCartBean) {
                iProductDetailActivity.shouAddCart(addCartBean);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}

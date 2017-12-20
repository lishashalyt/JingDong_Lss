package com.bawei.jingdong_lss.net;

import com.bawei.jingdong_lss.bean.AddCartBean;
import com.bawei.jingdong_lss.bean.CartBean;
import com.bawei.jingdong_lss.bean.GridData;
import com.bawei.jingdong_lss.bean.LoginBean;
import com.bawei.jingdong_lss.bean.ProductBean;
import com.bawei.jingdong_lss.bean.ProductDetailBean;
import com.bawei.jingdong_lss.bean.RegisterBean;
import com.bawei.jingdong_lss.bean.SearchListBean;
import com.bawei.jingdong_lss.bean.ZiProduct;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ServerApi {
/*    @GET(Api.BANNER)
    Observable<ShouBean> shou();*/
    @GET(Api.GRID)
    Observable<GridData> grid();
    @GET(Api.PRODUCT)
    Observable<ProductBean> product(@Query("cid") String cid);
    @GET(Api.ZIPRODUCT)
    Observable<ZiProduct> ziproduct(@Query("pscid") String cid);
    @GET(Api.PRODUCTDETAIL)
    Observable<ProductDetailBean> detail(@Query("pid") String pid);

    @GET(Api.LOGIN)
    Observable<LoginBean> login(@Query("mobile") String phone,@Query("password") String pass);
    @GET(Api.REGIST)
    Observable<RegisterBean> regist(@Query("mobile") String phone, @Query("password") String pass);

    @GET(Api.SEARCH)
    Observable<SearchListBean> searchList(@Query("keywords") String name);

    @GET(Api.CART)
    Observable<CartBean> cart(@Query("uid") String uid, @Query("token") String token);//查询购物车
    @GET(Api.ADDCART)
    Observable<AddCartBean> addCart(@Query("uid") String uid, @Query("pid") String pid);//添加购物车
}

package com.bawei.jingdong_lss.presenter;


import com.bawei.jingdong_lss.bean.LoginBean;
import com.bawei.jingdong_lss.model.ILoginModel;
import com.bawei.jingdong_lss.model.LoginModel;
import com.bawei.jingdong_lss.net.OnNetListener;
import com.bawei.jingdong_lss.view.ILoginActivity;

public class LoginPresenter {
    private ILoginModel iLoginModel;
    private ILoginActivity iLoginActivity;

    public LoginPresenter(ILoginActivity iLoginActivity) {
        this.iLoginActivity = iLoginActivity;
        iLoginModel = new LoginModel();
    }
    public void setLogin(String phone,String pass){
        iLoginModel.getLogin(new OnNetListener<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                String code = loginBean.getCode();
                String msg = loginBean.getMsg();
                iLoginActivity.showLogin(loginBean,code,msg);
            }

            @Override
            public void onFailure(Exception e) {

            }
        },phone,pass);
    }
}

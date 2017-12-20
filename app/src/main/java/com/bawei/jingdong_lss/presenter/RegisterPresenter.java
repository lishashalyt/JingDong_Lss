package com.bawei.jingdong_lss.presenter;


import com.bawei.jingdong_lss.bean.RegisterBean;
import com.bawei.jingdong_lss.model.IRegisterModel;
import com.bawei.jingdong_lss.model.RegisterModel;
import com.bawei.jingdong_lss.net.OnNetListener;
import com.bawei.jingdong_lss.view.IRegisterActivity;

public class RegisterPresenter {
    private IRegisterModel iRegisterModel;
    private IRegisterActivity iRegisterActivity;

    public RegisterPresenter(IRegisterActivity iRegisterActivity) {
        this.iRegisterActivity = iRegisterActivity;
        iRegisterModel = new RegisterModel();
    }
    public void setRegist(String phone,String pass){
        iRegisterModel.getRegister(new OnNetListener<RegisterBean>() {
            @Override
            public void onSuccess(RegisterBean registerBean) {
                String code = registerBean.getCode();
                String msg = registerBean.getMsg();
                iRegisterActivity.showRegister(code,msg);
            }

            @Override
            public void onFailure(Exception e) {

            }
        }, phone, pass);
    }
}

package com.bawei.jingdong_lss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.jingdong_lss.bean.LoginBean;
import com.bawei.jingdong_lss.presenter.LoginPresenter;
import com.bawei.jingdong_lss.utils.Myapp;
import com.bawei.jingdong_lss.view.ILoginActivity;


/*
* 登录
* */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener,ILoginActivity {

    /**
     * 请输入账号
     */
    private EditText mAccont;
    /**
     * 请输入密码
     */
    private EditText mPass;
    /**
     * 登录
     */
    private Button mLogin;
    /**
     * 请注册
     */
    private TextView mRegist;
    private LoginPresenter loginPresenter;
    String codes;
    String msgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        ActionBar bar = getSupportActionBar();
        bar.hide();
        loginPresenter = new LoginPresenter(this);
    }

    private void initView() {
        mAccont = findViewById(R.id.accont);
        mPass = findViewById(R.id.pass);
        mLogin = findViewById(R.id.login);
        mLogin.setOnClickListener(this);
        mRegist = findViewById(R.id.regist);
        mRegist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login:
                String phone = mAccont.getText().toString().trim();
                String pass = mPass.getText().toString().trim();
                loginPresenter.setLogin(phone,pass);
                Intent intent = new Intent(this, TowActivity.class);
                intent.putExtra("myfrag",1);
                startActivity(intent);
                break;
            case R.id.regist:
                Intent intent1 = new Intent(this, RegisterActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void showLogin(LoginBean loginBean, String code, String msg) {
        codes = code;
        msgs = msg;
        if (codes.equals("0")){
            Toast.makeText(this,msgs,Toast.LENGTH_SHORT).show();
            LoginBean.DataBean data = loginBean.getData();
            int uid = data.getUid();
            String name = data.getUsername();
            String token = data.getToken();
            Myapp.edit.putInt("uid",uid);
            Myapp.edit.putString("name",name);
            Myapp.edit.putString("token",token);
            Myapp.edit.putBoolean("flag",true);
            Myapp.edit.commit();
            finish();
        } else {
            Myapp.edit.putBoolean("flag",false);
            Myapp.edit.commit();
            Toast.makeText(this,msgs,Toast.LENGTH_SHORT).show();
        }
    }
}
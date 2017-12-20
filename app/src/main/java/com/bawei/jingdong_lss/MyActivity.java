package com.bawei.jingdong_lss;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bawei.jingdong_lss.utils.Myapp;
import com.facebook.drawee.view.SimpleDraweeView;
/*
* 已登录的个人信息界面--可退出当前账号
* */
public class MyActivity extends AppCompatActivity {

    private ImageView myBack2;
    private SimpleDraweeView myXimg;
    /**
     * **
     */
    private TextView myNickname2;
    private LinearLayout myL;
    /**
     * 退出登录
     */
    private Button myTui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initView();
        ActionBar bar = getSupportActionBar();
        bar.hide();
    }

    private void initView() {
        myBack2 = findViewById(R.id.my_back2);
        back();
        myXimg = findViewById(R.id.my_ximg);
        myNickname2 = findViewById(R.id.my_nickname2);
        myL = findViewById(R.id.my_l);
        myTui = findViewById(R.id.my_tui);
        exit();
    }

    private void back() {
        myBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void exit() {
        myTui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity.this);
                //    设置Title的图标
                builder.setIcon(R.mipmap.ic_launcher_round);
                //    设置Title的内容
                builder.setTitle("退出操作");
                //    设置Content来显示一个信息
                builder.setMessage("确定退出登录吗？");
                //    设置一个PositiveButton
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Myapp.edit.putBoolean("flag",false);
                        Myapp.edit.commit();
                        finish();
                    }
                });
                //    设置一个NegativeButton
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(MyActivity.this, "您取消了删除" + which, Toast.LENGTH_SHORT).show();
                    }
                });
                //    设置一个NeutralButton
                builder.setNeutralButton("忽略", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(MyActivity.this, "您忽略了该操作" + which, Toast.LENGTH_SHORT).show();
                    }
                });
                //    显示出该对话框
                builder.show();
            }
        });
    }

    public void into(){
        String nickname = Myapp.sp.getString("name", "");
        myNickname2.setText(nickname+"");
    }
    @Override
    protected void onResume() {
        super.onResume();
        into();
    }
}

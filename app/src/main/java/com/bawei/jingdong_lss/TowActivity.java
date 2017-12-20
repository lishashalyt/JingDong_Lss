package com.bawei.jingdong_lss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawei.jingdong_lss.fragment.Frag_car;
import com.bawei.jingdong_lss.fragment.Frag_fl;
import com.bawei.jingdong_lss.fragment.Frag_fx;
import com.bawei.jingdong_lss.fragment.Frag_me;
import com.bawei.jingdong_lss.fragment.Frag_sy;

import java.util.ArrayList;
import java.util.List;

public class TowActivity extends AppCompatActivity {

    private ViewPager mVp;
    private RadioButton mFirstpage;
    private RadioButton mWeitao;
    private RadioButton mXiaoxi;
    private RadioButton mGouwuche;
    private RadioButton mMine;
    private RadioGroup mRg;

    List<Fragment> list;
    MyFragAdapter myFragAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tow);
        initView();

        list = new ArrayList<Fragment>();
        list.add(new Frag_sy());
        list.add(new Frag_fl());
        list.add(new Frag_fx());
        list.add(new Frag_car());
        list.add(new Frag_me());
        myFragAdapter = new MyFragAdapter(getSupportFragmentManager());
        mVp.setAdapter(this.myFragAdapter);

        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mRg.check(R.id.firstpage);
                        break;
                    case 1:
                        mRg.check(R.id.weitao);
                        break;
                    case 2:
                        mRg.check(R.id.xiaoxi);
                        break;
                    case 3:
                        mRg.check(R.id.gouwuche);
                        break;
                    case 4:
                        mRg.check(R.id.mine);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                switch (i) {
                    case R.id.firstpage:
                        mVp.setCurrentItem(0);
                        break;
                    case R.id.weitao:
                        mVp.setCurrentItem(1);
                        break;
                    case R.id.xiaoxi:
                        mVp.setCurrentItem(2);
                        break;
                    case R.id.gouwuche:
                        mVp.setCurrentItem(3);
                        break;
                    case R.id.mine:
                        mVp.setCurrentItem(4);
                        break;
                    default:
                        break;
                }
            }
        });
       /*阻止滑动*/
        mVp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        mVp.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        mVp.requestDisallowInterceptTouchEvent(false);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mFirstpage = (RadioButton) findViewById(R.id.firstpage);
        mWeitao = (RadioButton) findViewById(R.id.weitao);
        mXiaoxi = (RadioButton) findViewById(R.id.xiaoxi);
        mGouwuche = (RadioButton) findViewById(R.id.gouwuche);
        mMine = (RadioButton) findViewById(R.id.mine);
        mRg = (RadioGroup) findViewById(R.id.rg);
    }
    /*向viewpager里添加fragment*/
    class MyFragAdapter extends FragmentPagerAdapter {

        public MyFragAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        int tag= intent.getIntExtra("tag", 0);
        int my = intent.getIntExtra("myfrag", 0);
        if(tag== 3){
            Frag_car frag_car = new Frag_car();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.vp,frag_car);
            fragmentTransaction.commit();
            mVp.setCurrentItem(3);
        }
        if(my == 5 ){

            mVp.setCurrentItem(4);
        }
    }
}

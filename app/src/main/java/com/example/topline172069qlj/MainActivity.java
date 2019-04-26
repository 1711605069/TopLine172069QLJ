package com.example.topline172069qlj;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.topline172069qlj.fragment.CountFragment;
import com.example.topline172069qlj.fragment.HomeFragment;
import com.example.topline172069qlj.fragment.MeFragment;
import com.example.topline172069qlj.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView TvMainTitle;
    private ViewPager ViewPager;
    private RadioGroup RadioGroup;
    private List<Fragment> alFragement=new ArrayList<>();
    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        intViewPager();
        initListener();
        initDialog();
    }

    private void initDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("TopLine").setIcon(R.drawable.mm4).setMessage("是否退出？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("取消",null);
        mDialog =builder.create();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if (!mDialog.isShowing()){
                    mDialog.show();
                }else {
                    mDialog.dismiss();
                }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initListener() {
        ViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
               switch (i){
                   case 0:
                       TvMainTitle.setText("首页");
                       RadioGroup.check(R.id.rb_home);
                   break;
                   case 1:
                       TvMainTitle.setText("统计");
                       RadioGroup.check(R.id.rb_count);
                       break;
                   case 2:
                       TvMainTitle.setText("视频");
                       RadioGroup.check(R.id.rb_video);
                       break;
                   case 3:
                       TvMainTitle.setText("我的模块");
                       RadioGroup.check(R.id.rb_me);
                       break;
               }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(android.widget.RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        ViewPager.setCurrentItem(0,false);
                        break;
                    case R.id.rb_count:
                        ViewPager.setCurrentItem(1,false);
                        break;
                    case R.id.rb_video:
                        ViewPager.setCurrentItem(2,false);
                        break;
                    case R.id.rb_me:
                        ViewPager.setCurrentItem(3,false);
                        break;
                }
            }
        });
    }

    private void intViewPager() {
        alFragement.add(new HomeFragment());
        alFragement.add(new CountFragment());
        alFragement.add(new VideoFragment());
       alFragement.add(new MeFragment());
        ViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return alFragement.get(i);
            }

            @Override
            public int getCount() {
                return alFragement.size();
            }
        });
    }

    private void initView() {
        TvMainTitle = (TextView) findViewById(R.id.tv_main_title);
        ViewPager = (ViewPager) findViewById(R.id.viewPager);
        RadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    }
}

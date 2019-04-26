package com.example.topline172069qlj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //3秒后跳转到MainActivity.java
        //定时器和任务
        Timer timer=new Timer();//定时器
        TimerTask task=new TimerTask() {//定义任务，实现Runn接口
            @Override
            public void run() {//重定run（）方法
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
     startActivity(intent);
     finish();
            }
        };
        timer.schedule(task,3000);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        try{
//            Thread.sleep(3000);
//
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
}

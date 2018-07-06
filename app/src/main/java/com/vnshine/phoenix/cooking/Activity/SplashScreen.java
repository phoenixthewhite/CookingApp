package com.vnshine.phoenix.cooking.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.google.android.gms.ads.MobileAds;
import com.vnshine.phoenix.cooking.DataBase.DatabaseHelper;
import com.vnshine.phoenix.cooking.Model.Dish;
import com.vnshine.phoenix.cooking.R;

import java.util.ArrayList;
import java.util.Random;



public class SplashScreen extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DatabaseHelper.xuLiSaoChepCSDL(this);
        databaseHelper = new DatabaseHelper(this);
        MobileAds.initialize(this, "ca-app-pub-9164465649979150~6166338470");
        final Intent intent=new Intent(this,MainActivity.class);
        saveDailyDishes();
        intent.setAction("main");
        CountDownTimer countDownTimer=new CountDownTimer(2000,100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(intent);
                finish();
            }
        }.start();
    }


    private void saveDailyDishes() {
        SharedPreferences sharedPreferences = getSharedPreferences("daily_dishes",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        ArrayList<Dish> autoDish = new ArrayList<>();
        ArrayList<Dish> tempList;
        tempList = databaseHelper.getListDishes("1");
//        autoDish.add(tempList.get(new Random().nextInt(tempList.size())));
        editor.putInt("kho",new Random().nextInt(tempList.size()-1));
        tempList.clear();
        tempList = databaseHelper.getListDishes("2");
//        autoDish.add(tempList.get(new Random().nextInt(tempList.size())));
        editor.putInt("canh",new Random().nextInt(tempList.size()-1));
        tempList.clear();
        tempList = databaseHelper.getListDishes("3");
//        autoDish.add(tempList.get(new Random().nextInt(tempList.size())));
        editor.putInt("xao",new Random().nextInt(tempList.size()-1));
        editor.commit();

    }
}

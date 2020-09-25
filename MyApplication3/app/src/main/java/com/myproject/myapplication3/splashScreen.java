package com.myproject.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splashScreen extends AppCompatActivity {

    private static  int SplashScreen = 2000;

    Animation topanimation , bottomannimation;
    ImageView image;
    TextView appname ,slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        topanimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomannimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image=findViewById(R.id.img_boy);
        appname=findViewById(R.id.txt_appname);
        slogan=findViewById(R.id.txt_slogan);


        image.setAnimation(topanimation);
        appname.setAnimation(bottomannimation);
       slogan.setAnimation(bottomannimation);


       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent = new Intent(splashScreen.this,loadingPage.class );
               startActivity(intent);
               finish();

           }
       }, SplashScreen);
    }
}
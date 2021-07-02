package com.example.ban;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000;

    private Animation topAnim,bottomAnim;
    private ImageView shippe,logo;
    private TextView appname,splashtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //to remove the status bar
        setContentView(R.layout.activity_main);

        //Animation
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);




        shippe=findViewById(R.id.shippeid);
        logo=findViewById(R.id.logoid);
        appname=findViewById(R.id.appnameid);
        splashtext=findViewById(R.id.splashtextid);

        shippe.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        appname.setAnimation(bottomAnim);
        splashtext.setAnimation(bottomAnim);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,GetStarted.class);
              Pair[] pairs =new Pair[3];
              pairs[0]=new Pair<View,String>(logo,"logo_trns");
              pairs[1]=new Pair<View,String>(appname,"app_name_trns");
              pairs[2]=new Pair<View,String>(splashtext,"text_trns");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                    startActivity(intent,options.toBundle());
                    finishAfterTransition();
                }
            }
        },SPLASH_SCREEN);
    }
}
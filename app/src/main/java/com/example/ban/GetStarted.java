package com.example.ban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetStarted extends AppCompatActivity {

    private Button getStarted;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        setContentView(R.layout.activity_get_started);

        getStarted =findViewById(R.id.buttongetstartedID);

        String loginStatus = sharedPreferences.getString(getResources().getString(R.string.prefLoginState),"");
        if(loginStatus.equals("loggedin")){
            Intent intent =new Intent(GetStarted.this,Home.class);
            startActivity(intent);
            finish();
        }

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GetStarted.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
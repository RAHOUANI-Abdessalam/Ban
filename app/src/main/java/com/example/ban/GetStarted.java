package com.example.ban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetStarted extends AppCompatActivity {

    private Button getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        getStarted =findViewById(R.id.buttongetstartedID);

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GetStarted.this,UserInterrogation.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
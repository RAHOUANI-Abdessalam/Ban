package com.example.ban;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Check_out extends AppCompatActivity {
    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        myDialog = new Dialog(this);
    }
    public void showPopup(View v)
    {
        Button coninueShopping,goToOrders;
        myDialog.setContentView(R.layout.success_buying_popup);
        coninueShopping = (Button) myDialog.findViewById(R.id.continueShoppButtonID);
        goToOrders = (Button) myDialog.findViewById(R.id.gotoOrderButtonId);
        coninueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}
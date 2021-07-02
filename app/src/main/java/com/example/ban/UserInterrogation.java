package com.example.ban;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.RangeSlider;

import java.text.NumberFormat;
import java.util.Currency;

public class UserInterrogation extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    RangeSlider rangeSlider;
    TextView skipNow;
    Button validate;
//    List<CheckBox> checkBoxes =new ArrayList<>();
    CheckBox menCheck,womenCheck,clothingCheck,appleCheck,trendCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interrogation);
        validate = findViewById(R.id.validateButtonId);
        skipNow=findViewById(R.id.skipForNowID);
        menCheck=findViewById(R.id.menfashionCheckBox);
        womenCheck=findViewById(R.id.womenfashionCheckBox);
        clothingCheck=findViewById(R.id.clothingCheckBox);
        appleCheck=findViewById(R.id.appleCheckBox);
        trendCheck=findViewById(R.id.trendCheckBox);
//        checkBoxes.add(menCheck);
//        checkBoxes.add(womenCheck);
//        checkBoxes.add(clothingCheck);
//        checkBoxes.add(appleCheck);
//        checkBoxes.add(trendCheck);
        menCheck.setOnCheckedChangeListener(this);
        womenCheck.setOnCheckedChangeListener(this);
        clothingCheck.setOnCheckedChangeListener(this);
        appleCheck.setOnCheckedChangeListener(this);
        trendCheck.setOnCheckedChangeListener(this);

        validate.setOnClickListener(v -> {
            Intent intent =new Intent(UserInterrogation.this,Login.class);
            startActivity(intent);
            finish();
        });
        skipNow.setOnClickListener(v -> {
            Intent intent =new Intent(UserInterrogation.this,Login.class);
            startActivity(intent);
            finish();
        });
        rangeSlider = findViewById(R.id.sliderRangePriceID);
        rangeSlider.setLabelFormatter(value -> {
            NumberFormat currencyFormat=NumberFormat.getCurrencyInstance();
            currencyFormat.setCurrency(Currency.getInstance("USD"));
            return currencyFormat.format(value);
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            validate.setEnabled(true);
        }else{
            if(!menCheck.isChecked() && !womenCheck.isChecked() && !clothingCheck.isChecked() && !appleCheck.isChecked() && !trendCheck.isChecked()){
                validate.setEnabled(false);
            }
        }
    }

}
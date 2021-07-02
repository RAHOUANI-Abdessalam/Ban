package com.example.ban;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.RangeSlider;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;

import petrov.kristiyan.colorpicker.ColorPicker;


public class Filter extends AppCompatActivity {

    private TextView filter;
    private ImageView back,clear;
    private RangeSlider rangeSlider;
    private Spinner spinnerCategories,spinnerBrands;
    private Button pickColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        filter=findViewById(R.id.namePageID);
        back=findViewById(R.id.menu_id);
        clear=findViewById(R.id.bell_id);

        filter.setText("Filter");
        back.setImageResource(R.drawable.ic_baseline_arrow_back_24);
        clear.setVisibility(View.GONE);

        rangeSlider = findViewById(R.id.sliderRangePriceID);
        rangeSlider.setLabelFormatter(value -> {
            NumberFormat currencyFormat=NumberFormat.getCurrencyInstance();
            currencyFormat.setCurrency(Currency.getInstance("USD"));
            return currencyFormat.format(value);
        });

        spinnerCategories= (Spinner) findViewById(R.id.spinnerCategoriesID);
        spinnerBrands= (Spinner) findViewById(R.id.spinnerBrandsID);

        ArrayList<String> categoriesListForSpinner = new ArrayList<>();
        categoriesListForSpinner.add("Women's Fashion");
        categoriesListForSpinner.add("Men's Fashoin");
        categoriesListForSpinner.add("Phones");
        categoriesListForSpinner.add("Pc");
        categoriesListForSpinner.add("Baby toys");
        categoriesListForSpinner.add("Sport");
        categoriesListForSpinner.add("Art");
        categoriesListForSpinner.add("Food");
        categoriesListForSpinner.add("Beauty and perfumes");
        categoriesListForSpinner.add("Health");
        categoriesListForSpinner.add("Car and motorbike");
        categoriesListForSpinner.add("IT and hight tech");
        categoriesListForSpinner.add("Gardening");
        categoriesListForSpinner.add("Diy");
        categoriesListForSpinner.add("Home appliance");
        categoriesListForSpinner.add("Games and accessories");
        categoriesListForSpinner.add("Smart Home");

        ArrayList<String> brandsListForSpinner = new ArrayList<>();
        brandsListForSpinner.add("Apple");
        brandsListForSpinner.add("Microsoft");
        brandsListForSpinner.add("Toyota");
        brandsListForSpinner.add("Bmw");
        brandsListForSpinner.add("Intel");
        brandsListForSpinner.add("Nike");
        brandsListForSpinner.add("Louis Vuitton");
        brandsListForSpinner.add("Nescafe");
        brandsListForSpinner.add("Philips");
        brandsListForSpinner.add("Canon");
        brandsListForSpinner.add("Gucci");
        brandsListForSpinner.add("Danone");
        brandsListForSpinner.add("adidas");
        brandsListForSpinner.add("Prada");
        brandsListForSpinner.add("Dior");
        brandsListForSpinner.add("Tesla");

        ArrayAdapter<String> adapterCategoriesSpinner=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,categoriesListForSpinner);
        ArrayAdapter<String> adapterBrandsSpinner=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,brandsListForSpinner);

        spinnerCategories.setAdapter(adapterCategoriesSpinner);
        spinnerBrands.setAdapter(adapterBrandsSpinner);

        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerBrands.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pickColor = findViewById(R.id.buttonPickColorsID);
        pickColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencolorpicker();
            }
        });
    }

    private void opencolorpicker() {
        ColorPicker colorPicker=new ColorPicker(Filter.this);
        ArrayList<String> colors =new ArrayList<>();
        colors.add("#FF0000");
        colors.add("#00FFFF");
        colors.add("#00008B");
        colors.add("#ADD8E6");
        colors.add("#800080");
        colors.add("#FFFF00");
        colors.add("#00FF00");
        colors.add("#FF00FF");
        colors.add("#FFC0CB");
        colors.add("#FFFFFF");
        colors.add("#C0C0C0");
        colors.add("#808080");
        colors.add("#000000");
        colors.add("#FFA500");
        colors.add("#A52A2A");
        colors.add("#800000");
        colors.add("#008000");
        colors.add("#808000");


        colorPicker.setColors(colors);
        colorPicker.setColumns(6);
        colorPicker.setRoundColorButton(true);
        colorPicker.show();
        colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        Toast.makeText(Filter.this,"you choosed the " + (position+1) +" color",Toast.LENGTH_LONG).show();
                        pickColor.setText("Done");
                    }

                    @Override
                    public void onCancel() {
                        pickColor.setText("Pick a color");
                    }
                });
    }
}
package com.example.ban.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ban.Controller.ProductAdapter;
import com.example.ban.Filter;
import com.example.ban.Model.Product;
import com.example.ban.R;
import com.example.ban.SliderAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import petrov.kristiyan.colorpicker.ColorPicker;

public class Details_product extends AppCompatActivity implements View.OnClickListener{
    private List<Product> productList =new ArrayList<>();
    private RecyclerView recyclerViewSearch;
    private ProductAdapter productAdapter;

    private TextView productName,price,oldPrice;
    private Button addToCart,pickColor;
    private ImageView image ;
    private Bundle extras;
    SliderView sliderView;
    int[] images = {R.drawable.mensfashion,R.drawable.womenfashoin,R.drawable.sport,R.drawable.phones,R.drawable.watch,R.drawable.pc};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);

        sliderView= findViewById(R.id.productDetailsImageID);
        SliderAdapter sliderAdapter=new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


        extras = getIntent().getExtras();
        //find view by id...
        productName=findViewById(R.id.productDetailsNameID);
        price=findViewById(R.id.productDetailsPriceID);
        oldPrice=findViewById(R.id.productDetailsOldPriceID);
//        image=findViewById(R.id.productDetailsImageID);
        if(extras != null){
            productName.setText(extras.getString("name"));
            price.setText(extras.getString("price"));
            oldPrice.setText(extras.getString("oldPrice"));
            oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//            image.setImageResource(extras.getInt("image"));

        }
        recyclerViewSearch = findViewById(R.id.productHomeRecyclerViewId);
        productAdapter= new ProductAdapter(getApplicationContext(),productList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerViewSearch.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSearch.setAdapter(productAdapter);
        Productdata();

        addToCart=findViewById(R.id.addToCartButtonId);
        addToCart.setOnClickListener(this);
    }
    private void Productdata() {
        Product product=new Product("watch","$250","$400",R.drawable.watch);
        productList.add(product);
        Product product2=new Product("head phones","$40","",R.drawable.headphones);
        productList.add(product2);
        Product product3=new Product("appel phone","$1000","$2000",R.drawable.iphone);
        productList.add(product3);
        Product product4=new Product("T-shirt","$50","",R.drawable.tshirt);
        productList.add(product4);
        Product product5=new Product("shoes","$70","",R.drawable.shoes);
        productList.add(product5);
        Product product6=new Product("car","$450","",R.drawable.car);
        productList.add(product6);
        Product product7=new Product("pc","$150","$250",R.drawable.pc);
        productList.add(product7);
        Product product8=new Product("home","$2500","",R.drawable.smarthome);
        productList.add(product8);
        Product product9=new Product("food","$20","",R.drawable.food);
        productList.add(product9);

        productAdapter.notifyDataSetChanged();
    }
    private void opencolorpicker() {
        ColorPicker colorPicker=new ColorPicker(Details_product.this);
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
                pickColor.setText("Done");
            }

            @Override
            public void onCancel() {
                pickColor.setText("Pick a color");
            }
        });
    }

    @Override
    public void onClick(View v) {
        NumberPicker numberPicker;
        TextView productName,price,oldPrice;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                Details_product.this, R.style.BottomSheetDialogTheme );
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(
                R.layout.add_producttocart_bottom_sheet,
                (LinearLayout) bottomSheetDialog.findViewById(R.id.bottomSheetContainerAddProductToCartID)
        );
        bottomSheetDialog.setContentView(bottomSheetView);
        productName=bottomSheetView.findViewById(R.id.productNameIBottomShetID);
        price=bottomSheetView.findViewById(R.id.productPriceBottomSheetID);
        oldPrice=bottomSheetView.findViewById(R.id.productOldPriceBottomSheetID);
        if(extras != null){
            productName.setText(extras.getString("name"));
            price.setText(extras.getString("price"));
            oldPrice.setText(extras.getString("oldPrice"));
            oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//            image.setImageResource(extras.getInt("image"));

        }
        numberPicker=bottomSheetView.findViewById(R.id.numberProductID);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
//        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                Toast.makeText(bottomSheetView.getContext(),newVal,Toast.LENGTH_SHORT).show();
//            }
//        });
        pickColor = bottomSheetView.findViewById(R.id.buttonPickColorsID);
        pickColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencolorpicker();
            }
        });
        bottomSheetDialog.show();
    }
}
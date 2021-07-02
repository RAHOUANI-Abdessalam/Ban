package com.example.ban;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ban.Controller.ProductAdapter;
import com.example.ban.Model.Product;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;



public class Home_fragment extends Fragment implements View.OnClickListener {
    
    private List<Product> productList =new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private CardView womenCard,menCard,phonesCard,pcCard,sportsCard,smartHomeCard,babytoyCard;
    private TextView recomendedtext;
    private EditText search;

    SliderView sliderView;
    int[] images = {R.drawable.mensfashion,R.drawable.womenfashoin,R.drawable.sport,R.drawable.phones,R.drawable.watch,R.drawable.pc};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home_fragment, container, false);

        recomendedtext = v.findViewById(R.id.recomendedTextID);
        search=v.findViewById(R.id.searchEditText_id);
        recomendedtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),RecomendedProducts.class);
                startActivity(intent);
            }
        });
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String searchtxt= search.getText().toString();
                    if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                        if(searchtxt.isEmpty()){
                            alert("Insert a product name !!");
                        }else{
                            Intent intent=new Intent(getActivity(),Search_results.class);
                            intent.putExtra("wordSearch",searchtxt);
                            startActivity(intent);
                            search.setText(null);
                            return true;
                        }
                    }
                return false;
            }
        });
        womenCard = v.findViewById(R.id.womencardID);
        menCard = v.findViewById(R.id.mencardID);
        phonesCard = v.findViewById(R.id.phonecardID);
        pcCard = v.findViewById(R.id.pccardID);
        sportsCard = v.findViewById(R.id.sportscardID);
        smartHomeCard = v.findViewById(R.id.smarthomecardID);
        babytoyCard = v.findViewById(R.id.babytoycardID);

        womenCard.setOnClickListener(this);
        menCard.setOnClickListener(this);
        phonesCard.setOnClickListener(this);
        pcCard.setOnClickListener(this);
        sportsCard.setOnClickListener(this);
        smartHomeCard.setOnClickListener(this);
        babytoyCard.setOnClickListener(this);

        sliderView= v.findViewById(R.id.imageSlider);
        SliderAdapter sliderAdapter=new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        recyclerView = v.findViewById(R.id.productHomeRecyclerViewId);
        productAdapter= new ProductAdapter(v.getContext(),productList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);

        Productdata();
        return v;
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
        Product product10=new Product("hand back","$90","$120",R.drawable.womenfashoin);
        productList.add(product10);
        Product product11=new Product("hand back","$90","$120",R.drawable.womenfashoin);
        productList.add(product11);
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        TextView categoriesBottomText;

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                getActivity(), R.style.BottomSheetDialogTheme
        );
        View bottomSheetView = LayoutInflater.from(getContext()).inflate(
                R.layout.categories_bottom_sheet,
                (LinearLayout) bottomSheetDialog.findViewById(R.id.bottomSheetContainer)
        );
        bottomSheetDialog.setContentView(bottomSheetView);
        categoriesBottomText= bottomSheetView.findViewById(R.id.categoriesBottomSheetTextId);
        switch (v.getId())
        {
            case R.id.womencardID :
                categoriesBottomText.setText("Women's Fashion");
                break;
            case R.id.mencardID:
                categoriesBottomText.setText("Men's Fashion");
                break;
            case R.id.phonecardID :
                categoriesBottomText.setText("Phons");
                break;
            case R.id.pccardID :
                categoriesBottomText.setText("PC");
                break;
            case R.id.sportscardID :
                categoriesBottomText.setText("Sports");
                break;
            case R.id.smarthomecardID :
                categoriesBottomText.setText("Smart Homes");
                break;
            case R.id.babytoycardID :
                categoriesBottomText.setText("Baby Toys");
                break;
        }
        bottomSheetDialog.show();
    }

    private void alert(String str){
        AlertDialog alertDialog=new AlertDialog.Builder(getContext()).setTitle("Alert")
                .setMessage(str)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        alertDialog.show();
    }

}
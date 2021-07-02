package com.example.ban;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ban.Controller.ProductAdapter;
import com.example.ban.Model.Product;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class Search_results extends AppCompatActivity {

    private List<Product> productList =new ArrayList<>();
    private RecyclerView recyclerViewSearch;
    private ProductAdapter productAdapter;
    private EditText search;
    private Bundle extras;
    TextView result,nmbrResults;
    ImageView filterIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        extras = getIntent().getExtras();
        result=findViewById(R.id.namePageID);
        result.setText(extras.getString("wordSearch"));
        filterIcon=findViewById(R.id.bell_id);
        filterIcon.setImageResource(R.drawable.ic_baseline_filter_alt_24);
        filterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Search_results.this,Filter.class);
                startActivity(intent);
            }
        });
        nmbrResults= findViewById(R.id.txtresultsNumberID);
        search= findViewById(R.id.searchEditText_id);
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String searchtxt= search.getText().toString();
                if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    if(searchtxt.isEmpty()){
                        alert("Insert a product name !!");
                    }else{
                        search.setText(null);
                        return true;
                    }
                }
                return false;
            }
        });

        recyclerViewSearch = findViewById(R.id.productHomeRecyclerViewId);
        productAdapter= new ProductAdapter(getApplicationContext(),productList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerViewSearch.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSearch.setAdapter(productAdapter);
        Productdata();
        nmbrResults.setText(productAdapter.getItemCount() + " Items");
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
    private void alert(String str){
        AlertDialog alertDialog=new AlertDialog.Builder(Search_results.this).setTitle("Alert")
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
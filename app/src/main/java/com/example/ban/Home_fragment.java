package com.example.ban;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ban.Controller.ProductAdapter;
import com.example.ban.Model.Product;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Home_fragment extends Fragment implements View.OnClickListener {

    private List<Product> productList =new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private CardView womenCard,menCard,phonesCard,pcCard,sportsCard,smartHomeCard,babytoyCard;
    private TextView recomendedtext;
    private EditText search;
    private static  final String BASE_URL = "http://10.0.2.2/loginregister/get_products2.php";
    private static  final String BASE_URL1 = "http://10.0.2.2/loginregister/get_products_from_categorie.php";
    private static  final String BASE_URL2 = "http://10.0.2.2/loginregister/get_products_from_catalogue.php";
    private static  final String SHAREDUSERID = "userid";
    private static  final String TEXT = "userid";


    SharedPreferences sharedPreferences,sharedPreferences2;
    int user_id;
    private Bundle extras;

    SliderView sliderView;
    int[] images = {R.drawable.mensfashion,R.drawable.womenfashoin,R.drawable.sport,R.drawable.phones,R.drawable.watch,R.drawable.pc};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home_fragment, container, false);

        sharedPreferences = getActivity().getSharedPreferences(SHAREDUSERID, Context.MODE_PRIVATE);
//        String loginStatus = sharedPreferences.getString(getResources().getString(R.string.prefLoginState),"");

        sharedPreferences2 = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String loginStatus = sharedPreferences2.getString(getResources().getString(R.string.prefLoginState),"");
        if(!loginStatus.equals("loggedin")){
            extras = getActivity().getIntent().getExtras();
            user_id=extras.getInt("user_id");
            Toast.makeText(getActivity(),"Your ID in Home : " +user_id,Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString(TEXT,String.valueOf(user_id));
            editor.apply();

            SharedPreferences.Editor editor2= sharedPreferences2.edit();
            editor2.putString(getResources().getString(R.string.prefLoginState),"loggedin");
            editor2.apply();
            Toast.makeText(getActivity(),"Your ID is storing : " +user_id,Toast.LENGTH_LONG).show();
        }else{
            String userID = sharedPreferences.getString(TEXT,"");
            if(userID.isEmpty()) {
                extras = getActivity().getIntent().getExtras();
                user_id = extras.getInt("user_ID");
                Toast.makeText(getActivity(), "Your ID in Home : " + user_id, Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(TEXT, String.valueOf(user_id));
                editor.apply();
                Toast.makeText(getActivity(), "Your ID is storing : " + user_id, Toast.LENGTH_LONG).show();
            }
        }



        recomendedtext = v.findViewById(R.id.recomendedTextID);
        search=v.findViewById(R.id.searchEditText_id);
        recomendedtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sharedPreferences = getActivity().getSharedPreferences(SHAREDUSERID, Context.MODE_PRIVATE);
                int user_id2 = Integer.parseInt(sharedPreferences.getString(TEXT, ""));

                Intent intent=new Intent(getActivity(),RecomendedProducts.class);
                intent.putExtra("user_id",user_id2);
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
        productAdapter= new ProductAdapter(getContext(),productList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);

//        Productdata();
        int user_id2 = Integer.parseInt(sharedPreferences.getString(TEXT, ""));

        getProductsFromCategories(user_id2);
        getProductsFromCatalogue(user_id2);
        getProducts();



        return v;
    }

//    private void Productdata() {
//        Product product=new Product("watch","$250","$400",R.drawable.watch);
//        productList.add(product);
//        Product product2=new Product("head phones","$40","",R.drawable.headphones);
//        productList.add(product2);
//        Product product3=new Product("appel phone","$1000","$2000",R.drawable.iphone);
//        productList.add(product3);
//        Product product4=new Product("T-shirt","$50","",R.drawable.tshirt);
//        productList.add(product4);
//        Product product5=new Product("shoes","$70","",R.drawable.shoes);
//        productList.add(product5);
//        Product product6=new Product("car","$450","",R.drawable.car);
//        productList.add(product6);
//        Product product7=new Product("pc","$150","$250",R.drawable.pc);
//        productList.add(product7);
//        Product product8=new Product("home","$2500","",R.drawable.smarthome);
//        productList.add(product8);
//        Product product9=new Product("food","$20","",R.drawable.food);
//        productList.add(product9);
//        Product product10=new Product("hand back","$90","$120",R.drawable.womenfashoin);
//        productList.add(product10);
//        Product product11=new Product("hand back","$90","$120",R.drawable.womenfashoin);
//        productList.add(product11);
//        productAdapter.notifyDataSetChanged();
//    }

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

    private void getProducts(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(getActivity().getApplicationContext(), response.toString(),Toast.LENGTH_LONG).show();
                try {
                    JSONArray array = new JSONArray(response);
                    boolean exixte;
                    for(int i = 0 ; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);

                        int id = object.getInt("ID_prodect");
                        String productName = object.getString("product_name");
                        String price = object.getString("price");
                        String oldPrice = object.getString("old_price");
                        int fav = object.getInt("favorite");
                        String content = object.getString("content");
                        String image = object.getString("image");

                        Product product = new Product(id,productName,price,oldPrice,fav,content,image);
                        exixte=false;
                        for (Product p:productList
                        ) {
                            if(product.getId()==p.getId()){
                                exixte=true;
                            }
                        }
                        if(exixte==false){
                            productList.add(product);
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity().getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
                }


                productAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }
    private void getProductsFromCategories(int ID){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(getActivity().getApplicationContext(), response.toString(),Toast.LENGTH_LONG).show();
                try {
                    JSONArray array = new JSONArray(response);
                    for(int i = 0 ; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);

                        int id = object.getInt("ID_prodect");
                        String productName = object.getString("product_name");
                        String price = object.getString("price");
                        String oldPrice = object.getString("old_price");
                        int fav = object.getInt("favorite");
                        String content = object.getString("content");
                        String image = object.getString("image");

                        Product product = new Product(id,productName,price,oldPrice,fav,content,image);
                        productList.add(product);

                    }
                }catch (Exception e){
                    Toast.makeText(getActivity().getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
                }

//                productAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("ID",String.valueOf(ID));
                return param;
            }
        };
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    private void getProductsFromCatalogue(int ID){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(getActivity().getApplicationContext(), response.toString(),Toast.LENGTH_LONG).show();
                try {
                    JSONArray array = new JSONArray(response);
                    boolean exixte;
                    for(int i = 0 ; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);

                        int id = object.getInt("ID_prodect");
                        String productName = object.getString("product_name");
                        String price = object.getString("price");
                        String oldPrice = object.getString("old_price");
                        int fav = object.getInt("favorite");
                        String content = object.getString("content");
                        String image = object.getString("image");

                        Product product = new Product(id,productName,price,oldPrice,fav,content,image);
                        exixte=false;
                        for (Product p:productList
                        ) {
                            if(product.getId()==p.getId()){
                                exixte=true;
                            }
                        }
                        if(exixte==false){
                            productList.add(product);
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity().getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
                }
//                productAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("ID",String.valueOf(ID));
                return param;
            }
        };
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

}
package com.example.ban;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ban.Controller.ProductAdapter;
import com.example.ban.Model.Product;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecomendedProducts extends AppCompatActivity {

    private List<Product> productList =new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    private static  final String BASE_URL = "http://10.0.2.2/loginregister/get_products_from_categorie.php";
    private static  final String BASE_URL2 = "http://10.0.2.2/loginregister/get_products_from_catalogue.php";

    int user_id;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomended_products);

        extras = getIntent().getExtras();
        user_id=extras.getInt("user_id");
        Toast.makeText(RecomendedProducts.this,"Your ID in Home : " +user_id,Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.productRecommendedRecyclerViewId);
        productAdapter= new ProductAdapter(getApplicationContext(),productList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);

        getProductsFromCategories(user_id);
        getProductsFromCatalogue(user_id);

    }
    private void getProductsFromCategories(int ID){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL, new Response.Listener<String>() {
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
                    Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
                }

//                productAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("ID",String.valueOf(ID));
                return param;
            }
        };
        Volley.newRequestQueue(RecomendedProducts.this).add(stringRequest);
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
                    Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
                }
                productAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("ID",String.valueOf(ID));
                return param;
            }
        };
        Volley.newRequestQueue(RecomendedProducts.this).add(stringRequest);
    }
}
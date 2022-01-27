package com.example.ban;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.ban.Model.Catalogue;
import com.example.ban.Model.Categorie;
import com.example.ban.Model.Product;
import com.google.android.material.slider.RangeSlider;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInterrogation extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    RangeSlider rangeSlider;
    TextView skipNow;
    Button validate;
    List<CheckBox> catalogue =new ArrayList<>();
//    List<CheckBox> catalogueIsChecked =new ArrayList<>();
    List<CheckBox> categories =new ArrayList<>();
//    List<CheckBox> categoriesIsChecked =new ArrayList<>();

    private List<Catalogue> catalogueList =new ArrayList<>();
    private List<Catalogue> catalogueListchecked =new ArrayList<>();
    private List<Categorie> categorieList =new ArrayList<>();
    private List<Categorie> categorieListchecked =new ArrayList<>();
    int user_id;
    private Bundle extras;

//    SharedPreferences sharedPreferences;

    CheckBox menCheck,womenCheck,phonesch,pcch,artch,babytoych,foodch,smarthomech,beautyAndperfumesch,healthch,sportsch,carAndMotorbikech,itAndHighTechch, gardeningch,diych,homeAppliancech,gamesAndAccessoriesch,
        clothingCheck,shoesch,jewerlych,watchesch,carsch,handbagsch,accessoriesch,girlsFashionch,otherch,mixch,pcAccessoriesch,phonech,technologiech,housesch,feedch;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interrogation);

//       sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
//       String loginStatus = sharedPreferences.getString(getResources().getString(R.string.prefLoginState),"");
//       if(loginStatus.equals("loggedin")){
//           Intent intent =new Intent(UserInterrogation.this,Login.class);
//           startActivity(intent);
//           finish();
//       }
       extras = getIntent().getExtras();
       user_id=extras.getInt("userID");
       Toast.makeText(UserInterrogation.this,"Your ID : " +user_id,Toast.LENGTH_SHORT).show();


        validate = findViewById(R.id.validateButtonId);
        skipNow=findViewById(R.id.skipForNowID);

        //catalogue checkbox
        womenCheck=findViewById(R.id.womenfashionCheckBox);
        menCheck=findViewById(R.id.menfashionCheckBox);
        phonesch=findViewById(R.id.phonesCheckBox);
        pcch=findViewById(R.id.pcCheckBox);
        artch=findViewById(R.id.artCheckBox);
        babytoych=findViewById(R.id.babytoyCheckBox);
        foodch=findViewById(R.id.foodCheckBox);
        smarthomech=findViewById(R.id.smarthomeCheckBox);
        beautyAndperfumesch=findViewById(R.id.beautyAndPerfumesCheckBox);
        healthch=findViewById(R.id.healthCheckBox);
        sportsch=findViewById(R.id.sportsCheckBox);
        carAndMotorbikech=findViewById(R.id.carAndMotorbikeCheckBox);
        itAndHighTechch=findViewById(R.id.itAndHighTechCheckBox);
        gardeningch=findViewById(R.id.gardeningCheckBox);
        diych=findViewById(R.id.diyCheckBox);
        homeAppliancech=findViewById(R.id.homeApplianceCheckBox);
        gamesAndAccessoriesch=findViewById(R.id.gamesAndAccessoriesCheckBox);
        
        catalogue.add(menCheck);
        catalogue.add(womenCheck);
        catalogue.add(phonesch);
        catalogue.add(pcch);
        catalogue.add(artch);
        catalogue.add(babytoych);
        catalogue.add(foodch);
        catalogue.add(smarthomech);
        catalogue.add(beautyAndperfumesch);
        catalogue.add(healthch);
        catalogue.add(sportsch);
        catalogue.add(carAndMotorbikech);
        catalogue.add(itAndHighTechch);
        catalogue.add(gardeningch);
        catalogue.add(diych);
        catalogue.add(homeAppliancech);
        catalogue.add(gamesAndAccessoriesch);

        //categories checkbox
        clothingCheck=findViewById(R.id.clothingCheckBox);
        shoesch=findViewById(R.id.shoesCheckBox);
        jewerlych=findViewById(R.id.jewerlyCheckBox);
        watchesch=findViewById(R.id.watchesCheckBox);
        carsch=findViewById(R.id.carsCheckBox);
        handbagsch=findViewById(R.id.handbagsCheckBox);
        accessoriesch=findViewById(R.id.accessoriesCheckBox);
        girlsFashionch=findViewById(R.id.girlsFashionCheckBox);
        otherch=findViewById(R.id.otherCheckBox);
        mixch=findViewById(R.id.mixCheckBox);
        pcAccessoriesch=findViewById(R.id.pcAccessoriesCheckBox);
        phonech=findViewById(R.id.phoneCheckBox);
        technologiech=findViewById(R.id.technologieCheckBox);
        housesch=findViewById(R.id.housesCheckBox);
        feedch=findViewById(R.id.feedCheckBox);

        categories.add(clothingCheck);
        categories.add(shoesch);
        categories.add(jewerlych);
        categories.add(watchesch);
        categories.add(carsch);
        categories.add(handbagsch);
        categories.add(accessoriesch);
        categories.add(girlsFashionch);
        categories.add(otherch);
        categories.add(mixch);
        categories.add(pcAccessoriesch);
        categories.add(phonech);
        categories.add(technologiech);
        categories.add(housesch);
        categories.add(feedch);


        int i=1,j=1;
       for (CheckBox c:catalogue
            ) {
           c.setOnCheckedChangeListener(this);
           Catalogue catalogue = new Catalogue(i,c.getText().toString());
           i++;
           catalogueList.add(catalogue);

       }

       for (CheckBox c:categories
       ) {
           c.setOnCheckedChangeListener(this);
           Categorie categorie = new Categorie(j,c.getText().toString());
           j++;
           categorieList.add(categorie);
       }


        validate.setOnClickListener(v -> {
            //ajoute d les checkboxes checked dans un vecteur
            for (CheckBox c:catalogue
            ) {
                if(c.isChecked()){
//                    catalogueIsChecked.add(c);
                    int p = catalogue.indexOf(c);
                    catalogueListchecked.add(catalogueList.get(p));
                }
            }

            for (CheckBox c:categories
            ) {
                if(c.isChecked()){
//                    categoriesIsChecked.add(c);
                    int k = categories.indexOf(c);
                    categorieListchecked.add(categorieList.get(k));
                }
            }

//            List<Integer> preferencesList =new ArrayList<>();
            for (Categorie c:categorieListchecked
                 ) {
                preferenceCategorie(user_id,c.getId());

            }
            for (Catalogue c:catalogueListchecked
            ) {
                preferenceCatalogue(user_id,c.getId());

            }

            Intent intent =new Intent(UserInterrogation.this,Home.class);
            intent.putExtra("user_id",user_id);
            startActivity(intent);
            finish();
        });
        skipNow.setOnClickListener(v -> {
            Intent intent =new Intent(UserInterrogation.this,Home.class);
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
            if(!validate.isEnabled()) {
                validate.setEnabled(true);
            }
        }else{
            boolean notchecked=true;
            for (CheckBox c:catalogue
                 ) {
                if (c.isChecked() && notchecked){
                    notchecked=false;
                }
            }
            for (CheckBox c:categories
            ) {
                if (c.isChecked() && notchecked){
                    notchecked=false;
                }
            }
            if(notchecked){
                validate.setEnabled(false);
            }
        }
    }



    private void preferenceCategorie(int id_user,int id_categorie){
//        ProgressDialog progressDialog = new ProgressDialog(UserInterrogation.this);
//        progressDialog.setCancelable(false);
//        progressDialog.setIndeterminate(false);
//        progressDialog.setTitle("Sending preferences ");
//        progressDialog.show();
        String uRl = "http://10.0.2.2/loginregister/send_categorie.php";
        StringRequest request = new StringRequest(Request.Method.POST, uRl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Successfully Registered")){
//                    progressDialog.dismiss();
//                    Toast.makeText(UserInterrogation.this,"Categorie Added",Toast.LENGTH_SHORT).show();
//                    Intent intent =new Intent(UserInterrogation.this,Login.class);
//                    startActivity(intent);
//                    finish();
                }else {
//                    progressDialog.dismiss();
                    Toast.makeText(UserInterrogation.this,response,Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
                Toast.makeText(UserInterrogation.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("ID",String.valueOf(id_user));
                param.put("ID_categorie",String.valueOf(id_categorie));
                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(UserInterrogation.this).addToRequestQueue(request);
    }




    private void preferenceCatalogue(int id_user,int id_catalogue){
//        ProgressDialog progressDialog = new ProgressDialog(UserInterrogation.this);
//        progressDialog.setCancelable(false);
//        progressDialog.setIndeterminate(false);
//        progressDialog.setTitle("Sending preferences ");
//        progressDialog.show();
        String uRl = "http://10.0.2.2/loginregister/send_catalogue.php";
        StringRequest request = new StringRequest(Request.Method.POST, uRl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Successfully Registered")){
//                    progressDialog.dismiss();
//                    Toast.makeText(UserInterrogation.this,"Catalogue Added",Toast.LENGTH_SHORT).show();
//                    Intent intent =new Intent(UserInterrogation.this,Login.class);
//                    startActivity(intent);
//                    finish();
                }else {
//                    progressDialog.dismiss();
                    Toast.makeText(UserInterrogation.this,response,Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
                Toast.makeText(UserInterrogation.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("ID",String.valueOf(id_user));
                param.put("ID_catalogue",String.valueOf(id_catalogue));
                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(UserInterrogation.this).addToRequestQueue(request);
    }



}
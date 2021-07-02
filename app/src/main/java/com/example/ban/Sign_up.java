package com.example.ban;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class Sign_up extends AppCompatActivity {

    private Button signin,register;
    private TextInputEditText fullname,email,password,confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fullname=findViewById(R.id.editTextnameId);
        email=findViewById(R.id.editTxtMaileId);
        password=findViewById(R.id.editTextPasswordid);
        confirmPassword=findViewById(R.id.editTxtConfirmPasswordid);
        signin=findViewById(R.id.buttSigninId);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Sign_up.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
        register=findViewById(R.id.buttRegisterId);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textfullName = fullname.getText().toString();
                String textEmail = email.getText().toString();
                String textPassword = password.getText().toString();
                String textConfirmPassword = confirmPassword.getText().toString();
                if(TextUtils.isEmpty(textfullName) || TextUtils.isEmpty(textEmail) ||
                        TextUtils.isEmpty(textPassword) ||TextUtils.isEmpty(textConfirmPassword)){
                    Toast.makeText(Sign_up.this,"All Fields required",Toast.LENGTH_SHORT).show();
                }else {
                    if(!textPassword.equals(textConfirmPassword)){
                        Toast.makeText(Sign_up.this,"Mismatching of passwords Please reverify",Toast.LENGTH_SHORT).show();
                    }else{
                        registerNewAccount(textfullName,textEmail,textPassword);
                    }
                }
            }
        });
    }
    private void registerNewAccount(String username,String email,String password){
        ProgressDialog progressDialog = new ProgressDialog(Sign_up.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Registering New Account");
        progressDialog.show();
        String uRl = "http://10.0.2.2/loginregister/register.php";
        StringRequest request = new StringRequest(Request.Method.POST, uRl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Successfully Registered")){
                    progressDialog.dismiss();
                    Toast.makeText(Sign_up.this,response,Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(Sign_up.this,Login.class);
                    startActivity(intent);
                    finish();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(Sign_up.this,response,Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Sign_up.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("username",username);
                param.put("email",email);
                param.put("psw",password);
                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(Sign_up.this).addToRequestQueue(request);
    }
}
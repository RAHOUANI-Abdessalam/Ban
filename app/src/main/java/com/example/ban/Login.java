package com.example.ban;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Login extends AppCompatActivity {

    private Button signup,frgtpsswd,login,guest;
    private TextInputEditText fullnameORemail,password;
    SharedPreferences sharedPreferences;
    int lastID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);


        signup=findViewById(R.id.buttSignupId);
        frgtpsswd=findViewById(R.id.buttForgetPasswordid);
        login=findViewById(R.id.buttRegisterId);
        guest=findViewById(R.id.guestButtonID);
        fullnameORemail=findViewById(R.id.editTxtnameLoginId);
        password=findViewById(R.id.editTxtPasswordLoginid);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Login.this,Sign_up.class);
                startActivity(intent);
                finish();
            }
        });
        frgtpsswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Login.this,Forget_password.class);
                startActivity(intent);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtEmail= fullnameORemail.getText().toString();
                String txtPassword = password.getText().toString();
                if(TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)){
                    Toast.makeText(Login.this,"All Fields Required",Toast.LENGTH_SHORT).show();
                }
                else{
                    login(txtEmail,txtPassword);
                }
            }
        });
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Login.this,Home.class);
                startActivity(intent);
                finish();
            }
        });
        String loginStatus = sharedPreferences.getString(getResources().getString(R.string.prefLoginState),"");
        if(loginStatus.equals("loggedin")){
            Intent intent =new Intent(Login.this,Home.class);
            startActivity(intent);
            finish();
        }
    }
    private void login(String email,String password){
        ProgressDialog progressDialog = new ProgressDialog(Login.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Login Account");
        progressDialog.show();
        String uRl = "http://10.0.2.2/loginregister/login2.php";
        StringRequest request = new StringRequest(Request.Method.POST, uRl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    lastID = Integer.valueOf(response);
                    progressDialog.dismiss();
//                    Toast.makeText(Login.this,response,Toast.LENGTH_SHORT).show();
                    Toast.makeText(Login.this,"Successfully Login",Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor= sharedPreferences.edit();
                    editor.putString(getResources().getString(R.string.prefLoginState),"loggedin");
                    editor.apply();

                    Intent intent =new Intent(Login.this,Home.class);
                    intent.putExtra("user_ID",lastID);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e){
                    progressDialog.dismiss();
                    Toast.makeText(Login.this,e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Login.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("email",email);
                param.put("psw",password);
                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(Login.this).addToRequestQueue(request);
    }
}
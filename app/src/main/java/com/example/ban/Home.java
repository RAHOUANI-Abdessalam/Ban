package com.example.ban;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {

    FloatingActionButton fabCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fabCart=findViewById(R.id.fab_id);
        fabCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Home.this,Cart.class);
                startActivity(intent);
            }
        });
        BottomNavigationView bottomNavigationView =findViewById(R.id.buttomNavigationViewId);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Home_fragment()).commit();

    }

      private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment= null;
            switch (item.getItemId())
            {
                case R.id.homeId :
                    selectedFragment = new Home_fragment();
                    break;
                case R.id.categoriesId:
                    selectedFragment = new CategoriesFragment();
                    break;
                case R.id.favoriteId :
                    selectedFragment = new FavoritesFragment();
                    break;
                case R.id.profileId :
                    selectedFragment = new ProfileFragment();
                    break;
            }
            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };
}
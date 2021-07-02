package com.example.ban;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileFragment extends Fragment {
    private CardView shippingAddress,paymentMethod,order,favorite,prefernceData,setting,logout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_profile, container, false);

        // initialisation component by ID
//        shippingAddress = v.findViewById(R.id.shippingAdrsCardViewId);
//        paymentMethod=v.findViewById(R.id.paymentMethodCardViewId);
//        order=v.findViewById(R.id.ordersCardViewId);
//        setting=v.findViewById(R.id.settingCardViewId);
        logout=v.findViewById(R.id.exitCardViewId);
        prefernceData=v.findViewById(R.id.prefernceDataCardViewId);
        favorite=v.findViewById(R.id.favoritesCardViewId);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new FavoritesFragment();
                replaceFragment(fragment);
            }
        });
        prefernceData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),UserInterrogation.class);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getResources().getString(R.string.prefLoginState),"loggedout");
                editor.apply();
                Intent intent=new Intent(getActivity(),Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return v;
    }
    public void replaceFragment(Fragment someFragment) {
        assert getFragmentManager() != null;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
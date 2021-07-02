package com.example.ban;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ban.Controller.ProductAdapter;
import com.example.ban.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    private List<Product> productList =new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private Bundle extras;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_favorites, container, false);


        recyclerView = v.findViewById(R.id.productHomeRecyclerViewId);
        productAdapter= new ProductAdapter(v.getContext(),productList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(productAdapter);

//        Product product=new Product(productName,price,oldPrice,R.drawable.phones);
//        productList.add(product);
//        productAdapter.notifyDataSetChanged();

        return v;
    }
}
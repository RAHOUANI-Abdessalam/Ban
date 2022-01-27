package com.example.ban.Controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ban.FavoritesFragment;
import com.example.ban.Model.Product;
import com.example.ban.R;
import com.example.ban.View.Details_product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private List<Product> productList;
    private Context context;


    public ProductAdapter(Context context,List<Product> productList) {
        this.productList = productList;
        this.context= context;
    }

    @NonNull

    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getProductName());
        holder.price.setText(product.getPrice());
        holder.oldPrice.setText(product.getOldPrice());
//        holder.image.setImageResource(product.getImage());
        Glide.with(context).load(product.getImageSTR()).into(holder.image);
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView productName,price,oldPrice;
        private ImageView image ;
        private Button favorite;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            productName = itemView.findViewById(R.id.productNameID);
            price = itemView.findViewById(R.id.productPriceID);
            oldPrice=itemView.findViewById(R.id.productOldPriceID);
            oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            image=itemView.findViewById(R.id.productMainImageID);
            favorite=itemView.findViewById(R.id.favoriteCheckBoxID);
            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(1==1)
                    {
                        favorite.setBackgroundResource(R.drawable.ic_baseline_favorite_24_selected);
                    }else{
                        favorite.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24);
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            int position =getAdapterPosition();
            Product product = productList.get(position);
            Intent intent = new Intent(context, Details_product.class);
            intent.putExtra("name",product.getProductName());
            intent.putExtra("price",product.getPrice());
            intent.putExtra("oldPrice",product.getOldPrice());
            intent.putExtra("image",product.getImage());
            context.startActivity(intent);
        }
    }
}
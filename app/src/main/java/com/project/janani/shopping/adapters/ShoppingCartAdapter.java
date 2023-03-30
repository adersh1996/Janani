package com.project.janani.shopping.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.janani.shopping.R;

import java.util.ArrayList;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHolder> {
    int[] image = {R.drawable.shopping, R.drawable.shopping, R.drawable.shopping, R.drawable.shopping};



    Context context;


    public ShoppingCartAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_cart_custom_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context).load(image[position]).into(holder.ivProductImage);
    }

    @Override
    public int getItemCount() {
        return image.length;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView cvProductImage;
        private ImageView ivProductImage;
        private TextView tvProductTitle;
        private TextView tvProductCategory;
        private TextView tvRsSign;
        private TextView tvProductPrice;
        private CardView cvAddButton;
        private CardView cvRemoveButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cvProductImage = itemView.findViewById(R.id.cv_product_image);
            ivProductImage = itemView.findViewById(R.id.iv_product_image);
            tvProductTitle = itemView.findViewById(R.id.tv_product_title);
            tvProductCategory = itemView.findViewById(R.id.tv_product_category);
            tvRsSign = itemView.findViewById(R.id.tv_rs_sign);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);
            cvAddButton = itemView.findViewById(R.id.cv_add_button);
            cvRemoveButton = itemView.findViewById(R.id.cv_remove_button);

        }
    }
}

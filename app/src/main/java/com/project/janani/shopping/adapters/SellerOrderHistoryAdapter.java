package com.project.janani.shopping.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.project.janani.shopping.R;
import com.project.janani.shopping.model.Root;

public class SellerOrderHistoryAdapter extends RecyclerView.Adapter<SellerOrderHistoryAdapter.MyViewHolder> {

    Root root;
    Context context;


    public SellerOrderHistoryAdapter(Root root, Context context) {
        this.root = root;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_view_seller_order_history, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.sellerProductName.setText(root.product_details.get(position).name);
        Glide.with(context).load(root.product_details.get(position).image1).into(holder.sellerProductImage);

    }

    @Override
    public int getItemCount() {
        return root.product_details.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ShapeableImageView sellerProductImage;
        private TextView sellerProductName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);

        }

        private void initView(View itemView) {
            sellerProductImage = itemView.findViewById(R.id.seller_product_image);
            sellerProductName = itemView.findViewById(R.id.seller_product_name);
        }

    }

}

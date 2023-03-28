package com.project.janani.shopping.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.janani.shopping.R;
import com.project.janani.shopping.model.Root;

public class SellerProductListAdapter extends RecyclerView.Adapter<SellerProductListAdapter.MyViewHolder> {

    Root root;
    Context context;


    public SellerProductListAdapter(Root root, Context context) {
        this.root = root;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_view_item_seller_product_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      //  holder.sellerProductName.setText(root.product_details.get(position).);
        Glide.with(context).load(root.product_details.get(position).image1).into(holder.sellerProductImage);
        holder.sellerProductPrice.setText(root.product_details.get(position).mrp);
    }

    @Override
    public int getItemCount() {
        return root.product_details.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView sellerProductImage;
        private TextView sellerProductName;
        private TextView sellerProductPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);

        }
        private void initView(View itemView) {
            sellerProductImage = itemView.findViewById(R.id.seller_product_image);
            sellerProductName = itemView.findViewById(R.id.seller_product_name);
            sellerProductPrice = itemView.findViewById(R.id.seller_product_price);
        }
    }
}

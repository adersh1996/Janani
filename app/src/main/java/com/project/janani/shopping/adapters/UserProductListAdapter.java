package com.project.janani.shopping.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.janani.shopping.R;
import com.project.janani.shopping.model.Root;

public class UserProductListAdapter extends RecyclerView.Adapter<UserProductListAdapter.MyViewHolder> {
    Context context;
    Root root;
    int row_index = -1;
    int[] images = { R.drawable.shopping, R.drawable.shopping, R.drawable.shopping, R.drawable.shopping};

    public UserProductListAdapter(Context context, Root root) {
        this.context = context;
        this.root = root;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_display_custom_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        Glide.with(context).load(root.product_details.get(position).image1).into(holder.ivProductImage);
//        holder.tvProductPrice.setText(root.product_details.get(position).mrp);
        Glide.with(context).load(images[position]).into(holder.ivProductImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();

            }
        });
        if (row_index == position) {
            holder.tvProductTitle.setTextColor(Color.parseColor("#757070"));
            holder.tvProductPrice.setTextColor(Color.parseColor("#757070"));
            holder.rlHolderView.setBackgroundColor(Color.parseColor("#5FCCA2"));
            holder.rlMainRelativeView.setBackgroundColor(Color.parseColor("#EEF3F1"));

        } else {
            holder.tvProductTitle.setTextColor(Color.parseColor("#414D48"));
            holder.tvProductPrice.setTextColor(Color.parseColor("#414D48"));
            holder.rlHolderView.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.rlMainRelativeView.setBackgroundColor(Color.parseColor("#CAC8C8"));
        }

    }

    @Override
    public int getItemCount() {
        return images.length;
//        return root.product_details.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rlHolderView, rlMainRelativeView;
        private ImageView ivProductImage;
        private TextView tvProductTitle;
        private TextView tvProductPrice;
        private RatingBar ratingBar;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rlHolderView = itemView.findViewById(R.id.rl_holder_view);
            ivProductImage = itemView.findViewById(R.id.iv_product_image);
            tvProductTitle = itemView.findViewById(R.id.tv_product_title);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            rlMainRelativeView = itemView.findViewById(R.id.rl_main_relative_view);
        }

    }
}

package com.project.janani.shopping.adapters;


import static androidx.core.content.ContextCompat.getDrawable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.engine.Resource;
import com.project.janani.shopping.R;

import org.w3c.dom.Text;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {
    int row_index = -1;
    Context context;

    public CategoriesAdapter(Context context) {
        this.context = context;
    }

    String[] productCategories = {"All Items", "Period Pads", "Tampons", "Menstrual Cups", "Period Pants"};

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_custom_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.categoryTitle.setText(productCategories[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();

            }
        });
        if (row_index == position) {
            holder.categoryTitle.setTextColor(Color.parseColor("#757070"));
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#5FCCA2"));

        } else {
            holder.categoryTitle.setTextColor(Color.parseColor("#414D48"));
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }


    }

    @Override
    public int getItemCount() {
        return productCategories.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryTitle;
        RelativeLayout relativeLayout;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.tv_category_title);
            cardView = itemView.findViewById(R.id.cv_holder_card);
            relativeLayout = itemView.findViewById(R.id.rl_holder_view);
        }
    }
}

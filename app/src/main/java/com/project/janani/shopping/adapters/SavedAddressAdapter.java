package com.project.janani.shopping.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.janani.shopping.R;
import com.project.janani.shopping.SavedAddressClass;

import java.util.ArrayList;

public class SavedAddressAdapter extends RecyclerView.Adapter<SavedAddressAdapter.MyViewHolder> {
    Context context;


    public SavedAddressAdapter() {
    }

    public SavedAddressAdapter(Context context, ArrayList<SavedAddressClass> exampleList) {
        this.context = context;
        mExampleList = exampleList;
    }

    private ArrayList<SavedAddressClass> mExampleList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_address_custom_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SavedAddressClass currentItem = mExampleList.get(position);
        holder.tvDisplayUsername.setText(currentItem.getUserName());
        holder.tvDisplayUserAddress.setText(currentItem.getUserAddress());
        holder.tvDisplayUserPhone.setText(currentItem.getUserPhoneNumber());
        holder.tvDisplayUserPinCode.setText(currentItem.getUserPinCode());
        holder.tvDisplayUserCity.setText(currentItem.getUserCity());
        holder.tvDisplayUserState.setText(currentItem.getUserState());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences selectAddressPreference = context.getSharedPreferences("selectAddress", context.MODE_PRIVATE);
                SharedPreferences.Editor editor = selectAddressPreference.edit();
                editor.putString("select_username", currentItem.getUserName());
                editor.putString("select_address", currentItem.getUserAddress());
                editor.putString("select_pin_code", currentItem.getUserPinCode());
                editor.putString("select_city", currentItem.getUserCity());
                editor.putString("select_state", currentItem.getUserState());
                editor.putString("select_phone_number", currentItem.getUserPhoneNumber());
                editor.apply();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvDisplayUsername;
        TextView tvDisplayUserAddress;
        TextView tvDisplayUserPhone;
        TextView tvDisplayUserPinCode;
        TextView tvDisplayUserCity;
        TextView tvDisplayUserState;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            tvDisplayUsername = itemView.findViewById(R.id.tv_display_username);
            tvDisplayUserAddress = itemView.findViewById(R.id.tv_display_user_address);
            tvDisplayUserPhone = itemView.findViewById(R.id.tv_display_user_phone);
            tvDisplayUserPinCode = itemView.findViewById(R.id.tv_display_user_pin_code);
            tvDisplayUserCity = itemView.findViewById(R.id.tv_display_user_city);
            tvDisplayUserState = itemView.findViewById(R.id.tv_display_user_state);
        }
    }
}

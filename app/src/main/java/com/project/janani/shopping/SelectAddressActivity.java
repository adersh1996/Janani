package com.project.janani.shopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.janani.shopping.adapters.SavedAddressAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SelectAddressActivity extends AppCompatActivity {
    ArrayList<SavedAddressClass> savedAddressList;
    private RecyclerView savedAddressView;
    private SavedAddressAdapter savedAddressAdapter;
    private Button btAddAddressButton;
    private TextView tvAllClearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        initView();
        loadData();
        buildRecyclerView();


        btAddAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EnterAddressActivity.class));
            }
        });
        tvAllClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedAddressList = new ArrayList<>();
                SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(savedAddressList);
                editor.putString("task list", json);
                editor.apply();
                Toast.makeText(SelectAddressActivity.this, "saved", Toast.LENGTH_SHORT).show();
                buildRecyclerView();
            }
        });

    }

    public void buildRecyclerView() {

        savedAddressView = findViewById(R.id.rv_saved_address_view);
        savedAddressView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        savedAddressAdapter = new SavedAddressAdapter(getApplicationContext(), savedAddressList);
        savedAddressView.setLayoutManager(linearLayoutManager);
        savedAddressView.setAdapter(savedAddressAdapter);
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<SavedAddressClass>>() {
        }.getType();


        savedAddressList = gson.fromJson(json, type);
        if (savedAddressList == null) {
            savedAddressList = new ArrayList<>();
        }
        EnterAddressActivity enterAddressActivity = new EnterAddressActivity(savedAddressList);
    }

    private void initView() {
        tvAllClearButton = findViewById(R.id.tv_all_clear_button);
        btAddAddressButton = findViewById(R.id.bt_add_new_address);
    }
}

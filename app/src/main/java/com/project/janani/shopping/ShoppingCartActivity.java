package com.project.janani.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.janani.shopping.adapters.ShoppingCartAdapter;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {

    private RecyclerView rvWishlistItems;
    private Button btPlaceOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        initView();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvWishlistItems.setLayoutManager(linearLayoutManager);
        ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(getApplicationContext());
        rvWishlistItems.setAdapter(shoppingCartAdapter);

        btPlaceOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SelectAddressActivity.class));
            }
        });
    }

    private void initView() {
        rvWishlistItems = findViewById(R.id.rv_wishlist_items);
        btPlaceOrderButton = findViewById(R.id.bt_place_order_button);
    }
}
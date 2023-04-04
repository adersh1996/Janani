package com.project.janani.shopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.project.janani.shopping.model.Root;
import com.project.janani.shopping.retrofit.APIClient;
import com.project.janani.shopping.retrofit.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivProductImage;
    private CardView cvSaveButton;

    private TextView tvProductName;
    private TextView tvProductPrice;
    private TextView tvDescriptionBody;
    private TextView btAddToCart;
    private TextView tvProductSellingPrice;
    private TextView tvMrpRsSymbol;
    private ImageButton ibSaveButton;
    public static String productId;
    int i = 0;
    private NumberPicker npQuantityPicker;
    private RelativeLayout relativeLayout;
    private ShimmerFrameLayout shimmerFrameLayout;

    public ProductDetailsActivity() {
    }

    public ProductDetailsActivity(String productId) {
        this.productId = productId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        initView();

        shimmerFrameLayout.startShimmer();


        npQuantityPicker.setMaxValue(50);
        npQuantityPicker.setMinValue(1);

        ibSaveButton.setOnClickListener(this);
        APIInterface apiProductDetails = APIClient.getClient().create(APIInterface.class);
        apiProductDetails.viewProductDetailsApiCall(productId).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root root = response.body();
                if (response.isSuccessful()) {
                    if (root.status) {
                        Glide.with(getApplicationContext()).load(root.product_details.get(0).image1).into(ivProductImage);
                        tvProductName.setText(root.product_details.get(0).name);
                        tvProductPrice.setText(root.product_details.get(0).mrp);
                        tvProductPrice.setPaintFlags(tvProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        tvMrpRsSymbol.setPaintFlags(tvProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                        tvDescriptionBody.setText(root.product_details.get(0).description);
                        tvProductSellingPrice.setText(root.product_details.get(0).selling_price);

                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        relativeLayout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(ProductDetailsActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(ProductDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences loginSharedPreferences = getSharedPreferences("loginShared", MODE_PRIVATE);
                String data = loginSharedPreferences.getString("userId", "default");
                APIInterface apiAddToCart = APIClient.getClient().create(APIInterface.class);
                apiAddToCart.addToCartApiCall(productId, data, String.valueOf(npQuantityPicker.getValue())).enqueue(new Callback<Root>() {
                    @Override
                    public void onResponse(Call<Root> call, Response<Root> response) {
                        Root root = response.body();
                        if (response.isSuccessful()) {
                            if (root.status) {
                                Toast.makeText(ProductDetailsActivity.this, root.message, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), UserHomeActivity.class));
                                finish();
                            } else {
                                Toast.makeText(ProductDetailsActivity.this, "adding failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Root> call, Throwable t) {
                        Toast.makeText(ProductDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initView() {
        ivProductImage = findViewById(R.id.iv_product_image);
        cvSaveButton = findViewById(R.id.cv_save_button);
        ibSaveButton = findViewById(R.id.iv_save_button);
        tvProductName = findViewById(R.id.tv_product_name);
        tvProductPrice = findViewById(R.id.tv_product_price);
        tvDescriptionBody = findViewById(R.id.tv_description_body);
        btAddToCart = findViewById(R.id.bt_add_to_cart);
        tvProductSellingPrice = findViewById(R.id.tv_product_selling_price);
        tvMrpRsSymbol = findViewById(R.id.tv_mrp_rs_symbol);
        npQuantityPicker = findViewById(R.id.np_quantity_picker);
        relativeLayout = findViewById(R.id.relativeLayout);
        shimmerFrameLayout = findViewById(R.id.shimmer_layout_product_detail);
    }

    @Override
    public void onClick(View view) {
        if (i == 0) {
            ibSaveButton.setBackgroundColor(Color.parseColor("#EE0D5C"));
            i++;
        } else if (i == 1) {
            ibSaveButton.setBackgroundColor(Color.parseColor("#D5CEA3"));
            i = 0;

        }
    }
}
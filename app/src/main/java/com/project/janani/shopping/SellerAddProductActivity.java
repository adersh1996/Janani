package com.project.janani.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

public class SellerAddProductActivity extends AppCompatActivity {


    private static final int PICK_IMAGE_REQUEST_CODE = 100;
    private static final int PICK_VIDEO_REQUEST_CODE = 200;

    private TextInputEditText productNameEditText;
    private TextInputEditText categoryEditText;
    private TextInputEditText descriptionEditText;
    private TextInputEditText mrpEditText;
    private TextInputEditText sellingPriceEditText;
    private TextInputEditText productImageOneEditText;
    private TextInputEditText productImageTwoEditText;
    private TextInputEditText productImageThreeEditText;
    private TextInputEditText productVideoEditText;
    private Button addProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_add_product);


        productNameEditText = findViewById(R.id.product_name_edittext);
        categoryEditText = findViewById(R.id.product_category_edittext);
        descriptionEditText = findViewById(R.id.product_description_edittext);
        mrpEditText = findViewById(R.id.product_mrp_edittext);
        sellingPriceEditText = findViewById(R.id.product_selling_price_edittext);
        productImageOneEditText = findViewById(R.id.product_image_one_edittext);
        productImageTwoEditText = findViewById(R.id.product_image_two_edittext);
        productImageThreeEditText = findViewById(R.id.product_image_three_edittext);
        productVideoEditText = findViewById(R.id.product_video_edittext);
        addProductButton = findViewById(R.id.add_product_button);

        productImageOneEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery(PICK_IMAGE_REQUEST_CODE);
            }
        });

        productImageTwoEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery(PICK_IMAGE_REQUEST_CODE);
            }
        });

        productImageThreeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery(PICK_IMAGE_REQUEST_CODE);
            }
        });

        productVideoEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery(PICK_VIDEO_REQUEST_CODE);
            }
        });

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the values from the input fields
                String productName = productNameEditText.getText().toString();
                String category = categoryEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String mrp = mrpEditText.getText().toString();
                String sellingPrice = sellingPriceEditText.getText().toString();
                String productImageOne = productImageOneEditText.getText().toString();
                String productImageTwo = productImageTwoEditText.getText().toString();
                String productImageThree = productImageThreeEditText.getText().toString();
                String productVideo = productVideoEditText.getText().toString();

                // Validate the input fields
                if (productName.trim().isEmpty()) {
                    productNameEditText.setError("Product name is required");
                    productNameEditText.requestFocus();
                    return;
                }

                if (category.trim().isEmpty()) {
                    categoryEditText.setError("Category is required");
                    categoryEditText.requestFocus();
                    return;
                }

                if (description.trim().isEmpty()) {
                    descriptionEditText.setError("Description is required");
                    descriptionEditText.requestFocus();
                    return;
                }

                if (mrp.trim().isEmpty()) {
                    mrpEditText.setError("MRP is required");


                    mrpEditText.requestFocus();
                    return;
                }

                if (sellingPrice.trim().isEmpty()) {
                    sellingPriceEditText.setError("Selling price is required");
                    sellingPriceEditText.requestFocus();
                    return;
                }

                if (productImageOne.trim().isEmpty()) {
                    productImageOneEditText.setError("Product image one is required");
                    productImageOneEditText.requestFocus();
                    return;
                }

                if (productImageTwo.trim().isEmpty()) {
                    productImageTwoEditText.setError("Product image two is required");
                    productImageTwoEditText.requestFocus();
                    return;
                }

                if (productImageThree.trim().isEmpty()) {
                    productImageThreeEditText.setError("Product image three is required");
                    productImageThreeEditText.requestFocus();
                    return;
                }

                if (productVideo.trim().isEmpty()) {
                    productVideoEditText.setError("Product video is required");
                    productVideoEditText.requestFocus();
                    return;
                }

                // Save the product to the database
                // TODO: Implement the code to save the product to the database

                // Show a success message
                Toast.makeText(SellerAddProductActivity.this, "Product added successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery(int requestCode) {
        Intent intent;
        if (requestCode == PICK_IMAGE_REQUEST_CODE) {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        } else if (requestCode == PICK_VIDEO_REQUEST_CODE) {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        } else {
            return;
        }
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/*", "video/*"});
        startActivityForResult(Intent.createChooser(intent, "Select File"), requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST_CODE) {
                Uri selectedImageUri = data.getData();
                File file = new File(selectedImageUri.getPath());
                String imagePath = file.getAbsolutePath();
                productImageOneEditText.setText(imagePath);
            } else if (requestCode == PICK_VIDEO_REQUEST_CODE) {
                Uri selectedVideoUri = data.getData();
                File file = new File(selectedVideoUri.getPath());
                String videoPath = file.getAbsolutePath();
                productVideoEditText.setText(videoPath);
            }
        }
    }
}

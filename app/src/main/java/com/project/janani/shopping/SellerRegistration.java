package com.project.janani.shopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SellerRegistration extends AppCompatActivity implements Validator.ValidationListener, View.OnClickListener {

    private ImageView ivDisplayImage;
    private CardView cvDpEditButton;
    @NotEmpty(message = "Enter your company name")
    private EditText etCompanyName;
    @NotEmpty(message = "Enter your phone number")
    private EditText etPhoneNumber;
    @NotEmpty(message = "Enter your Email ID")
    @Email
    private EditText etEmailId;
    @NotEmpty
    @Password(min = 8, message = "The password must have minimum 8 characters")
    private EditText etPassword;
    @ConfirmPassword
    private EditText etConfirmPassword;
    private Button btSaveButton;
    private File proImageFile;
    int selectPicture = 200;
    private Validator validator;
    protected boolean validated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_registration);
        initView();

        validator = new Validator(this);
        validator.setValidationListener(this);

        cvDpEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });
    }

    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityIfNeeded(Intent.createChooser(i, "Select Picture"), selectPicture);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == selectPicture) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    ivDisplayImage.setImageURI(selectedImageUri);
                }
            }
        }
    }

    private void initView() {
        ivDisplayImage = findViewById(R.id.iv_display_image);
        cvDpEditButton = findViewById(R.id.cv_dp_edit_button);
        etCompanyName = findViewById(R.id.et_company_name);
        etPhoneNumber = findViewById(R.id.et_phone_number);
        etEmailId = findViewById(R.id.et_email_id);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btSaveButton = findViewById(R.id.bt_save_button);

        btSaveButton.setOnClickListener(this);
    }

    protected boolean validate() {
        if (validator != null)
            validator.validate();
        return validated;           // would be set in one of the callbacks below
    }

    @Override
    public void onClick(View view) {
        validator.validate();
        if (validated) {
            SharedPreferences sharedPreferences = getSharedPreferences("myShared", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("companyName", etCompanyName.getText().toString());
            editor.putString("phoneNumber", etPhoneNumber.getText().toString());
            editor.putString("emailID", etEmailId.getText().toString());
            editor.putString("password", etPassword.getText().toString());
            editor.putString("confirmPassword", etConfirmPassword.getText().toString());
            editor.commit();
            Toast.makeText(SellerRegistration.this, "Data Saved!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), SellerAdditionalDetails.class));
        }
    }

    @Override
    public void onValidationSucceeded() {
        validated = true;
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        validated = false;

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);


            // Display error messages
            if (view instanceof Spinner) {
                Spinner sp = (Spinner) view;
                view = ((LinearLayout) sp.getSelectedView()).getChildAt(0);        // we are actually interested in the text view spinner has
            }

            if (view instanceof TextView) {
                TextView et = (TextView) view;
                et.setError(message);
            }
        }
    }
}
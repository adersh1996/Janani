package com.project.janani.shopping;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.project.janani.shopping.model.Root;
import com.project.janani.shopping.retrofit.APIClient;
import com.project.janani.shopping.retrofit.APIInterface;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerAdditionalDetails extends AppCompatActivity implements Validator.ValidationListener, View.OnClickListener {

    @NotEmpty(message = "Please enter company address")
    private EditText etCompanyAddress;
    @NotEmpty(message = "Please enter your license number")
    private EditText etLicenseNumber;
    private SwitchMaterial swUserKitSwitch;
    @NotEmpty(message = "Please enter your bank account number")
    private EditText etAccountNumber;
    @NotEmpty(message = "Please enter your branch address")
    private EditText etBranchAddress;
    @NotEmpty(message = "Please enter your IFSC code")
    private EditText etIFSCCode;
    private Button btRegisterSellerButton;
    private Validator validator;
    protected boolean validated;
    private static boolean switchState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_additional_details);
        initView();

        validator = new Validator(this);
        validator.setValidationListener(this);

        if (swUserKitSwitch.isChecked()) {
            // on below line we are setting text
            // if switch is checked.

        } else {
            // on below line we are setting the text
            // if switch is un checked


        }

        // on below line we are adding check change listener for our switch.
        swUserKitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // on below line we are checking
                // if switch is checked or not.
                if (isChecked) {
                    // on below line we are setting text
                    // if switch is checked.
                    switchState = true;
                    Toast.makeText(getApplicationContext(), String.valueOf(switchState), Toast.LENGTH_SHORT).show();

                } else {
                    // on below line we are setting text
                    // if switch is unchecked.
                    switchState = false;
                    Toast.makeText(getApplicationContext(), String.valueOf(switchState), Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    private void initView() {
        etCompanyAddress = findViewById(R.id.et_company_address);
        etLicenseNumber = findViewById(R.id.et_license_number);
        swUserKitSwitch = findViewById(R.id.sw_user_kit_switch);
        etAccountNumber = findViewById(R.id.et_account_number);
        etBranchAddress = findViewById(R.id.et_branch_address);
        etIFSCCode = findViewById(R.id.et_IFSC_code);
        btRegisterSellerButton = findViewById(R.id.bt_register_seller_button);

        btRegisterSellerButton.setOnClickListener(this);
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
            SharedPreferences preferences = getSharedPreferences("myShared", MODE_PRIVATE);

            RequestBody companyName = RequestBody.create(MediaType.parse("text/plain"), preferences.getString("companyName", "default"));
            RequestBody phoneNumber = RequestBody.create(MediaType.parse("text/plain"), preferences.getString("phoneNumber", "default"));
            RequestBody emailID = RequestBody.create(MediaType.parse("text/plain"), preferences.getString("emailID", "default"));
            RequestBody password = RequestBody.create(MediaType.parse("text/plain"), preferences.getString("password", "default"));
            RequestBody confirmPassword = RequestBody.create(MediaType.parse("text/plain"), preferences.getString("confirmPassword", "default"));


            RequestBody companyAddress = RequestBody.create(MediaType.parse("text/plain"), etCompanyAddress.getText().toString());
            RequestBody licenseNumber = RequestBody.create(MediaType.parse("text/plain"), etLicenseNumber.getText().toString());
            RequestBody accountNumber = RequestBody.create(MediaType.parse("text/plain"), etAccountNumber.getText().toString());
            RequestBody branchAddress = RequestBody.create(MediaType.parse("text/plain"), etBranchAddress.getText().toString());
            RequestBody ifscCode = RequestBody.create(MediaType.parse("text/plain"), etIFSCCode.getText().toString());
            RequestBody userKit = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(switchState));


            APIInterface api_seller_registration = APIClient.getClient().create(APIInterface.class);
            api_seller_registration.CALL_API_Seller_Registration(companyName, phoneNumber, emailID, password, confirmPassword, companyAddress, licenseNumber, userKit, accountNumber, branchAddress, ifscCode).enqueue(new Callback<Root>() {
                @Override
                public void onResponse(Call<Root> call, Response<Root> response) {

                }

                @Override
                public void onFailure(Call<Root> call, Throwable t) {
                    Toast.makeText(SellerAdditionalDetails.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
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
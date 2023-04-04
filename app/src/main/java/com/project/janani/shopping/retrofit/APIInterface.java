package com.project.janani.shopping.retrofit;

import com.project.janani.shopping.model.Root;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIInterface {
    @Multipart
    @POST("login_client.php")
    Call<Root> CALL_APIUserLogin(@Part("phone") RequestBody phone_number,
                                 @Part("password") RequestBody password);

    @Multipart
    @POST("user_reg.php")
    Call<Root> CALL_API_User_Registration(@Part MultipartBody.Part image,
                                          @Part("name") RequestBody name,
                                          @Part("mobile") RequestBody mobile,
                                          @Part("age") RequestBody age,
                                          @Part("email") RequestBody email,
                                          @Part("password") RequestBody password,
                                          @Part("confirmPassword") RequestBody confirm_password
    );

    @Multipart
    @POST("seller_reg.php")
    Call<Root> CALL_API_Seller_Registration(@Part("name") RequestBody name,
                                            @Part("mobile") RequestBody mobile,
                                            @Part("email") RequestBody email,
                                            @Part("password") RequestBody password,
                                            @Part("confirmPassword") RequestBody confirm_password,
                                            @Part("companyAddress") RequestBody company_address,
                                            @Part("licenseNumber") RequestBody license_number,
                                            @Part("bankAccount") RequestBody bank_account,
                                            @Part("branchAddress") RequestBody branch_address,
                                            @Part("IFSCCode") RequestBody IFSC_code,
                                            @Part("userKit") RequestBody user_kit
    );

    @FormUrlEncoded
    @POST("view_product_seller_id.php")
    Call<Root> viewProductsSellerApiCall(@Field("seller_id") String sellerId);



    @FormUrlEncoded
    @POST("view_products.php")
    Call<Root> viewProductsUserApiCall(@Field("product_id") String productId);

    @Multipart
    @POST("add_product.php")
    Call<Root> addProductSellerApiCall(@Part("seller_id")RequestBody sellerId,@Part("name")RequestBody name,
                                       @Part("description")RequestBody description,@Part("mrp")RequestBody mrp,
                                       @Part("selling_price")RequestBody sellingPrice,@Part("qty")RequestBody quantity,
                                       @Part MultipartBody.Part image1,@Part MultipartBody.Part image2,
                                       @Part MultipartBody.Part image3,@Part MultipartBody.Part video1);

    @FormUrlEncoded
    @POST("view_products.php")
    Call<Root> viewOrderHistorySellerApiCall(@Field("seller_id") String sellerId);


}

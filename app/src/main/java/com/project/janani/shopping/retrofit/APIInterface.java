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
    @POST("user_login.php")
    Call<Root> CALL_APIUserLogin(@Part("mobile") RequestBody phone_number,
                                 @Part("password") RequestBody password);


    @Multipart
    @POST("user_reg.php")
    Call<Root> CALL_API_User_Registration(
            @Part("name") RequestBody name,
            @Part("mobile") RequestBody mobile,
            @Part("age") RequestBody age,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("device_token") RequestBody deviceToken
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


    @POST("view_products.php")
    Call<Root> viewProductsUserApiCall();

    @FormUrlEncoded
    @POST("view_product_byProduct_id.php")
    Call<Root> viewProductDetailsApiCall(@Field("product_id") String product_id);

    @FormUrlEncoded
    @POST("add_cart.php")
    Call<Root> addToCartApiCall(@Field("product_id") String product_id, @Field("user_id") String user_id, @Field("qty") String quantity);


    @FormUrlEncoded
    @POST("view_cart.php")
    Call<Root> viewCartApiCall(@Field("user_id") String user_id);

    @POST("view_category.php")
    Call<Root> categoryListApiCall();

    @POST("view_faq.php")
    Call<Root> faqApiCall();

    @FormUrlEncoded
    @POST("order_history.php")
    Call<Root> orderHistoryApiCall(@Field("user_id") String user_id);

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


    @FormUrlEncoded
    @POST("product_filter.php")
    Call<Root> categoryFilterApiCall(@Field("category") String category);

    @FormUrlEncoded
    @POST("delete_cart.php")
    Call<Root> removeItemApiCall(@Field("cart_id") String cart_id);

    @FormUrlEncoded
    @POST("search_product.php")
    Call<Root> searchItemApiCall(@Field("term") String term);

    @FormUrlEncoded
    @POST("kit_button.php")
    Call<Root> userKitCheckOutApiCall(@Field("user_id") String user_id, @Field("lat") String latitude, @Field("log") String longitude, @Field("address") String address, @Field("phone") String phone, @Field("category") String category, @Field("product_id") String product_id);

    @FormUrlEncoded
    @POST("add_cart.php")
    Call<Root> placeOrderAPiCall(@Field("product_id") String product_id, @Field("user_id") String user_id, @Field("qty") String quantity);
}

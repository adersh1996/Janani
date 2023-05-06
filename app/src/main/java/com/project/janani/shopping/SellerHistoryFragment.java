package com.project.janani.shopping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.project.janani.shopping.model.Root;
import com.project.janani.shopping.retrofit.APIClient;
import com.project.janani.shopping.retrofit.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SellerHistoryFragment extends Fragment {


    private RecyclerView sellerOrderHistoryRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seller_history, container, false);
        initView(view);


        return view;
    }

    private void initView(View view) {
        sellerOrderHistoryRecyclerView = view.findViewById(R.id.seller_order_history_recycler_view);
    }

    private void sellerHistoryApiCall(String sellerId){

        APIInterface sellerHistoryApi = APIClient.getClient().create(APIInterface.class);
        sellerHistoryApi.viewOrderHistorySellerApiCall(sellerId).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()){
                    Root root = response.body();
                    if (root.status){

                    }
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });



    }
}
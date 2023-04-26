package com.project.janani.shopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.janani.shopping.adapters.CategoriesAdapter;
import com.project.janani.shopping.adapters.UserProductListAdapter;
import com.project.janani.shopping.model.Root;
import com.project.janani.shopping.retrofit.APIClient;
import com.project.janani.shopping.retrofit.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserHomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ScrollView scrollView;
    private ShimmerFrameLayout shimmerFrameLayout;
    private EditText etSearchBar;
    private RecyclerView rvCategoriesView;
    private RecyclerView rvProductListView;
    private FloatingActionButton btUserKitButton;
    public static String data;
    private ImageView ivSearchButton;

    public UserHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserHomeFragment newInstance(String param1, String param2) {
        UserHomeFragment fragment = new UserHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);
        initView(view);

        shimmerFrameLayout.startShimmer();

        rvProductListView.setNestedScrollingEnabled(false);

        SharedPreferences categorySharedPreference = getActivity().getSharedPreferences("category select", Context.MODE_PRIVATE);
        data = categorySharedPreference.getString("category", "default");

        categoryViewDisplay();
        productViewDisplay();

        ivSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchProduct();
            }
        });
        btUserKitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UserKitProductActivity.class));
            }
        });


        return view;
    }

    private void searchProduct() {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (!etSearchBar.getText().toString().isEmpty()) {
            intent.putExtra("searchTerm", etSearchBar.getText().toString());
            startActivity(intent);
        }
    }

    private void productViewDisplay() {

        APIInterface api = APIClient.getClient().create(APIInterface.class);
        api.viewProductsUserApiCall().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    Root root = response.body();
                    if (root.status) {

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                        rvProductListView.setLayoutManager(gridLayoutManager);
                        UserProductListAdapter userProductListAdapter = new UserProductListAdapter(getActivity(), root);
                        rvProductListView.setAdapter(userProductListAdapter);
                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        scrollView.setVisibility(View.VISIBLE);
//                        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
//                        rvProductListView.setLayoutManager(layoutManager);
//                        SellerProductListAdapter sellerProductListAdapter = new SellerProductListAdapter(root, getActivity());
//
//                        int spanCount = 2; // 3 columns
//                        int spacing = 50; // 50px
//                        boolean includeEdge = true;
//                        rvProductListView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
//                        rvProductListView.setAdapter(sellerProductListAdapter);
                    }
                } else {
                    Toast.makeText(getActivity(), "Server Failed", Toast.LENGTH_SHORT).show();
                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);

            }
        });
    }

    private void categoryViewDisplay() {


        APIInterface apiCategoryList = APIClient.getClient().create(APIInterface.class);
        apiCategoryList.categoryListApiCall().enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root root = response.body();
                if (response.isSuccessful()) {
                    if (root.status) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                        rvCategoriesView.setLayoutManager(linearLayoutManager);
                        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getActivity(), root);
                        rvCategoriesView.setAdapter(categoriesAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(View view) {

        etSearchBar = view.findViewById(R.id.et_search_bar);
        rvCategoriesView = view.findViewById(R.id.rv_categories_view);
        rvProductListView = view.findViewById(R.id.rv_product_list_view);
        btUserKitButton = view.findViewById(R.id.bt_user_kit_button);
        ivSearchButton = view.findViewById(R.id.iv_search_button);
        scrollView = view.findViewById(R.id.scroll_view);
        shimmerFrameLayout = view.findViewById(R.id.sl_user_home_shimmer_layout);
    }
}
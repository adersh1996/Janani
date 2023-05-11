package com.project.janani.shopping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.janani.shopping.adapters.UserProductListAdapter;
import com.project.janani.shopping.model.Root;
import com.project.janani.shopping.retrofit.APIClient;
import com.project.janani.shopping.retrofit.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PadsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PadsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rvProductListView;

    public PadsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PadsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PadsFragment newInstance(String param1, String param2) {
        PadsFragment fragment = new PadsFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pads, container, false);
        initView(view);
        rvProductListView.setNestedScrollingEnabled(false);
        productFilter();
        return view;
    }

    private void productFilter() {

        APIInterface apiProductDisplay = APIClient.getClient().create(APIInterface.class);
        apiProductDisplay.productCategoryFilterApiCall("sanitary pads").enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root root = response.body();
                if (response.isSuccessful()) {
                    if (root.status) {
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                        rvProductListView.setLayoutManager(gridLayoutManager);
                        UserProductListAdapter userProductListAdapter = new UserProductListAdapter(getActivity(), root);
                        rvProductListView.setAdapter(userProductListAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
    }

    private void initView(View view) {
        rvProductListView = view.findViewById(R.id.rv_product_list_view);
    }
}
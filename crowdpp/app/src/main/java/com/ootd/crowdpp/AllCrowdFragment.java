package com.ootd.crowdpp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ootd.crowdpp.Retrofits.CrowdModel;
import com.ootd.crowdpp.Retrofits.Result;
import com.ootd.crowdpp.Retrofits.RetrofitClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllCrowdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllCrowdFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllCrowdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllCrowdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllCrowdFragment newInstance(String param1, String param2) {
        AllCrowdFragment fragment = new AllCrowdFragment();
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
        View v = inflater.inflate(R.layout.fragment_all_crowd, container, false);

        recyclerView = v.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        getData();

        return v;
    }

    private void getData(){

        Call<ArrayList<CrowdModel>> call;
        call = RetrofitClient.getApiService().getAllCrowd();
        call.enqueue(new Callback<ArrayList<CrowdModel>>(){
            @Override
            public void onResponse(Call call, Response response) {
                if (response.code() == 200) {
                    ArrayList<CrowdModel> result = (ArrayList<CrowdModel>) response.body();
                    System.out.println(result.size());
                    for (int i=0; i<result.size(); i++) {
                        CrowdData crowd = new CrowdData();
                        crowd.setImage(result.get(i).getId());
                        crowd.setName(result.get(i).getName());
                        crowd.setIntroduction(result.get(i).getExplain());
                        crowd.setCrowdID(result.get(i).getId());
                        adapter.addItem(crowd);
                    }
                    adapter.notifyDataSetChanged();
                } else {

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("request fail", t.getMessage());
            }
        });
    }
}
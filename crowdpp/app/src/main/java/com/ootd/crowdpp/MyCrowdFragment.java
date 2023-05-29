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
import android.widget.ImageView;
import android.widget.TextView;

import com.ootd.crowdpp.Retrofits.CrowdModel;
import com.ootd.crowdpp.Retrofits.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyCrowdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCrowdFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private RecyclerAdapter adapter;
    ArrayList<CrowdData> CrowdDataArray;
    ImageView createCrowd;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyCrowdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyCrowdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyCrowdFragment newInstance(String param1, String param2) {
        MyCrowdFragment fragment = new MyCrowdFragment();
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
        // Inflate the layout for this fragment

        CrowdDataArray = new ArrayList<>();

        recyclerView = v.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter(CrowdDataArray, getContext());
        recyclerView.setAdapter(adapter);

        getData();

        return v;
    }

    private void getData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("id", "");
        Call<ArrayList<CrowdModel>> call;
        call = RetrofitClient.getApiService().getMyCrowd(userId);
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
                        crowd.setIntroduction(result.get(i).getDescription());
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
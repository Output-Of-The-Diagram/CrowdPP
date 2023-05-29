package com.ootd.crowdpp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
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
    ArrayList<CrowdData> CrowdDataArray;
    ImageView createCrowd;

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
        // + 버튼을 누를 시 모임 생성
        createCrowd = v.findViewById(R.id.createCrowd);
        createCrowd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog createCrowdDialog = new Dialog(getContext());
                createCrowdDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                createCrowdDialog.setContentView(R.layout.activity_create_crowd_popup);
                createCrowdDialog.show();

                EditText createCrowdName = createCrowdDialog.findViewById(R.id.createCrowdName);
                Button createCrowdButton = createCrowdDialog.findViewById(R.id.createCrowdButton);

                createCrowdButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String crowdName = createCrowdName.getText().toString();

//                이름 중복 검사 구현해줘잉(이름은 crowdName임)

                        boolean isDuplicatedCrowdName = false;
                        if (isDuplicatedCrowdName){
                            Toast.makeText(v.getContext(), "중복된 Crowd입니다", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(v.getContext(), "Crowd 생성이 완료되었습니다", Toast.LENGTH_SHORT).show();
//                            디비에 Crowd 추가해줘잉
                            createCrowdDialog.dismiss();
                        }
                    }
                });

            }
        });

        // RecyclerView를 통해 모임 리스트 나열
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
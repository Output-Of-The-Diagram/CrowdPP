package com.ootd.crowdpp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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
        CrowdData crowd1 = new CrowdData();
        crowd1.setImage(1);
        crowd1.setName("국민대 조기축구단");
        crowd1.setIntroduction("우리는 대한민국을 지배하러온 국민대 조기축구단이다");
        crowd1.setLeaderID("leader1");

        CrowdData crowd2 = new CrowdData();
        crowd2.setImage(1);
        crowd2.setName("1일1백준 정예용사");
        crowd2.setIntroduction("1일 1백준을 실천하지 않을 시 손모가지를 자른다");
        crowd2.setLeaderID("leader2");

        ArrayList<CrowdData> crowdData = new ArrayList<>();
        crowdData.add(crowd1);
        crowdData.add(crowd2);

        adapter.addItem(crowd1);
        adapter.addItem(crowd2);

        adapter.notifyDataSetChanged();
    }
}
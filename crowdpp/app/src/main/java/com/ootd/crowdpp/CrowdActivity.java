package com.ootd.crowdpp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.ootd.crowdpp.Retrofits.CrowdModel;
import com.ootd.crowdpp.Retrofits.Result;
import com.ootd.crowdpp.Retrofits.RetrofitClient;
import com.ootd.crowdpp.Retrofits.UserModel;

import java.lang.reflect.Member;
import java.sql.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrowdActivity extends AppCompatActivity {

    ArrayList<MembersData> members;
    int crowdId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crowd);
        Intent intent = getIntent();
        crowdId = intent.getIntExtra("crowdId", 0);
        System.out.println(Integer.toString(crowdId));

        getData(crowdId);



    }
    private void getData(int crowdId){
        members = new ArrayList<MembersData>();

        Call<ArrayList<UserModel>> call;
        call = RetrofitClient.getApiService().getAllMember(crowdId);
        call.enqueue(new Callback<ArrayList<UserModel>>(){
            @Override
            public void onResponse(Call call, Response response) {
                if (response.code() == 200) {
                    ArrayList<UserModel> result = (ArrayList<UserModel>) response.body();
                    for (int i=0; i<result.size(); i++) {
                        MembersData member = new MembersData(result.get(i).getName());
                        System.out.println(result.get(i).getName());
                        members.add(member);
                    }
                    ListView listview = findViewById(R.id.crowdMemberListview);
                    MembersAdapter membersAdapter = new MembersAdapter(getApplicationContext(), members);
                    listview.setAdapter(membersAdapter);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("request fail", t.getMessage());
            }
        });

//        MembersData member1 = new MembersData("Messi");
//        MembersData member2 = new MembersData("Ronaldo");
//        MembersData member3 = new MembersData("Holland");
//
//        members.add(member1);
//        members.add(member2);
//        members.add(member3);

    }


}
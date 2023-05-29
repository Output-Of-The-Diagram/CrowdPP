package com.ootd.crowdpp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.lang.reflect.Member;
import java.sql.Array;
import java.util.ArrayList;

public class CrowdActivity extends AppCompatActivity {

    ArrayList<MembersData> members;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crowd);

        getData();


        ListView listview = findViewById(R.id.crowdMemberListview);
        MembersAdapter membersAdapter = new MembersAdapter(this, members);
        listview.setAdapter(membersAdapter);


    }
    private void getData(){
        members = new ArrayList<MembersData>();
        MembersData member1 = new MembersData("Messi");
        MembersData member2 = new MembersData("Ronaldo");
        MembersData member3 = new MembersData("Holland");

        members.add(member1);
        members.add(member2);
        members.add(member3);

    }


}
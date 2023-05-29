package com.ootd.crowdpp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
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
    ImageView escapeCrowdButton;
    ImageView manageMembersButton;
    boolean isLeader;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crowd);
        Intent intent = getIntent();
        crowdId = intent.getIntExtra("crowdId", 0);
        System.out.println(Integer.toString(crowdId));

        getData(crowdId);

        escapeCrowdButton = findViewById(R.id.escapeCrowdButton);
        manageMembersButton = findViewById(R.id.manageMembersButton);
        escapeCrowdButton.setOnClickListener(new View.OnClickListener() { // 탈퇴, 추방 버튼 클릭
            @Override
            public void onClick(View v) {
                isLeader = false; // 리더인지 확인
                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
                String userId = sharedPreferences.getString("id", "");
                Call<Result> call;
                call = RetrofitClient.getApiService().isleader(userId, crowdId);
                call.enqueue(new Callback<Result>(){
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.code() == 200) {
                            Result result = response.body();
                            String msg = result.getMsg();
                            if (msg.equals("notLeader")) {
                                isLeader = false;
                            } else if (msg.equals("leader")) {
                                isLeader = true;
                            }
                            System.out.println(msg);
                        } else {
                        }
                    }
                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Log.e("request fail", t.getMessage());
                    }
                });

//                리더인지 확인하여 isLeader 설정
                if (!isLeader){ // 멤버가 버튼을 클릭하면 모임 탈퇴
                    AlertDialog.Builder builder = new AlertDialog.Builder(CrowdActivity.this);
                    builder.setMessage("정말 탈퇴하시겠습니까?");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            Call<Result> call;
                            call = RetrofitClient.getApiService().kickmember(userId, crowdId);
                            call.enqueue(new Callback<Result>(){
                                @Override
                                public void onResponse(Call<Result> call, Response<Result> response) {
                                    if (response.code() == 200) {
                                        Result result = response.body();
                                        String msg = result.getMsg();
                                        if (msg.equals("registered!")) {
                                        }
                                        System.out.println(msg);
                                    } else {
                                    }
                                }
                                @Override
                                public void onFailure(Call<Result> call, Throwable t) {
                                    Log.e("request fail", t.getMessage());
                                }
                            });
                            dialog.cancel();
                        }
                    });
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{ // 리더가 버튼을 클릭하면 모임 삭제
                    AlertDialog.Builder builder = new AlertDialog.Builder(CrowdActivity.this);
                    builder.setMessage("정말 Crowd를 삭제하시겠습니까?");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int whichButton){
                            Call<Result> call;
                            call = RetrofitClient.getApiService().deletecrowd(crowdId);
                            call.enqueue(new Callback<Result>(){
                                @Override
                                public void onResponse(Call<Result> call, Response<Result> response) {
                                    if (response.code() == 200) {
                                        Result result = response.body();
                                        String msg = result.getMsg();
                                        if (msg.equals("deleted!")) {
                                        }
                                        System.out.println(msg);
                                    } else {
                                    }
                                }
                                @Override
                                public void onFailure(Call<Result> call, Throwable t) {
                                    Log.e("request fail", t.getMessage());
                                }
                            });
//
                            dialog.cancel();
                        }
                    });
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
        });

        // 멤버 수락 및 거절 관리
        isLeader = true; // 리더인지 확인 필요
        if (isLeader){
            manageMembersButton.setVisibility(View.VISIBLE);
        }
        manageMembersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog arManageMembersDialog = new Dialog(CrowdActivity.this);
                arManageMembersDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                arManageMembersDialog.setContentView(R.layout.activity_accept_reply_members_popup);
                arManageMembersDialog.show();

                ArrayList<MembersData> arMembers = new ArrayList<MembersData>();

                //
                // response 구현 필요(테스트용 데이터 추가해둠)
                MembersData member1 = new MembersData("Messi");
                MembersData member2 = new MembersData("Ronaldo");
                MembersData member3 = new MembersData("Holland");
                arMembers.add(member1);
                arMembers.add(member2);
                arMembers.add(member3);
                //
                //

                ListView arListView = arManageMembersDialog.findViewById(R.id.arListView);
                MembersAdapter arMembersAdapter = new MembersAdapter(getApplicationContext(), arMembers);
                arListView.setAdapter(arMembersAdapter);

                Button acceptButton = arManageMembersDialog.findViewById(R.id.arAcceptButton);
                Button refuseButton = arManageMembersDialog.findViewById(R.id.arRefuseButton);
                acceptButton.setOnClickListener(new View.OnClickListener() { // 수락 버튼이 눌린 경우
                    @Override
                    public void onClick(View v) {
                        // 수락 완료. Crowd에 멤버 추가. 요청 제거.
                    }
                });

                refuseButton.setOnClickListener(new View.OnClickListener() { // 거절 버튼이 눌린 경우
                    @Override
                    public void onClick(View v) {
                        // 거절 완료. 요청 제거.
                    }
                });
            }
        });


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
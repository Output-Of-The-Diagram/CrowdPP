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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ootd.crowdpp.Retrofits.CrowdModel;
import com.ootd.crowdpp.Retrofits.Result;
import com.ootd.crowdpp.Retrofits.RetrofitClient;
import com.ootd.crowdpp.Retrofits.UserModel;

import org.w3c.dom.Text;

import java.lang.reflect.Member;
import java.sql.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrowdActivity extends AppCompatActivity {
    // Crowd의 기능들이 담겨있는 파일

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

        SharedPreferences sharedPreferences = getSharedPreferences("loginUserInfo", MODE_PRIVATE);
        String userId = sharedPreferences.getString("id", "");
        System.out.println(userId);
        System.out.println(userId);
        System.out.println(userId);
        Call<Result> call;
        call = RetrofitClient.getApiService().isLeader(sharedPreferences.getString("id", ""), crowdId);
        call.enqueue(new Callback<Result>(){
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.code() == 200) {
                    Result result = response.body();
                    String msg = result.getMsg();
                    System.out.println(msg);
                    System.out.println(msg);
                    System.out.println(msg);
                    if (msg.equals("notLeader")) {
                        isLeader = false;
                    } else if (msg.equals("leader")) {
                        isLeader = true;
                    }
                    if (isLeader) { // TODO: belong에 요청이 있을때만 버튼을 활성화시켜야 함(도전과제?)
                        manageMembersButton.setVisibility(View.VISIBLE);
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

        //TODO: isLeader를 처음에 받아와서 true/false를 설정하여 밑에를 다 구현하는게 깔끔할 것 같다... 일단 냅둠

        escapeCrowdButton = findViewById(R.id.escapeCrowdButton); // Crowd 탈퇴 및 삭제 버튼 (X 모양)
        manageMembersButton = findViewById(R.id.manageMembersButton); // Crowd에 신청한 멤버 관리 버튼 (톱니 모양)

        // Crowd 탈퇴, 삭제 버튼 클릭
        escapeCrowdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EscapeAndRemoveCrowd(); // 멤버라면 모임 탈퇴, 리더라면 모임 삭제
            }
        });


        // Crowd에 신청한 멤버 관리 버튼 클릭
        manageMembersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplyAndRefuseMembers(); // 멤버 관리
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
    // Crowd 탈퇴, 삭제와 관련된 기능
    public void EscapeAndRemoveCrowd(){
        // 리더인지 확인
        SharedPreferences sharedPreferences = getSharedPreferences("loginUserInfo", MODE_PRIVATE);
        String userId = sharedPreferences.getString("id", "");
        Call<Result> call;
        call = RetrofitClient.getApiService().isLeader(userId, crowdId);
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
                    System.out.println(msg);
                } else {
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("request fail", t.getMessage());
            }
        });


    }

    public void ApplyAndRefuseMembers(){
        Dialog arManageMembersDialog = new Dialog(CrowdActivity.this); // 신청을 넣은 멤버들의 리스트 화면
        arManageMembersDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        arManageMembersDialog.setContentView(R.layout.activity_accept_reply_members_popup);
        arManageMembersDialog.show();

        ArrayList<MembersData> crowdMembers = new ArrayList<MembersData>(); // Crowd에 소속되어있는 멤버들의 리스트

        // 신청을 넣은 멤버들 수락/거절 관리
        //
        ArrayList<MembersData> arMembers = new ArrayList<MembersData>(); // 신청을 넣은 멤버들의 리스트
        // TODO: response 구현 필요(테스트용 데이터 추가해둠)
        //
        MembersData member1 = new MembersData("Messi");
        MembersData member2 = new MembersData("Ronaldo");
        MembersData member3 = new MembersData("Holland");
        arMembers.add(member1);
        arMembers.add(member2);
        arMembers.add(member3);
        //
        //
        ListView arListView = arManageMembersDialog.findViewById(R.id.arListView);
        AcceptRefuseMemberAdapter arMembersAdapter = new AcceptRefuseMemberAdapter(getApplicationContext(), arMembers);
        arListView.setAdapter(arMembersAdapter);

        // 수락, 거절 버튼 클릭 시 해야 할 일은 AcceptRefuseMemberAdapter.java의 getView()에 구현되어 있음.
        // 여기서 구현하고 싶었지만 리스트뷰 내에 버튼이 있기 때문에 어댑터에서 구현해야 함.
    }


}
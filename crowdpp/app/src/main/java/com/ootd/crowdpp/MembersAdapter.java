package com.ootd.crowdpp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ootd.crowdpp.Retrofits.Result;
import com.ootd.crowdpp.Retrofits.RetrofitClient;

import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MembersAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<MembersData> Members = new ArrayList<>();
    boolean isLeader;

    public MembersAdapter(Context context, ArrayList<MembersData> members) {
        mContext = context;
        Members = members;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return Members.size();
    }

    @Override
    public Object getItem(int position) {
        return Members.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.card_members, null);
        TextView memberName = view.findViewById(R.id.memberName);
        Button kickMemberButton = view.findViewById(R.id.kickMemberButton);

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("crowdInfo", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences1 = mContext.getSharedPreferences("loginUserInfo", Context.MODE_PRIVATE);
        int crowdId = sharedPreferences.getInt("crowdId", -1);
        String userId = sharedPreferences1.getString("id", ""); // 로그인된 유저 아이디
        String kickMemberId = Members.get(position).getMembers(); // 추방하는 멤버 아이디
        if (crowdId == -1){
            System.out.println("Error");
        }
        else{
            Call<Result> call;
            call = RetrofitClient.getApiService().isLeader(userId, crowdId);
            call.enqueue(new Callback<Result>(){
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    if (response.code() == 200) {
                        Result result = response.body();
                        String msg = result.getMsg();
                        System.out.println(msg);
                        System.out.println("//////////");
                        System.out.println(crowdId);
                        if (msg.equals("notLeader")) {
                            isLeader = false;
                        } else if (msg.equals("leader")) {
                            isLeader = true;
                            kickMemberButton.setVisibility(View.VISIBLE); // 리더라면 추방 버튼 활성화

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

        memberName.setText(Members.get(position).getMembers());




        kickMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());

                builder.setMessage("정말 추방하시겠습니까?");

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int whichButton){
                        Call<Result> call;
                        call = RetrofitClient.getApiService().kickmember(kickMemberId, crowdId);
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
                android.app.AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }
}
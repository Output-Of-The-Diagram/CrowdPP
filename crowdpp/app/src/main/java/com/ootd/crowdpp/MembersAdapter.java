package com.ootd.crowdpp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.util.ArrayList;

public class MembersAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<MembersData> Members = new ArrayList<>();

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


        memberName.setText(Members.get(position).getMembers());

        Button kickMemberButton = view.findViewById(R.id.kickMemberButton);
        // 이 사람이 리더인지 확인 필요
        boolean isLeader = true;
        if (isLeader){
            kickMemberButton.setVisibility(View.VISIBLE); // 리더라면 추방 버튼 활성화
        }
        kickMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("정말 추방하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int whichButton){
//
//                        추방 구현해주세요
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
        });

        return view;
    }
}
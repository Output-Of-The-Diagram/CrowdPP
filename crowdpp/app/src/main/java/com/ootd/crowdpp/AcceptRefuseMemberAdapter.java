package com.ootd.crowdpp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AcceptRefuseMemberAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<MembersData> Members = new ArrayList<>();

    public AcceptRefuseMemberAdapter(Context context, ArrayList<MembersData> members) {
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
        View view = mLayoutInflater.inflate(R.layout.card_acceptrefuse, null);
        TextView memberName = view.findViewById(R.id.arMemberName);

        memberName.setText(Members.get(position).getMembers());

//        수락, 거절 버튼 클릭 시 각각 해야 할 일 수행
        TextView acceptButton = view.findViewById(R.id.arAcceptButton);
        TextView refuseButton = view.findViewById(R.id.arRefuseButton);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 수락 완료, Crowd에 해당 멤버 추가, 요청 제거
                Toast.makeText(v.getContext(), "수락 완료", Toast.LENGTH_SHORT).show();
            }
        });

        refuseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // 거절 완료, 요청 제거
                Toast.makeText(v.getContext(), "거절 완료", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
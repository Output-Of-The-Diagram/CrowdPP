package com.ootd.crowdpp;

import android.content.Context;
import android.content.DialogInterface;
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

public class ApplyRefuseAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<MembersData> Members = new ArrayList<>();

    public ApplyRefuseAdapter(Context context, ArrayList<MembersData> members) {
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

        return view;
    }
}

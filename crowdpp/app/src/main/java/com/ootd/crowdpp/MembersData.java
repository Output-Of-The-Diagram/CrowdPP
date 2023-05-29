package com.ootd.crowdpp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Member;

public class MembersData {
    @SerializedName("members")
    @Expose
    private String members;

    public MembersData(String members){
        this.members = members;
    }


    public String getMembers(){
        return members;
    }

    public void setMembers(String members){
        this.members = members;
    }
}

package com.ootd.crowdpp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CrowdData {
    @SerializedName("image")
    @Expose
    private int image;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("leaderID")
    @Expose
    private String leaderID;

    @SerializedName("introduction")
    @Expose
    private String introduction;

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getLeaderID() {
        return leaderID;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setImage(int image){
        this.image = image;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setLeaderID(String leaderID){
        this.leaderID = leaderID;
    }

    public void setIntroduction(String introduction){
        this.introduction = introduction;
    }
}

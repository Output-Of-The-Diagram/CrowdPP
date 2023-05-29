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

    @SerializedName("crowdID")
    @Expose
    private int crowdID;

    @SerializedName("introduction")
    @Expose
    private String introduction;

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getCrowdID() {
        return crowdID;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setImage(int image){
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCrowdID(int crowdID){
        this.crowdID = crowdID;
    }

    public void setIntroduction(String introduction){
        this.introduction = introduction;
    }
}

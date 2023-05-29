package com.ootd.crowdpp.Retrofits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("userID")
    @Expose
    private String userID;

    public String getName() {
        return userID;
    }

    public void setName(String name) {
        this.userID = name;
    }
}

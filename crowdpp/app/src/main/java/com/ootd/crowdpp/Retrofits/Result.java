package com.ootd.crowdpp.Retrofits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("msg")
    @Expose
    private String msg;


    public String getMsg() {
        return msg;
    }
}

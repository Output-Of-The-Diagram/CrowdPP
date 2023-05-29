package com.ootd.crowdpp.Retrofits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel {
    @SerializedName("name")
    @Expose
    private String name;


    public String getName() {
        return name;
    }
}
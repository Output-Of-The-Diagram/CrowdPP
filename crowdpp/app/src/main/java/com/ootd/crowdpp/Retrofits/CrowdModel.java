package com.ootd.crowdpp.Retrofits;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CrowdModel {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("getDate")
    @Expose
    private String getDate;

    @SerializedName("explain")
    @Expose
    private String explain;

    @SerializedName("representImage")
    @Expose
    private String representImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGetDate() {
        return getDate;
    }

    public void setGetDate(String getDate) {
        this.getDate = getDate;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getRepresentImage() {
        return representImage;
    }

    public void setRepresentImage(String representImage) {
        this.representImage = representImage;
    }
}

package com.example.covid19_navigation;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class news {
    @SerializedName("status")
    private String status;
    @SerializedName("totalResults")
    private String totalResults;
    @SerializedName("articles")
    private ArrayList<articles>  articlesArrayList;

    public news() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<articles> getArticlesArrayList() {
        return articlesArrayList;
    }

    public void setArticlesArrayList(ArrayList<articles> articlesArrayList) {
        this.articlesArrayList = articlesArrayList;
    }
}

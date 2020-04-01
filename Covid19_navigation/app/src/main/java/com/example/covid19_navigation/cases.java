package com.example.covid19_navigation;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class cases {
    @SerializedName("cases_time_series")
    private ArrayList<case_time_series> timeline;
    @SerializedName("statewise")
    private ArrayList<statewise>  statewiseArrayList;

    public cases() {

    }

    public ArrayList<case_time_series> getTimeline() {
        return timeline;
    }

    public ArrayList<statewise> getStatewiseArrayList() {
        return statewiseArrayList;
    }
}

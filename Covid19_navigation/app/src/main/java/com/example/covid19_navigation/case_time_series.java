package com.example.covid19_navigation;

import com.google.gson.annotations.SerializedName;

public class  case_time_series{

    @SerializedName("dailyconfirmed")
    private String daily_confirmed;
    @SerializedName("dailydeceased")
    private String daily_deceased;
    @SerializedName("dailyrecovered")
    private  String daily_recovered;
    @SerializedName("date")
    private  String date;
    @SerializedName("totalconfirmed")
    private String total_confirmed;
    @SerializedName("totaldeceased")
    private  String total_deceased;
    @SerializedName("totalrecovered")
    private  String total_recovered;

    public case_time_series() {
    }

    public String getDaily_confirmed() {
        return daily_confirmed;
    }

    public void setDaily_confirmed(String daily_confirmed) {
        this.daily_confirmed = daily_confirmed;
    }

    public String getDaily_deceased() {
        return daily_deceased;
    }

    public void setDaily_deceased(String daily_deceased) {
        this.daily_deceased = daily_deceased;
    }

    public String getDaily_recovered() {
        return daily_recovered;
    }

    public void setDaily_recovered(String daily_recovered) {
        this.daily_recovered = daily_recovered;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal_confirmed() {
        return total_confirmed;
    }

    public void setTotal_confirmed(String total_confirmed) {
        this.total_confirmed = total_confirmed;
    }

    public String getTotal_deceased() {
        return total_deceased;
    }

    public void setTotal_deceased(String total_deceased) {
        this.total_deceased = total_deceased;
    }

    public String getTotal_recovered() {
        return total_recovered;
    }

    public void setTotal_recovered(String total_recovered) {
        this.total_recovered = total_recovered;
    }
}

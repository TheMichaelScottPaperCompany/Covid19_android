package com.example.covid19_navigation;

import retrofit2.Call;
import retrofit2.http.GET;

public interface api2 {

    @GET("v2/everything?q=COVID19&sources=the-times-of-india&from=2020-03-31&to=2020-03-31&sortBy=published&apiKey=1180c78f6b1d4d968304547aa6d881ad")//endpoint

    Call<String> news( //response we shall get



    );


}

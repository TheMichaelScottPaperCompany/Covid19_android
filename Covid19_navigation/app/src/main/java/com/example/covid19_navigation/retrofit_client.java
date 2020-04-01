package com.example.covid19_navigation;

;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class retrofit_client {

    //private  static  final String base_url = "https://still-lake-87096.herokuapp.com/" ;//base url
    private  static  final String base_url = "https://api.covid19india.org/" ;//base url
    private static  retrofit_client instance;
    public Retrofit retrofit, retrofitApiV2; //retrofit object

    public retrofit_client(){
        retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();

    }
    public static synchronized retrofit_client getInstance(){
        if (instance == null){
            instance = new retrofit_client();
        }
        return instance;

    }
    /*public void retrofit2() {
         retrofitApiV2 = new Retrofit.Builder()
                .baseUrl("https://guarded-stream-17525.herokuapp.com/")

                .addConverterFactory(GsonConverterFactory.create())
                .build();*/

    /*public api2 getapi2(){
        return retrofitApiV2.create(api2.class);

    }
*/
    public api getapi(){
        return retrofit.create(api.class);
    }
}


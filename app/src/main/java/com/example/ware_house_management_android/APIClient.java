package com.example.ware_house_management_android;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static String baseUrl = "http://10.0.2.2:8386/api/v1/";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(
                            GsonConverterFactory
                                    .create())
                    .build();
        }
        return retrofit;
    }
}

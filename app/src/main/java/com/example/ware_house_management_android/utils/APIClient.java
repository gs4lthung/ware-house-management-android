package com.example.ware_house_management_android.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static String baseUrl = "https://mwbe-latest-w8vt.onrender.com/api/v1/";
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

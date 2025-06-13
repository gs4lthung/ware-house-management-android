package com.example.ware_house_management_android.services;

import android.content.Context;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.GetUsersResponseDto;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface UserService {
    @GET("users")
    Call<APIResponseDto<GetUsersResponseDto>> getUsers(@Header("Authorization") String token, @Query("filter") JSONObject filter);
}

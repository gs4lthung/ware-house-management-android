package com.example.ware_house_management_android.services;

import android.content.Context;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.GetUsersResponseDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UserService {
    @GET("users")
    Call<APIResponseDto<GetUsersResponseDto>> getUsers(@Header("Authorization") String token);
}

package com.example.ware_house_management_android.services;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.LoginRequestDto;
import com.example.ware_house_management_android.dtos.LoginResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/login")
    Call<APIResponseDto<LoginResponseDto>> login(@Body LoginRequestDto loginRequestDto);
}

package com.example.ware_house_management_android.services;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.auth.LoginRequestDto;
import com.example.ware_house_management_android.dtos.auth.LoginResponseDto;
import com.example.ware_house_management_android.dtos.auth.RegisterRequestDto;
import com.example.ware_house_management_android.dtos.auth.RegisterResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/login")
    Call<APIResponseDto<LoginResponseDto>> login(@Body LoginRequestDto loginRequestDto);

    @POST("auth/signup")
    Call<APIResponseDto<RegisterResponseDto>> register(@Body RegisterRequestDto registerRequestDto); // Sửa tham số
}
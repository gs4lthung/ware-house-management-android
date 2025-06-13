package com.example.ware_house_management_android.services;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.CreateInputDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface InputService {
    @POST("inputs")
    Call<APIResponseDto<CreateInputDto>> createInput(@Header("Authorization") String token, @Body CreateInputDto createInputDto);
}

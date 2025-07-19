package com.example.ware_house_management_android.services;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.output.CreateOutputDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OutputService {
    @POST("outputs")
    Call<APIResponseDto<CreateOutputDto>> createOutput(@Header("Authorization") String token, @Body CreateOutputDto createOutputDto);
}

package com.example.ware_house_management_android.services;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.input_details.UpdateInputDetailDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InputDetailService {
    @PUT("inputs/details/{id}")
    Call<APIResponseDto<Void>> updateInputDetail(
            @Header("Authorization") String token,
            @Path("id") String id,
            @Body UpdateInputDetailDto updateInputDetailDto
    );
}

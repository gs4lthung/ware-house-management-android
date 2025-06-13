package com.example.ware_house_management_android.services;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.GetBaseItemsResponseDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface BaseItemService {
    @GET("baseitems")
    Call<APIResponseDto<GetBaseItemsResponseDto>> getBaseItems(@Header("Authorization") String token);
}

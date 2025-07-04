package com.example.ware_house_management_android.services;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.base_items.GetBaseItemsResponseDto;
import com.example.ware_house_management_android.dtos.items.GetItemsResponseDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ItemService {
    String SELECT_PATH = "code status manufactureDate expiredDate unit baseItemId";
    String EXPAND_PATH = "baseItem";

    @GET("items")
    Call<APIResponseDto<GetItemsResponseDto>> getItems(
            @Header("Authorization") String token,
            @Query("select") String select,
            @Query("expand") String expand
    );
}

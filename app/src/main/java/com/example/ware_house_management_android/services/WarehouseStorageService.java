package com.example.ware_house_management_android.services;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.warehouse_storages.GetWarehouseStoragesResponseDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface WarehouseStorageService {
    String SELECT_PATH = "warehouseId itemId batchNumber quantity";
    String EXPAND_PATH = "warehouse item";

    @GET("warehouses/storages")
    Call<APIResponseDto<GetWarehouseStoragesResponseDto>> getWarehouseStorages(@Header("Authorization") String token,
                                                                               @Query("select") String select,
                                                                               @Query("expand") String expand);
}

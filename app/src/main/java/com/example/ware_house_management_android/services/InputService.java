package com.example.ware_house_management_android.services;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.CreateInputDto;
import com.example.ware_house_management_android.dtos.GetInputByIdResponseDto;
import com.example.ware_house_management_android.dtos.GetInputsResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InputService {
    String SELECT_PATH = "description cancelReason batchNumber totalPrice fromDate toDate status supplierId reportStaffId managerId inventoryStaffIds";
    String EXPAND_PATH = "supplier inventoryStaffs reportStaff manager";

    @GET("inputs")
    Call<APIResponseDto<GetInputsResponseDto>> getInputs(@Header("Authorization") String token,
                                                         @Query("select") String select,
                                                         @Query("expand") String expand);

    @GET("inputs/{id}")
    Call<APIResponseDto<GetInputByIdResponseDto>> getInputById(@Header("Authorization") String token, @Path("id") String id);

    @POST("inputs")
    Call<APIResponseDto<CreateInputDto>> createInput(@Header("Authorization") String token, @Body CreateInputDto createInputDto);
}

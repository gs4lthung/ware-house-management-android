package com.example.ware_house_management_android.services;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.output.CreateOutputDto;
import com.example.ware_house_management_android.dtos.output.GetOutputByIdResponseDto;
import com.example.ware_house_management_android.dtos.output.GetOutputsResponseDto;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OutputService {
    String SELECT_PATH = "description cancelReason batchNumber status totalPrice fromDate toDate customerId reportStaffId managerId inventoryStaffIds createdAt updatedAt";
    String EXPAND_PATH = "customer reportStaff manager inventoryStaffs";

    @GET("outputs")
    Call<APIResponseDto<GetOutputsResponseDto>> getOutputs(
            @Header("Authorization") String token,
            @Query("filter") JSONObject filter,
            @Query("select") String select,
            @Query("expand") String expand
    );

    @GET("outputs/{id}")
    Call<APIResponseDto<GetOutputByIdResponseDto>> getOutputById(
            @Header("Authorization") String token,
            @Path("id") String id
    );


    @POST("outputs")
    Call<APIResponseDto<CreateOutputDto>> createOutput(@Header("Authorization") String token, @Body CreateOutputDto createOutputDto);
}

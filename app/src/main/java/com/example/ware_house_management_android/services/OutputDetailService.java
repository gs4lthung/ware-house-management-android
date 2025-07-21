package com.example.ware_house_management_android.services;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.output_details.UpdateOutputDetailDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OutputDetailService {
    @PUT("outputs/details/{id}")
    Call<APIResponseDto<Void>> updateOutputDetail(
            @Header("Authorization") String token,
            @Path("id") String id,
            @Body UpdateOutputDetailDto updateOutputDetailDto
    );
}

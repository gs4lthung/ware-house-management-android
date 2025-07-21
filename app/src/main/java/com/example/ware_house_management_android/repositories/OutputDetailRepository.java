package com.example.ware_house_management_android.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.output_details.UpdateOutputDetailDto;
import com.example.ware_house_management_android.services.OutputDetailService;
import com.example.ware_house_management_android.utils.APIClient;

import retrofit2.Call;

public class OutputDetailRepository {
    private static OutputDetailService outputDetailService;
    private final Context context;
    private SharedPreferences sharedPreferences;

    public OutputDetailRepository(Context context) {
        this.context = context.getApplicationContext();
        sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        outputDetailService = APIClient.getClient().create(OutputDetailService.class);
    }

    public Call<APIResponseDto<Void>> updateOutputDetail(String id, UpdateOutputDetailDto updateOutputDetailDto) {
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;

        return outputDetailService.updateOutputDetail(authHeader, id, updateOutputDetailDto);
    }

}

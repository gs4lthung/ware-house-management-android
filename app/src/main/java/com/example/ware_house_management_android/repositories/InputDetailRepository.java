package com.example.ware_house_management_android.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.input_details.UpdateInputDetailDto;
import com.example.ware_house_management_android.services.InputDetailService;
import com.example.ware_house_management_android.utils.APIClient;



import retrofit2.Call;

public class InputDetailRepository {
    private static InputDetailService inputDetailService;

    private final Context context;

    private SharedPreferences sharedPreferences;

    public InputDetailRepository(Context context) {
        this.context = context.getApplicationContext();
        sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        inputDetailService = APIClient.getClient().create(InputDetailService.class);
    }

    public Call<APIResponseDto<Void>> updateInputDetail(String id, UpdateInputDetailDto updateInputDetailDto) {
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;

        return inputDetailService.updateInputDetail(authHeader, id, updateInputDetailDto);
    }
}

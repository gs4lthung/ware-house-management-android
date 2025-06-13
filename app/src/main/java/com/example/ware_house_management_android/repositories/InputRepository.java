package com.example.ware_house_management_android.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.CreateInputDto;
import com.example.ware_house_management_android.services.InputService;
import com.example.ware_house_management_android.utils.APIClient;

import retrofit2.Call;

public class InputRepository {
    private static InputService inputService;
    private final Context context;

    public InputRepository(Context context) {
        this.context = context.getApplicationContext();
        inputService = APIClient.getClient().create(InputService.class);
    }

    public Call<APIResponseDto<CreateInputDto>> createInput(CreateInputDto createInputDto) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;
        return inputService.createInput(authHeader, createInputDto);
    }
}

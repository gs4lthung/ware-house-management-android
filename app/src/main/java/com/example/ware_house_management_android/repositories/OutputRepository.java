package com.example.ware_house_management_android.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.output.CreateOutputDto;
import com.example.ware_house_management_android.services.OutputService;
import com.example.ware_house_management_android.utils.APIClient;

import retrofit2.Call;

public class OutputRepository {
    private static OutputService outputService;
    private final Context context;
    private SharedPreferences sharedPreferences;

    public OutputRepository(Context context) {
        this.context = context.getApplicationContext();
        outputService = APIClient.getClient().create(OutputService.class);
        sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
    }

   public Call<APIResponseDto<CreateOutputDto>> createOutput(CreateOutputDto createOutputDto) {
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;
        return outputService.createOutput(authHeader, createOutputDto);
    }
}

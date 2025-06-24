package com.example.ware_house_management_android.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.base_items.GetBaseItemsResponseDto;
import com.example.ware_house_management_android.services.BaseItemService;
import com.example.ware_house_management_android.utils.APIClient;

import retrofit2.Call;

public class BaseItemRepository {
    private static BaseItemService baseItemService;

    private final Context context;

    public BaseItemRepository(Context context) {
        this.context = context.getApplicationContext();
        baseItemService = APIClient.getClient().create(BaseItemService.class);
    }

    public Call<APIResponseDto<GetBaseItemsResponseDto>> getBaseItems() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("access_token", "");

        String authHeader = "Bearer " + accessToken;
        return baseItemService.getBaseItems(authHeader);
    }

}

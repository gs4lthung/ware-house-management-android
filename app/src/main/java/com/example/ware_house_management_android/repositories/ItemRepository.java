package com.example.ware_house_management_android.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.items.GetItemsResponseDto;
import com.example.ware_house_management_android.services.ItemService;
import com.example.ware_house_management_android.utils.APIClient;

import java.util.ArrayList;

import retrofit2.Call;

public class ItemRepository {
    private static ItemService itemService;
    private final Context context;
    private SharedPreferences sharedPreferences;

    public ItemRepository(Context context) {
        this.context = context;
        itemService = APIClient.getClient().create(ItemService.class);
        sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
    }

    public Call<APIResponseDto<GetItemsResponseDto>> getItems() {
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;
        return itemService.getItems(authHeader, ItemService.SELECT_PATH, ItemService.EXPAND_PATH);
    }
}

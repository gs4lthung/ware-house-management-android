package com.example.ware_house_management_android.repositories;

import android.content.Context;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.warehouse_storages.GetWarehouseStoragesResponseDto;
import com.example.ware_house_management_android.services.WarehouseStorageService;
import com.example.ware_house_management_android.utils.APIClient;

import retrofit2.Call;

public class WarehouseStorageRepository {
    private static WarehouseStorageService warehouseStorageService;
    private final Context context;

    public WarehouseStorageRepository(Context context) {
        this.context = context.getApplicationContext();
        warehouseStorageService = APIClient.getClient().create(WarehouseStorageService.class);
    }

    public Call<APIResponseDto<GetWarehouseStoragesResponseDto>> getWarehouseStorages() {
        String accessToken = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                .getString("access_token", "");
        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;
        return warehouseStorageService.getWarehouseStorages(authHeader,
                WarehouseStorageService.SELECT_PATH,
                WarehouseStorageService.EXPAND_PATH);
    }

}

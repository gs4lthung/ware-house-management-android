package com.example.ware_house_management_android.repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.ware_house_management_android.APIClient;
import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.GetUsersResponseDto;
import com.example.ware_house_management_android.enums.SharedPreferenceEnum;
import com.example.ware_house_management_android.services.UserService;

import retrofit2.Call;

public class UserRepository {
    private static UserService userService;
    private final Context context;

    public UserRepository(Context context) {
        this.context = context.getApplicationContext();
        userService = APIClient.getClient().create(UserService.class);
    }

    public Call<APIResponseDto<GetUsersResponseDto>> getUsers() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;
        return userService.getUsers(authHeader);
    }
}
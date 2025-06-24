package com.example.ware_house_management_android.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ware_house_management_android.utils.APIClient;
import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.users.GetUsersResponseDto;
import com.example.ware_house_management_android.services.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;

public class UserRepository {
    private static UserService userService;
    private final Context context;

    public UserRepository(Context context) {
        this.context = context.getApplicationContext();
        userService = APIClient.getClient().create(UserService.class);
    }

    public Call<APIResponseDto<GetUsersResponseDto>> getUsers(String role) throws JSONException {
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;
        return userService.getUsers(authHeader, role != "" ? new JSONObject().put("role", role) : null);
    }
}
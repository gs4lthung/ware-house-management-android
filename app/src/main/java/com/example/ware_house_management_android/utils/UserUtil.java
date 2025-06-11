package com.example.ware_house_management_android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.example.ware_house_management_android.dtos.JWTPayloadDecoded;
import com.example.ware_house_management_android.enums.UserRoleEnum;
import com.example.ware_house_management_android.models.UserModel;
import com.google.gson.Gson;

public class UserUtil {
    private UserUtil() {
    }

    public static UserModel currentUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("user", "");
        if (TextUtils.isEmpty(userJson)) {
            return null;
        }

        Gson gson = new Gson();
        JWTPayloadDecoded jwtPayloadDecoded = gson.fromJson(userJson, JWTPayloadDecoded.class);

        return new UserModel(
                jwtPayloadDecoded.getId(),
                jwtPayloadDecoded.getFullName(),
                jwtPayloadDecoded.getEmail(),
                jwtPayloadDecoded.getRole()
        );
    }
}

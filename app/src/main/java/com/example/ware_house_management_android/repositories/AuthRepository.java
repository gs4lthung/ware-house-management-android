package com.example.ware_house_management_android.repositories;

import com.example.ware_house_management_android.utils.APIClient;
import com.example.ware_house_management_android.services.AuthService;

public class AuthRepository {
    public static AuthService getAuthService() {
        return APIClient.getClient().create(AuthService.class);
    }
}

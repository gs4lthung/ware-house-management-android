package com.example.ware_house_management_android.presenters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.auth0.android.jwt.JWT;
import com.example.ware_house_management_android.utils.BaseCallback;
import com.example.ware_house_management_android.LoginActivity;
import com.example.ware_house_management_android.MainActivity;
import com.example.ware_house_management_android.contracts.auth.LoginContract;
import com.example.ware_house_management_android.dtos.auth.JWTPayloadDecoded;
import com.example.ware_house_management_android.dtos.auth.LoginRequestDto;
import com.example.ware_house_management_android.dtos.auth.LoginResponseDto;
import com.example.ware_house_management_android.repositories.AuthRepository;
import com.example.ware_house_management_android.services.AuthService;
import com.google.gson.Gson;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private final Context context;

    public LoginPresenter(LoginContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    AuthService authService;

    private JWTPayloadDecoded decodeJWT(String token) {
        if (TextUtils.isEmpty(token)) {
            return null;
        }

        try {
            JWT jwt = new JWT(token);
            String id = jwt.getClaim("id").asString();
            String role = jwt.getClaim("role").asString();
            String email = jwt.getClaim("email").asString();
            String fullName = jwt.getClaim("fullName").asString();

            JWTPayloadDecoded payload = new JWTPayloadDecoded(id, role, email, fullName);

            return payload;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("LoginPresenter", "Error decoding JWT: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void checkAlreadyLogin() {
        if (view != null) {
            view.showLoading();
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("user", "");
        if (TextUtils.isEmpty(userJson)) {

            if (view != null) {
                view.hideLoading();
            }
            return;
        }

        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        if (context instanceof LoginActivity) {
            ((LoginActivity) context).finish();
        }
        if (view != null) {
            view.hideLoading();
        }
    }

    @Override
    public void login(LoginRequestDto loginRequestDto) {
        if (view != null) {
            view.showLoading();
        }
        if (loginRequestDto == null || !loginRequestDto.isValid()) {
            if (view != null) {
                view.hideLoading();
                view.showLoginError(loginRequestDto.getErrorMessage());
            }
            return;
        }

        authService = AuthRepository.getAuthService();
        authService.login(loginRequestDto)
                .enqueue(new BaseCallback<>(context) {
                    @Override
                    public void onSuccess(LoginResponseDto data) {
                        if (view != null) {
                            view.hideLoading();
                            view.showLoginSuccess("Login successful!");
                        }
                        String accessToken = data.getAccessToken();

                        JWTPayloadDecoded jwtPayload = decodeJWT(accessToken);
                        Gson gson = new Gson();
                        String userJson = gson.toJson(jwtPayload);

                        SharedPreferences sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user", userJson);
                        editor.putString("access_token", accessToken);
                        editor.apply();

                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                        if (context instanceof LoginActivity) {
                            ((LoginActivity) context).finish();
                        }
                    }

                    @Override
                    public void onError(int code, String message) {
                        if (view != null) {
                            view.hideLoading();
                            String errorMessage = "Login failed. Please try again.";
                            if (!TextUtils.isEmpty(message)) {
                                errorMessage = message;
                            }
                            view.showLoginError(errorMessage);
                        }
                        Log.e("LoginPresenter", "Error code: " + code + ", message: " + message);
                    }
                });
    }

    @Override
    public void onDestroy() {
        if (view != null) {
            view.hideLoading();
            view = null;
        }
    }
}

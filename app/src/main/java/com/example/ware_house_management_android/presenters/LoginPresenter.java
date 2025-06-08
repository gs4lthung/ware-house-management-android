package com.example.ware_house_management_android.presenters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.example.ware_house_management_android.BaseCallback;
import com.example.ware_house_management_android.LoginActivity;
import com.example.ware_house_management_android.MainActivity;
import com.example.ware_house_management_android.contracts.LoginContract;
import com.example.ware_house_management_android.dtos.APIErrorResponseDto;
import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.LoginRequestDto;
import com.example.ware_house_management_android.dtos.LoginResponseDto;
import com.example.ware_house_management_android.repositories.AuthRepository;
import com.example.ware_house_management_android.services.AuthService;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private Context context;

    public LoginPresenter(LoginContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    AuthService authService;

    @Override
    public void login(LoginRequestDto loginRequestDto) {
        if (view != null) {
            view.showLoading();
        }
        if (loginRequestDto == null ||
                TextUtils.isEmpty(loginRequestDto.getEmail()) ||
                TextUtils.isEmpty(loginRequestDto.getPassword())) {
            if (view != null) {
                view.hideLoading();
                view.showLoginError("Email and password cannot be empty.");
            }
            return;
        }

        authService = AuthRepository.getAuthService();
        authService.login(loginRequestDto)
                .enqueue(new BaseCallback<>() {
                    @Override
                    public void onSuccess(LoginResponseDto data) {
                        if (view != null) {
                            view.hideLoading();
                            view.showLoginSuccess("Login successful!");
                        }
                        String accessToken = data.getAccessToken();

                        SharedPreferences sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
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

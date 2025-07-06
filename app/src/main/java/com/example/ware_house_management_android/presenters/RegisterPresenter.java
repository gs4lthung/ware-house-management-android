package com.example.ware_house_management_android.presenters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.auth0.android.jwt.JWT;
import com.example.ware_house_management_android.LoginActivity;
import com.example.ware_house_management_android.MainActivity;
import com.example.ware_house_management_android.RegisterActivity;
import com.example.ware_house_management_android.contracts.auth.RegisterContract;
import com.example.ware_house_management_android.dtos.auth.JWTPayloadDecoded;
import com.example.ware_house_management_android.dtos.auth.RegisterRequestDto;
import com.example.ware_house_management_android.dtos.auth.RegisterResponseDto;
import com.example.ware_house_management_android.repositories.AuthRepository;
import com.example.ware_house_management_android.services.AuthService;
import com.example.ware_house_management_android.utils.BaseCallback;

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View view;
    private final Context context;
    private AuthService authService;

    public RegisterPresenter(RegisterContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.authService = AuthRepository.getAuthService(); // Khởi tạo một lần
    }

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

            return new JWTPayloadDecoded(id, role, email, fullName);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("RegisterPresenter", "Error decoding JWT: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void checkAlreadyRegister() { // Đổi tên từ checkAlreadyRegister thành checkAlreadyLogin cho rõ nghĩa
        if (view != null) {
            view.showLoading();
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("user", "");
        if (!TextUtils.isEmpty(userJson)) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            // Kiểm tra và cast context an toàn hơn
            if (context instanceof RegisterActivity) {
                ((RegisterActivity) context).finish();
            } else if (context instanceof android.app.Activity) {
                ((android.app.Activity) context).finish();
            }
        }
        if (view != null) {
            view.hideLoading();
        }
    }

    @Override
    public void register(RegisterRequestDto registerRequestDto) {
        if (view == null) return; // Kiểm tra null trước
        view.showLoading();

        if (registerRequestDto == null || !registerRequestDto.isValid()) {
            view.hideLoading();
            view.showRegisterError(registerRequestDto != null ? registerRequestDto.getErrorMessage() : "Invalid input data");
            return;
        }

        authService.register(registerRequestDto)
                .enqueue(new BaseCallback<RegisterResponseDto>(context) {
                    @Override
                    public void onSuccess(RegisterResponseDto data) {
                        if (view != null) {
                            view.hideLoading();
                            view.showRegisterSuccess("Register successful!");
                        }
                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(intent);
                            if (context instanceof RegisterActivity) {
                                ((RegisterActivity) context).finish();
                            } else if (context instanceof android.app.Activity) {
                                ((android.app.Activity) context).finish();

                        }
                    }

                    @Override
                    public void onError(int code, String message) {
                        if (view != null) {
                            view.hideLoading();
                            String errorMessage = "Registration failed. Please try again.";
                            if (!TextUtils.isEmpty(message)) {
                                errorMessage = message;
                            }
                            view.showRegisterError(errorMessage);
                        }
                        Log.e("RegisterPresenter", "Error code: " + code + ", message: " + message);
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
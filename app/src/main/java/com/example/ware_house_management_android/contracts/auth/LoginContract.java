package com.example.ware_house_management_android.contracts.auth;

import com.example.ware_house_management_android.dtos.auth.LoginRequestDto;

public interface LoginContract {
    interface View {

        void showLoginSuccess(String message);

        void showLoginError(String error);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void checkAlreadyLogin();

        void login(LoginRequestDto loginRequestDto);

        void onDestroy();
    }
}

package com.example.ware_house_management_android.contracts;

import com.example.ware_house_management_android.dtos.LoginRequestDto;

public interface LoginContract {
    interface View {
        void showLoginSuccess(String message);

        void showLoginError(String error);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void login(LoginRequestDto loginRequestDto);

        void onDestroy();
    }
}

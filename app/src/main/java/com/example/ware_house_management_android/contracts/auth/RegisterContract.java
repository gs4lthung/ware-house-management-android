package com.example.ware_house_management_android.contracts.auth;

import com.example.ware_house_management_android.dtos.LoginRequestDto;
import com.example.ware_house_management_android.dtos.RegisterRequestDto;

public interface RegisterContract {
    interface View {

        void showRegisterSuccess(String message);

        void showRegisterError(String error);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void checkAlreadyRegister();

        void register(RegisterRequestDto registerRequestDto);

        void onDestroy();
    }
}

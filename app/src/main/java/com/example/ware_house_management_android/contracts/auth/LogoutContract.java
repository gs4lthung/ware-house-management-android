package com.example.ware_house_management_android.contracts.auth;

public interface LogoutContract {
    interface View {
        void showLogoutSuccess(String message);

        void showLogoutError(String error);
    }

    interface Presenter {
        void logout();

        void onDestroy();
    }
}

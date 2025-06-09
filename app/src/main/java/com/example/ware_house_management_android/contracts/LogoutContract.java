package com.example.ware_house_management_android.contracts;

public interface LogoutContract {
    interface View {
        void showLogoutSuccess(String message);

        void showLogoutError(String error);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void logout();

        void onDestroy();
    }
}

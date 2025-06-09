package com.example.ware_house_management_android.contracts;

public interface SignupContract {
    interface View {
        void showSignupSuccess(String message);

        void showSignupError(String error);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void signup(String fullName, String email, String password, String confirmPassword);

        void onDestroy();
    }

}

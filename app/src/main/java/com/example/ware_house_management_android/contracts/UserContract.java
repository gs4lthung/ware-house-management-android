package com.example.ware_house_management_android.contracts;

import com.example.ware_house_management_android.models.UserModel;

import org.json.JSONException;

import java.util.ArrayList;

public interface UserContract {
    interface View {
        void showUsersList(ArrayList<UserModel> users);

        void showSuccess(String message);

        void showError(String error);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void getUsersList() throws JSONException;
    }
}

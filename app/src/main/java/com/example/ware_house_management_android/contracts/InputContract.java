package com.example.ware_house_management_android.contracts;

import org.json.JSONException;

public interface InputContract {
    interface View {
        void showSuccess(String message);

        void showError(String error);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void getInputList() throws JSONException;

        void getInputById(String id) throws JSONException;

        void approveInput(String id);
    }
}

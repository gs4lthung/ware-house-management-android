package com.example.ware_house_management_android.contracts;

public interface WarehouseStorageContract {
    interface View {
        void showSuccess(String message);

        void showError(String error);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void getWarehouseStorageList();
    }
}

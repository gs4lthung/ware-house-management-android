package com.example.ware_house_management_android.contracts;

import com.example.ware_house_management_android.models.WarehouseStorageModel;

import java.util.ArrayList;

public interface WarehouseStorageContract {
    interface View {
        void showSuccess(String message);

        void showError(String error);

        void showLoading();

        void hideLoading();

        void showWarehouseStorageList(ArrayList<WarehouseStorageModel> warehouseStorageList);
    }

    interface Presenter {
        void getWarehouseStorageList();
    }
}

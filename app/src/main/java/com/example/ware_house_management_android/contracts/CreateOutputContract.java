package com.example.ware_house_management_android.contracts;

import com.example.ware_house_management_android.dtos.output.CreateOutputDto;

public interface CreateOutputContract {
    interface View {
        void showSuccess(String message);

        void showError(String error);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void getItemList() throws Exception;

        void getCustomers() throws Exception;

        void createOutput(CreateOutputDto createOutputDto) throws Exception;
    }
}

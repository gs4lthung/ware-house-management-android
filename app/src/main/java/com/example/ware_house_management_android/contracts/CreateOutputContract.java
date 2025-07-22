package com.example.ware_house_management_android.contracts;

import com.example.ware_house_management_android.dtos.output.CreateOutputDto;
import com.example.ware_house_management_android.models.ItemModel;
import com.example.ware_house_management_android.models.UserModel;

import java.util.ArrayList;

public interface CreateOutputContract {
    interface View {
        void showItemList(ArrayList<ItemModel> items);

        void showCustomers(ArrayList<UserModel> customers);

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

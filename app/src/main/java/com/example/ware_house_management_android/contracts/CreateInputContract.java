package com.example.ware_house_management_android.contracts;

import com.example.ware_house_management_android.dtos.inputs.CreateInputDto;
import com.example.ware_house_management_android.models.BaseItemModel;
import com.example.ware_house_management_android.models.UserModel;

import java.util.ArrayList;

public interface CreateInputContract {

    interface View {
        void showBaseItemList(ArrayList<BaseItemModel> baseItems);

        void showSuppliers(ArrayList<UserModel> suppliers);

        void showSuccess(String message);

        void showError(String error);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void getBaseItemList() throws Exception;

        void getSuppliers() throws Exception;

        void createInput(CreateInputDto createInputDto) throws Exception;
    }
}

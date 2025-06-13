package com.example.ware_house_management_android.ui.input;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ware_house_management_android.models.BaseItemModel;
import com.example.ware_house_management_android.models.UserModel;

import java.util.ArrayList;

public class CreateInputViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<BaseItemModel>> mBaseItemList;
    private final MutableLiveData<ArrayList<UserModel>> mSuppliersList;

    public CreateInputViewModel() {
        mBaseItemList = new MutableLiveData<>(new ArrayList<>());
        mSuppliersList = new MutableLiveData<>(new ArrayList<>());
    }

    public MutableLiveData<ArrayList<BaseItemModel>> getBaseItemList() {
        return mBaseItemList;
    }

    public void setBaseItemList(ArrayList<BaseItemModel> baseItemList) {
        mBaseItemList.setValue(baseItemList);
    }

    public MutableLiveData<ArrayList<UserModel>> getSuppliersList() {
        return mSuppliersList;
    }

    public void setSuppliersList(ArrayList<UserModel> reportStaffsList) {
        mSuppliersList.setValue(reportStaffsList);
    }
}

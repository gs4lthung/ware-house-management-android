package com.example.ware_house_management_android.view_models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ware_house_management_android.models.WarehouseStorageModel;

import java.util.ArrayList;

public class WarehouseViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<WarehouseStorageModel>> mWarehouseStorageList;

    public WarehouseViewModel() {
        mWarehouseStorageList = new MutableLiveData<>(new ArrayList<>());
    }

    public MutableLiveData<ArrayList<WarehouseStorageModel>> getWarehouseStorageList() {
        return mWarehouseStorageList;
    }

    public void setWarehouseStorageList(ArrayList<WarehouseStorageModel> warehouseStorageList) {
        mWarehouseStorageList.setValue(warehouseStorageList);
    }
}

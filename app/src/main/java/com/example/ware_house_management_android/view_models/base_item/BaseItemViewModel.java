package com.example.ware_house_management_android.view_models.base_item;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ware_house_management_android.models.BaseItemModel;

import java.util.ArrayList;

public class BaseItemViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<BaseItemModel>> mBaseItemList;
    private boolean hasLoadedBaseItems = false;

    public BaseItemViewModel() {
        this.mBaseItemList = new MutableLiveData<>(new ArrayList<>());
    }

    public MutableLiveData<ArrayList<BaseItemModel>> getBaseItemList() {
        return mBaseItemList;
    }

    public void setBaseItemList(ArrayList<BaseItemModel> baseItems) {
        mBaseItemList.setValue(baseItems);
        hasLoadedBaseItems = true;
    }

    public boolean hasLoadedBaseItems() {
        return hasLoadedBaseItems;
    }
}

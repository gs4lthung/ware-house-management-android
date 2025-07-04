package com.example.ware_house_management_android.view_models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ware_house_management_android.dtos.items.GetItemsResponseDto;
import com.example.ware_house_management_android.models.ItemModel;

import java.util.ArrayList;

public class ItemViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<ItemModel>> mCreateOutputItems;

    public ItemViewModel() {
        mCreateOutputItems = new MutableLiveData<>(new ArrayList<>());
    }

    public MutableLiveData<ArrayList<ItemModel>> getCreateOutputItems() {
        return mCreateOutputItems;
    }

    public void setCreateOutputItems(ArrayList<ItemModel> items) {
        mCreateOutputItems.setValue(items);
    }
}

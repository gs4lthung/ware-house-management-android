package com.example.ware_house_management_android.dtos.items;

import com.example.ware_house_management_android.models.BaseItemModel;
import com.example.ware_house_management_android.models.ItemModel;

import java.util.ArrayList;

public class GetItemsResponseDto {
    private ArrayList<ItemModel> items;

    public GetItemsResponseDto(ArrayList<ItemModel> items) {
        this.items = items;
    }

    public ArrayList<ItemModel> getItems() {
        return items;
    }


    public void setItems(ArrayList<ItemModel> items) {
        this.items = items;
    }

}

package com.example.ware_house_management_android.dtos.base_items;

import com.example.ware_house_management_android.models.BaseItemModel;

import java.util.ArrayList;

public class GetBaseItemsResponseDto {
    private ArrayList<BaseItemModel> baseItems;
    private int page;
    private int totalPages;

    public ArrayList<BaseItemModel> getBaseItems() {
        return baseItems;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }
}

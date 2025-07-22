package com.example.ware_house_management_android.dtos.warehouse_storages;

import com.example.ware_house_management_android.models.WarehouseStorageModel;

import java.util.ArrayList;

public class GetWarehouseStoragesResponseDto {
    private ArrayList<WarehouseStorageModel> warehouseStorages;
    private int page;
    private int totalPages;

    public ArrayList<WarehouseStorageModel> getWarehouseStorages() {
        return warehouseStorages;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }
}

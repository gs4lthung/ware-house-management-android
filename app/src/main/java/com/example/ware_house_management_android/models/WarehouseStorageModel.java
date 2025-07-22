package com.example.ware_house_management_android.models;

public class WarehouseStorageModel {
    private String _id;
    private WarehouseModel warehouseId;
    private ItemModel itemId;
    private String batchNumber;
    private int quantity;

    public WarehouseStorageModel(String _id, WarehouseModel warehouseId, ItemModel itemId, String batchNumber, int quantity) {
        this._id = _id;
        this.warehouseId = warehouseId;
        this.itemId = itemId;
        this.batchNumber = batchNumber;
        this.quantity = quantity;
    }

    public String get_id() {
        return _id;
    }

    public WarehouseModel getWarehouseId() {
        return warehouseId;
    }

    public ItemModel getItemId() {
        return itemId;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public int getQuantity() {
        return quantity;
    }
}



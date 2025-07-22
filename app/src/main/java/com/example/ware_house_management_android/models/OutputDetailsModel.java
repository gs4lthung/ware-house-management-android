package com.example.ware_house_management_android.models;

import java.util.Date;

public class OutputDetailsModel {
    private String _id;
    private String outputId;
    private ItemModel itemId;
    private WarehouseModel warehouseId;
    private int quantity;
    private Double outputPrice;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public OutputDetailsModel(String _id, String outputId, ItemModel itemId, WarehouseModel warehouseId, int quantity, Double outputPrice, String status, Date createdAt, Date updatedAt) {
        this._id = _id;
        this.outputId = outputId;
        this.itemId = itemId;
        this.warehouseId = warehouseId;
        this.quantity = quantity;
        this.outputPrice = outputPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getOutputId() {
        return outputId;
    }

    public void setOutputId(String outputId) {
        this.outputId = outputId;
    }

    public ItemModel getItemId() {
        return itemId;
    }

    public void setItemId(ItemModel itemId) {
        this.itemId = itemId;
    }

    public WarehouseModel getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(WarehouseModel warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getOutputPrice() {
        return outputPrice;
    }

    public void setOutputPrice(Double outputPrice) {
        this.outputPrice = outputPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

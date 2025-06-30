package com.example.ware_house_management_android.models;

import java.util.Date;

public class InputDetailsModel {
    private String _id;
    private String inputId;
    private WarehouseModel warehouseId;
    private ItemModel itemId;
    private int requestQuantity;
    private int actualQuantity;
    private Double inputPrice;
    private Double suggestedOutputPrice;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public InputDetailsModel(String _id, String inputId, WarehouseModel warehouseId, ItemModel itemId, int requestQuantity, int actualQuantity, Double inputPrice, Double suggestedOutputPrice, String status, Date createdAt, Date updatedAt) {
        this._id = _id;
        this.inputId = inputId;
        this.warehouseId = warehouseId;
        this.itemId = itemId;
        this.requestQuantity = requestQuantity;
        this.actualQuantity = actualQuantity;
        this.inputPrice = inputPrice;
        this.suggestedOutputPrice = suggestedOutputPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getInputId() {
        return inputId;
    }

    public void setInputId(String inputId) {
        this.inputId = inputId;
    }

    public WarehouseModel getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(WarehouseModel warehouseId) {
        this.warehouseId = warehouseId;
    }

    public ItemModel getItemId() {
        return itemId;
    }

    public void setItemId(ItemModel itemId) {
        this.itemId = itemId;
    }

    public int getRequestQuantity() {
        return requestQuantity;
    }

    public void setRequestQuantity(int requestQuantity) {
        this.requestQuantity = requestQuantity;
    }

    public int getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public Double getInputPrice() {
        return inputPrice;
    }

    public void setInputPrice(Double inputPrice) {
        this.inputPrice = inputPrice;
    }

    public Double getSuggestedOutputPrice() {
        return suggestedOutputPrice;
    }

    public void setSuggestedOutputPrice(Double suggestedOutputPrice) {
        this.suggestedOutputPrice = suggestedOutputPrice;
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

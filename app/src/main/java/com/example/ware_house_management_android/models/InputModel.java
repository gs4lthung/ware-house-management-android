package com.example.ware_house_management_android.models;

import java.util.ArrayList;
import java.util.Date;

public class InputModel {
    private String _id;
    private String description;
    private String batchNumber;

    private Double totalPrice;
    private String status;
    private UserModel reportStaffId;
    private UserModel supplierId;
    private ArrayList<UserModel> inventoryStaffIds;
    private Date createdAt;
    private Date updatedAt;

    public InputModel(String _id, String description, String batchNumber, Double totalPrice, String status, UserModel reportStaffId, UserModel supplierId, ArrayList<UserModel> inventoryStaffIds, Date createdAt, Date updatedAt) {
        this._id = _id;
        this.description = description;
        this.batchNumber = batchNumber;
        this.totalPrice = totalPrice;
        this.status = status;
        this.reportStaffId = reportStaffId;
        this.supplierId = supplierId;
        this.inventoryStaffIds = inventoryStaffIds;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserModel getReportStaffId() {
        return reportStaffId;
    }

    public void setReportStaffId(UserModel reportStaffId) {
        this.reportStaffId = reportStaffId;
    }

    public UserModel getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(UserModel supplierId) {
        this.supplierId = supplierId;
    }

    public ArrayList<UserModel> getInventoryStaffIds() {
        return inventoryStaffIds;
    }

    public void setInventoryStaffIds(ArrayList<UserModel> inventoryStaffIds) {
        this.inventoryStaffIds = inventoryStaffIds;
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

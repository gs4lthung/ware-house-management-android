package com.example.ware_house_management_android.models;

import java.util.ArrayList;

public class InputModel {
    private String _id;
    private String description;
    private String batchNumber;

    private Double totalPrice;
    private String status;
    private String reportStaffId;
    private String supplierId;
    private ArrayList<String> inventoryStaffIds;

    private boolean isDeleted;

    public InputModel(String _id, String description, String batchNumber, Double totalPrice, String status, String reportStaffId, String supplierId, ArrayList<String> inventoryStaffIds, boolean isDeleted) {
        this._id = _id;
        this.description = description;
        this.batchNumber = batchNumber;
        this.totalPrice = totalPrice;
        this.status = status;
        this.reportStaffId = reportStaffId;
        this.supplierId = supplierId;
        this.inventoryStaffIds = inventoryStaffIds;
        this.isDeleted = isDeleted;
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

    public String getReportStaffId() {
        return reportStaffId;
    }

    public void setReportStaffId(String reportStaffId) {
        this.reportStaffId = reportStaffId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public ArrayList<String> getInventoryStaffIds() {
        return inventoryStaffIds;
    }

    public void setInventoryStaffIds(ArrayList<String> inventoryStaffIds) {
        this.inventoryStaffIds = inventoryStaffIds;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}

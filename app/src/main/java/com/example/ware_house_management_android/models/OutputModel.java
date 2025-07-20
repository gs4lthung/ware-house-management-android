package com.example.ware_house_management_android.models;

import java.util.ArrayList;
import java.util.Date;

public class OutputModel {
    private String _id;
    private String description;
    private String batchNumber;
    private String status;
    private Double totalPrice;

    private Date fromDate;
    private Date toDate;
    private Date createdAt;
    private Date updatedAt;
    private UserModel reportStaffId;
    private UserModel customerId;
    private UserModel managerId;
    private ArrayList<UserModel> inventoryStaffIds;

    public OutputModel(String _id, String description, String batchNumber, String status, Double totalPrice, Date fromDate, Date toDate, Date createdAt, Date updatedAt, UserModel reportStaffId, UserModel customerId, UserModel managerId, ArrayList<UserModel> inventoryStaffIds) {
        this._id = _id;
        this.description = description;
        this.batchNumber = batchNumber;
        this.status = status;
        this.totalPrice = totalPrice;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.reportStaffId = reportStaffId;
        this.customerId = customerId;
        this.managerId = managerId;
        this.inventoryStaffIds = inventoryStaffIds;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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

    public UserModel getReportStaffId() {
        return reportStaffId;
    }

    public void setReportStaffId(UserModel reportStaffId) {
        this.reportStaffId = reportStaffId;
    }

    public UserModel getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UserModel customerId) {
        this.customerId = customerId;
    }

    public UserModel getManagerId() {
        return managerId;
    }

    public void setManagerId(UserModel managerId) {
        this.managerId = managerId;
    }

    public ArrayList<UserModel> getInventoryStaffIds() {
        return inventoryStaffIds;
    }

    public void setInventoryStaffIds(ArrayList<UserModel> inventoryStaffIds) {
        this.inventoryStaffIds = inventoryStaffIds;
    }
}

package com.example.ware_house_management_android.dtos;

import com.example.ware_house_management_android.models.UserModel;

import java.util.ArrayList;
import java.util.Date;

public class GetInputsResponseDto {
    private String _id;
    private String description;

    private String cancelReason;

    private Date fromDate;
    private Date toDate;

    private String batchNumber;
    private Double totalPrice;
    private String status;
    private UserModel reportStaffId;
    private UserModel supplierId;
    private ArrayList<UserModel> inventoryStaffIds;

    public GetInputsResponseDto(String _id, String description, String cancelReason, String batchNumber, Date fromDate, Date toDate, Double totalPrice, String status, UserModel reportStaffId, UserModel supplierId, ArrayList<UserModel> inventoryStaffIds) {
        this._id = _id;
        this.description = description;
        this.cancelReason = cancelReason;
        this.batchNumber = batchNumber;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.reportStaffId = reportStaffId;
        this.supplierId = supplierId;
        this.inventoryStaffIds = inventoryStaffIds;
    }


    public String get_id() {
        return _id;
    }

    public String getDescription() {
        return description;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public UserModel getReportStaffId() {
        return reportStaffId;
    }

    public UserModel getSupplierId() {
        return supplierId;
    }

    public ArrayList<UserModel> getInventoryStaffIds() {
        return inventoryStaffIds;
    }
}

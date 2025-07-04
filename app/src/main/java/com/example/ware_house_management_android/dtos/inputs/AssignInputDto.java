package com.example.ware_house_management_android.dtos.inputs;

import android.text.TextUtils;

import com.example.ware_house_management_android.models.UserModel;

import java.util.ArrayList;
import java.util.Date;

public class AssignInputDto {
    private ArrayList<String> inventoryStaffIds;
    private String fromDate;
    private String toDate;

    public AssignInputDto(ArrayList<String> inventoryStaffIds, String fromDate, String toDate) {
        this.inventoryStaffIds = inventoryStaffIds;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public ArrayList<String> getInventoryStaffIds() {
        return inventoryStaffIds;
    }

    public void setInventoryStaffIds(ArrayList<String> inventoryStaffIds) {
        this.inventoryStaffIds = inventoryStaffIds;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public boolean isValid() {
        return
                inventoryStaffIds != null && !inventoryStaffIds.isEmpty() &&
                        !TextUtils.isEmpty(fromDate) && !TextUtils.isEmpty(toDate) &&
                        fromDate.compareTo(toDate) <= 0;
    }

    public String getErrorMessage() {

        if (inventoryStaffIds == null || inventoryStaffIds.isEmpty()) {
            return "Please select at least one staff member.";
        }
        if (TextUtils.isEmpty(fromDate)) {
            return "Please select a start date.";
        }
        if (TextUtils.isEmpty(toDate)) {
            return "Please select an end date.";
        }
        if (fromDate.compareTo(toDate) > 0) {
            return "Start date cannot be after end date.";
        }
        return null;
    }
}

package com.example.ware_house_management_android.dtos.output;

import android.text.TextUtils;

import java.util.ArrayList;

public class AssignOutputDto {
    private ArrayList<String> inventoryStaffIds;
    private String fromDate;
    private String toDate;

    public AssignOutputDto(ArrayList<String> inventoryStaffIds, String fromDate, String toDate) {
        this.inventoryStaffIds = inventoryStaffIds;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public ArrayList<String> getInventoryStaffIds() {
        return inventoryStaffIds;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }    public boolean isValid() {
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

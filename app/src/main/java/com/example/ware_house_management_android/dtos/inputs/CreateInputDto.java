package com.example.ware_house_management_android.dtos.inputs;

import android.text.TextUtils;

import com.example.ware_house_management_android.dtos.input_details.CreateInputDetailsDto;

import java.util.ArrayList;

public class CreateInputDto {
    private String reportStaffId;
    private String supplierId;
    private String description;

    private ArrayList<CreateInputDetailsDto> inputDetails;

    public CreateInputDto(String reportStaffId, String supplierId, String description, ArrayList<CreateInputDetailsDto> inputDetails) {
        this.reportStaffId = reportStaffId;
        this.supplierId = supplierId;
        this.description = description;
        this.inputDetails = inputDetails;
    }

    public String getReportStaffId() {
        return reportStaffId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isValid() {
        return !TextUtils.isEmpty(reportStaffId) &&
                !TextUtils.isEmpty(supplierId) &&
                !TextUtils.isEmpty(description) &&
                inputDetails != null && !inputDetails.isEmpty();

    }

    public String getErrorMessage() {
        if (TextUtils.isEmpty(reportStaffId)) {
            return "Report staff ID cannot be empty.";
        } else if (TextUtils.isEmpty(supplierId)) {
            return "Supplier ID cannot be empty.";
        } else if (TextUtils.isEmpty(description)) {
            return "Description cannot be empty.";
        } else if (inputDetails == null || inputDetails.isEmpty()) {
            return "Items cannot be empty.";
        }
        return null;
    }

    public ArrayList<CreateInputDetailsDto> getItems() {
        return inputDetails;
    }
}
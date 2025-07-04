package com.example.ware_house_management_android.dtos.output;

import com.example.ware_house_management_android.dtos.output_details.CreateOutputDetailsDto;

import java.util.ArrayList;

public class CreateOutputDto {
    private String reportStaffId;
    private String customerId;
    private String description;
    private ArrayList<CreateOutputDetailsDto> outputDetails;

    public CreateOutputDto(String reportStaffId, String customerId, String description, ArrayList<CreateOutputDetailsDto> outputDetails) {
        this.reportStaffId = reportStaffId;
        this.customerId = customerId;
        this.description = description;
        this.outputDetails = outputDetails;
    }

    public String getReportStaffId() {
        return reportStaffId;
    }

    public void setReportStaffId(String reportStaffId) {
        this.reportStaffId = reportStaffId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<CreateOutputDetailsDto> getOutputDetails() {
        return outputDetails;
    }

    public void setOutputDetails(ArrayList<CreateOutputDetailsDto> outputDetails) {
        this.outputDetails = outputDetails;
    }
}

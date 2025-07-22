package com.example.ware_house_management_android.dtos.output;

public class ApproveOutputDto {
    private String managerId;

    public ApproveOutputDto(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerId() {
        return managerId;
    }
}

package com.example.ware_house_management_android.dtos.inputs;

public class ApproveInputDto {
    private String managerId;

    public ApproveInputDto(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}

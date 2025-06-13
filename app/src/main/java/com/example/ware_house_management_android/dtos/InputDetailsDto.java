package com.example.ware_house_management_android.dtos;

public class InputDetailsDto {
    private String baseItemId;
    private int quantity;

    public InputDetailsDto(String baseItemId, int quantity) {
        this.baseItemId = baseItemId;
        this.quantity = quantity;
    }

    public String getBaseItemId() {
        return baseItemId;
    }

    public void setBaseItemId(String baseItemId) {
        this.baseItemId = baseItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

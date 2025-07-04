package com.example.ware_house_management_android.dtos.output_details;

import com.example.ware_house_management_android.dtos.input_details.UpdateInputDetailDto;

import java.util.Objects;

public class CreateOutputDetailsDto {
    private String itemId;
    private int quantity;
    private Double outputPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateOutputDetailsDto)) return false;
        CreateOutputDetailsDto that = (CreateOutputDetailsDto) o;
        return itemId == that.itemId; // Treat objects with same ID as equal
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId);
    }

    public CreateOutputDetailsDto(String itemId, int quantity, Double outputPrice) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.outputPrice = outputPrice;
    }

    public Double getOutputPrice() {
        return outputPrice;
    }

    public void setOutputPrice(Double outputPrice) {
        this.outputPrice = outputPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}

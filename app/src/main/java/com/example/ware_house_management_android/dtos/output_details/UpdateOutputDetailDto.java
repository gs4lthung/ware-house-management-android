package com.example.ware_house_management_android.dtos.output_details;

import com.example.ware_house_management_android.dtos.input_details.UpdateInputDetailDto;

import java.util.Objects;

public class UpdateOutputDetailDto {
    private String id;
    private int quantity;
    private Double outputPrice;
    private String status;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateOutputDetailDto)) return false;
        UpdateOutputDetailDto that = (UpdateOutputDetailDto) o;
        return id == that.id; // Treat objects with same ID as equal
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public UpdateOutputDetailDto(String id, int quantity, Double outputPrice, String status) {
        this.id = id;
        this.quantity = quantity;
        this.outputPrice = outputPrice;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getOutputPrice() {
        return outputPrice;
    }

    public void setOutputPrice(Double outputPrice) {
        this.outputPrice = outputPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

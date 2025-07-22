package com.example.ware_house_management_android.dtos.input_details;

import java.util.Date;
import java.util.Objects;

public class UpdateInputDetailDto {
    private String id;
    private int requestQuantity;
    private int actualQuantity;
    private Double inputPrice;
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateInputDetailDto)) return false;
        UpdateInputDetailDto that = (UpdateInputDetailDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public UpdateInputDetailDto(String id, int requestQuantity, int actualQuantity, Double inputPrice, String status) {
        this.id = id;
        this.requestQuantity = requestQuantity;
        this.actualQuantity = actualQuantity;
        this.inputPrice = inputPrice;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRequestQuantity() {
        return requestQuantity;
    }

    public void setRequestQuantity(int requestQuantity) {
        this.requestQuantity = requestQuantity;
    }

    public int getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public Double getInputPrice() {
        return inputPrice;
    }

    public void setInputPrice(Double inputPrice) {
        this.inputPrice = inputPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

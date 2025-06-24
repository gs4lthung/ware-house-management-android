package com.example.ware_house_management_android.dtos.inputs;

import com.example.ware_house_management_android.models.InputModel;

import java.util.ArrayList;

public class GetInputsResponseDto {
    private ArrayList<InputModel> inputs;
    private int page;
    private int totalPages;

    public GetInputsResponseDto(ArrayList<InputModel> inputs) {
        this.inputs = inputs;
    }

    public ArrayList<InputModel> getInputs() {
        return inputs;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }
}

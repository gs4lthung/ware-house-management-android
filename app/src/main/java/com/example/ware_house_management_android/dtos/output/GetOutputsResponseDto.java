package com.example.ware_house_management_android.dtos.output;

import com.example.ware_house_management_android.models.OutputModel;

import java.util.ArrayList;

public class GetOutputsResponseDto {
    private ArrayList<OutputModel> outputs;
    private int page;
    private int totalPages;

    public GetOutputsResponseDto(ArrayList<OutputModel> outputs) {
        this.outputs = outputs;
    }


    public ArrayList<OutputModel> getOutputs() {
        return outputs;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }
}

package com.example.ware_house_management_android.dtos.output;

import com.example.ware_house_management_android.models.OutputDetailsModel;
import com.example.ware_house_management_android.models.OutputModel;

import java.util.ArrayList;

public class GetOutputByIdResponseDto {
    private OutputModel output;
    private ArrayList<OutputDetailsModel> outputDetails;

    public GetOutputByIdResponseDto(OutputModel output, ArrayList<OutputDetailsModel> outputDetails) {
        this.output = output;
        this.outputDetails = outputDetails;
    }

    public OutputModel getOutput() {
        return output;
    }

    public ArrayList<OutputDetailsModel> getOutputDetails() {
        return outputDetails;
    }
}

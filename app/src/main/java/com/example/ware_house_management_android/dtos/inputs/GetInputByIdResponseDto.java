package com.example.ware_house_management_android.dtos.inputs;

import com.example.ware_house_management_android.models.InputDetailsModel;
import com.example.ware_house_management_android.models.InputModel;

import java.util.ArrayList;

public class GetInputByIdResponseDto {
    private InputModel input;
    private ArrayList<InputDetailsModel> inputDetails;

    public GetInputByIdResponseDto(InputModel input, ArrayList<InputDetailsModel> inputDetails) {
        this.input = input;
        this.inputDetails = inputDetails;
    }

    public InputModel getInput() {
        return input;
    }

    public ArrayList<InputDetailsModel> getInputDetails() {
        return inputDetails;
    }
}

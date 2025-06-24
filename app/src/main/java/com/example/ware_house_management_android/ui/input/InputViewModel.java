package com.example.ware_house_management_android.ui.input;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ware_house_management_android.models.InputDetailsModel;
import com.example.ware_house_management_android.models.InputModel;

import java.util.ArrayList;

public class InputViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<InputModel>> mInputs;
    private final MutableLiveData<ArrayList<InputDetailsModel>> mInputDetails;

    public InputViewModel() {
        mInputs = new MutableLiveData<>(new ArrayList<>());
        mInputDetails = new MutableLiveData<>(new ArrayList<>());
    }

    public MutableLiveData<ArrayList<InputModel>> getInputs() {
        return mInputs;
    }

    public void setInputs(ArrayList<InputModel> inputs) {
        mInputs.setValue(inputs);
    }

    public MutableLiveData<ArrayList<InputDetailsModel>> getInputDetails() {
        return mInputDetails;
    }

    public void setInputDetails(ArrayList<InputDetailsModel> inputDetails) {
        mInputDetails.setValue(inputDetails);
    }
}

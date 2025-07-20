package com.example.ware_house_management_android.view_models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ware_house_management_android.models.OutputModel;

import java.util.ArrayList;

public class OutputViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<OutputModel>> mOutputs;

    public OutputViewModel() {
        mOutputs = new MutableLiveData<>(new ArrayList<>());
    }

    public MutableLiveData<ArrayList<OutputModel>> getOutputs() {
        return mOutputs;
    }

    public void setOutputs(ArrayList<OutputModel> outputs) {
        mOutputs.setValue(outputs);
    }
}

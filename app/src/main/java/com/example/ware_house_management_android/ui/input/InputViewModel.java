package com.example.ware_house_management_android.ui.input;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ware_house_management_android.models.InputModel;

import java.util.ArrayList;

public class InputViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<InputModel>> mInputs;

    public InputViewModel() {
        mInputs = new MutableLiveData<>(new ArrayList<>());
    }

    public MutableLiveData<ArrayList<InputModel>> getInputs() {
        return mInputs;
    }

    public void setInputs(ArrayList<InputModel> inputs) {
        mInputs.setValue(inputs);
    }
}

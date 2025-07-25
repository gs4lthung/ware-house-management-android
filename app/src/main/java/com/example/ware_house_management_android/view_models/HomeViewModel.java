package com.example.ware_house_management_android.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Warehouse Management System");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
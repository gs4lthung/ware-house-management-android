package com.example.ware_house_management_android.ui.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ware_house_management_android.models.UserModel;

import java.util.ArrayList;

public class UserViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    private final MutableLiveData<ArrayList<UserModel>> mUsersList;

    public UserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("List Users");

        mUsersList = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<ArrayList<UserModel>> getUsersList() {
        return mUsersList;
    }

    public void setText(String text) {
        mText.setValue(text);
    }

    public void setUsersList(ArrayList<UserModel> users) {
        mUsersList.setValue(users);
    }
}
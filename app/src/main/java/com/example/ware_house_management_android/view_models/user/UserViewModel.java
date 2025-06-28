package com.example.ware_house_management_android.view_models.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ware_house_management_android.models.UserModel;

import java.util.ArrayList;

public class UserViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    private final MutableLiveData<ArrayList<UserModel>> mUsersList;
    private boolean hasLoadedUsers = false;

    private final MutableLiveData<ArrayList<UserModel>> mSuppliersList;
    private boolean hasLoadedSuppliers = false;

    private final MutableLiveData<ArrayList<UserModel>> mToAssignInventoryStaffsList;
    private boolean hasLoadedToAssignInventoryStaffs = false;
    private final MutableLiveData<ArrayList<UserModel>> mAssignedInventoryStaffsList;


    public UserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("List Users");

        mUsersList = new MutableLiveData<>(new ArrayList<>());
        mSuppliersList = new MutableLiveData<>(new ArrayList<>());
        mToAssignInventoryStaffsList = new MutableLiveData<>(new ArrayList<>());
        mAssignedInventoryStaffsList = new MutableLiveData<>(new ArrayList<>());
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
        hasLoadedUsers = true;
    }

    public boolean hasLoadedUsers() {
        return hasLoadedUsers;
    }

    public LiveData<ArrayList<UserModel>> getSuppliersList() {
        return mSuppliersList;
    }

    public void setSuppliersList(ArrayList<UserModel> suppliers) {
        mSuppliersList.setValue(suppliers);
        hasLoadedSuppliers = true;
    }

    public boolean hasLoadedSuppliers() {
        return hasLoadedSuppliers;
    }

    public LiveData<ArrayList<UserModel>> getToAssignInventoryStaffsList() {
        return mToAssignInventoryStaffsList;
    }

    public void setToAssignInventoryStaffsList(ArrayList<UserModel> toAssignInventoryStaffs) {
        mToAssignInventoryStaffsList.setValue(toAssignInventoryStaffs);
        hasLoadedToAssignInventoryStaffs = true;
    }

    public boolean hasLoadedToAssignInventoryStaffs() {
        return hasLoadedToAssignInventoryStaffs;
    }

    public LiveData<ArrayList<UserModel>> getAssignedInventoryStaffsList() {
        return mAssignedInventoryStaffsList;
    }

    public void setAssignedInventoryStaffsList(ArrayList<UserModel> assignedInventoryStaffs) {
        mAssignedInventoryStaffsList.setValue(assignedInventoryStaffs);
    }
}
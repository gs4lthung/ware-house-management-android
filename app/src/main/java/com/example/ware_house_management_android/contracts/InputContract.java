package com.example.ware_house_management_android.contracts;

import android.widget.TextView;

import com.example.ware_house_management_android.dtos.input_details.UpdateInputDetailDto;
import com.example.ware_house_management_android.models.InputModel;
import com.example.ware_house_management_android.models.UserModel;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;

public interface InputContract {
    interface View {
        void showAssignStaffDialog(String inputId, ArrayList<UserModel> inventoryStaffs);

        void showDatePickerDialog(TextView textView);

        void showSuccess(String message);

        void showError(String error);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void getInputList() throws JSONException;

        void getInputById(String id) throws JSONException;

        void approveInput(String id);

        void assignInput(String id, ArrayList<String> inventoryStaffIds, String fromDate, String toDate) throws JSONException;

        void completeInput(String id);

        void getInventoryStaffList() throws JSONException;

        void updateInputDetails(String id, UpdateInputDetailDto updateInputDetailDto) throws JSONException;
    }
}

package com.example.ware_house_management_android.contracts;

import android.widget.TextView;

import com.example.ware_house_management_android.dtos.output_details.UpdateOutputDetailDto;
import com.example.ware_house_management_android.models.UserModel;

import org.json.JSONException;

import java.util.ArrayList;

public interface OutputContract {
    interface View {
        void showAssignStaffDialog(String outputId, ArrayList<UserModel> inventoryStaffs);

        void showDatePickerDialog(TextView textView);
        void showSuccess(String message);

        void showError(String error);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void getOutputList() throws JSONException;

        void getOutputById(String id) throws JSONException;

        void approveOutput(String id);

        void assignOutput(String id, ArrayList<String> inventoryStaffIds, String fromDate, String toDate) throws JSONException;

        void completeOutput(String id);

        void getInventoryStaffList() throws JSONException;

        void updateOutputDetails(String id, UpdateOutputDetailDto updateOutputDetailDto) throws JSONException;
    }
}

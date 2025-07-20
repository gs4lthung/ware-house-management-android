package com.example.ware_house_management_android.presenters;

import android.content.Context;

import com.example.ware_house_management_android.contracts.OutputContract;
import com.example.ware_house_management_android.dtos.output.GetOutputsResponseDto;
import com.example.ware_house_management_android.models.OutputModel;
import com.example.ware_house_management_android.repositories.OutputRepository;
import com.example.ware_house_management_android.repositories.UserRepository;
import com.example.ware_house_management_android.utils.BaseCallback;
import com.example.ware_house_management_android.view_models.OutputViewModel;
import com.example.ware_house_management_android.view_models.UserViewModel;

import org.json.JSONException;

import java.util.ArrayList;

public class OutputPresenter implements OutputContract.Presenter {
    private OutputViewModel outputViewModel;
    private UserViewModel userViewModel;
    private OutputContract.View view;
    private Context context;

    public OutputPresenter(Context context, OutputViewModel outputViewModel, UserViewModel userViewModel, OutputContract.View view) {
        this.context = context;
        this.outputViewModel = outputViewModel;
        this.userViewModel = userViewModel;
        this.view = view;
    }

    OutputRepository outputRepository;
    UserRepository userRepository;

    @Override
    public void getOutputList() throws JSONException {
        ArrayList<OutputModel> outputs = new ArrayList<>();
        if (view != null) {
            view.showLoading();
        }

        outputRepository = new OutputRepository(context);
        outputRepository.getOutputs().enqueue(new BaseCallback<>(context) {
            @Override
            public void onSuccess(GetOutputsResponseDto data) throws JSONException {
                if (view != null) {
                    view.hideLoading();
                    view.showSuccess("Outputs fetched successfully");
                }

                if (data == null) {
                    view.showError("No outputs found");
                    return;
                }
                for (OutputModel output : data.getOutputs()) {
                    outputs.add(output);
                }

                outputViewModel.setOutputs(outputs);
            }

            @Override
            public void onError(int code, String message) {
                super.onError(code, message);
                if (view != null) {
                    view.hideLoading();
                    view.showError("Error fetching outputs: " + message);
                }
            }
        });
    }

    @Override
    public void getOutputById(String id) throws JSONException {

    }

    @Override
    public void approveOutput(String id) {

    }

    @Override
    public void assignOutput(String id, ArrayList<String> inventoryStaffIds, String fromDate, String toDate) throws JSONException {

    }

    @Override
    public void completeOutput(String id) {

    }

    @Override
    public void getInventoryStaffList() throws JSONException {

    }
}

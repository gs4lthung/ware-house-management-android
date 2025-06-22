package com.example.ware_house_management_android.presenters;

import android.content.Context;
import android.util.Log;

import com.example.ware_house_management_android.contracts.InputContract;
import com.example.ware_house_management_android.dtos.GetInputsResponseDto;
import com.example.ware_house_management_android.dtos.GetUsersResponseDto;
import com.example.ware_house_management_android.models.InputModel;
import com.example.ware_house_management_android.repositories.InputRepository;
import com.example.ware_house_management_android.ui.input.InputViewModel;
import com.example.ware_house_management_android.utils.BaseCallback;

import org.json.JSONException;

import java.util.ArrayList;

public class InputPresenter implements InputContract.Presenter {

    private InputViewModel inputViewModel;
    private InputContract.View view;
    private Context context;

    public InputPresenter(Context context, InputViewModel inputViewModel, InputContract.View view) {
        this.context = context;
        this.inputViewModel = inputViewModel;
        this.view = view;
    }

    InputRepository inputRepository;

    @Override
    public void getInputList() throws JSONException {
        ArrayList<InputModel> inputs = new ArrayList<>();
        if (view != null) {
            view.showLoading();
        }

        inputRepository = new InputRepository(context);
        inputRepository.getInputs().enqueue(new BaseCallback<>() {
            @Override
            public void onSuccess(GetInputsResponseDto data) {
                if (view != null) {
                    view.hideLoading();
                    view.showSuccess("Inputs fetched successfully");
                }

                if (data == null) {
                    view.showError("No inputs found");
                    return;
                }

                for (InputModel input : data.getInputs()) {
                    inputs.add(input);
                }

                inputViewModel.setInputs(inputs);
            }

            @Override
            public void onError(int code, String message) {
                if (view != null) {
                    view.hideLoading();
                    view.showError("Error fetching inputs: " + message);
                    Log.e("InputPresenter", "Error fetching inputs: " + message);
                }
            }
        });


    }
}

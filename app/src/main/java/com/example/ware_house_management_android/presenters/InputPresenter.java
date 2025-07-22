package com.example.ware_house_management_android.presenters;

import android.content.Context;
import android.util.Log;

import com.example.ware_house_management_android.contracts.InputContract;
import com.example.ware_house_management_android.dtos.input_details.UpdateInputDetailDto;
import com.example.ware_house_management_android.dtos.inputs.GetInputByIdResponseDto;
import com.example.ware_house_management_android.dtos.inputs.GetInputsResponseDto;
import com.example.ware_house_management_android.dtos.users.GetUsersResponseDto;
import com.example.ware_house_management_android.models.InputDetailsModel;
import com.example.ware_house_management_android.models.InputModel;
import com.example.ware_house_management_android.models.UserModel;
import com.example.ware_house_management_android.repositories.InputDetailRepository;
import com.example.ware_house_management_android.repositories.InputRepository;
import com.example.ware_house_management_android.repositories.UserRepository;
import com.example.ware_house_management_android.view_models.InputViewModel;
import com.example.ware_house_management_android.utils.BaseCallback;
import com.example.ware_house_management_android.view_models.UserViewModel;

import org.json.JSONException;

import java.util.ArrayList;

public class InputPresenter implements InputContract.Presenter {

    private InputViewModel inputViewModel;
    private UserViewModel userViewModel;
    private InputContract.View view;
    private Context context;

    public InputPresenter(Context context, InputViewModel inputViewModel, UserViewModel userViewModel, InputContract.View view) {
        this.context = context;
        this.inputViewModel = inputViewModel;
        this.userViewModel = userViewModel;
        this.view = view;
    }

    InputRepository inputRepository;
    InputDetailRepository inputDetailRepository;
    UserRepository userRepository;

    @Override
    public void getInputList() throws JSONException {
        ArrayList<InputModel> inputs = new ArrayList<>();
        if (view != null) {
            view.showLoading();
        }

        inputRepository = new InputRepository(context);
        inputRepository.getInputs().enqueue(new BaseCallback<>(context) {
            @Override
            public void onSuccess(GetInputsResponseDto data) {
                if (view != null) {
                    view.hideLoading();
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
                super.onError(code, message);
                if (view != null) {
                    view.hideLoading();
                    view.showError("Error fetching inputs: " + message);
                    Log.e("InputPresenter", "Error fetching inputs: " + message);
                }
            }
        });


    }

    @Override
    public void getInputById(String id) {
        if (view != null) {
            view.showLoading();
        }

        ArrayList<InputDetailsModel> inputDetails = new ArrayList<>();

        inputRepository = new InputRepository(context);
        inputRepository.getInputById(id).enqueue(new BaseCallback<>(context) {
            @Override
            public void onSuccess(GetInputByIdResponseDto data) {
                if (view != null) {
                    view.hideLoading();
                }

                if (data == null || data.getInput() == null) {
                    view.showError("No input found with the given ID");
                    return;
                }

                for (InputDetailsModel inputDetail : data.getInputDetails()) {
                    inputDetails.add(inputDetail);
                }

                inputViewModel.setInputDetails(inputDetails);
            }

            @Override
            public void onError(int code, String message) {
                super.onError(code, message);
                if (view != null) {
                    view.hideLoading();
                    view.showError("Error fetching input details: " + message);
                    Log.e("InputPresenter", "Error fetching input details: " + message);
                }
            }
        });

    }

    @Override
    public void approveInput(String id) {

        if (view != null) {
            view.showLoading();
        }

        inputRepository = new InputRepository(context);
        inputRepository.approveInput(id).enqueue(new BaseCallback<>(context) {
            @Override
            public void onSuccess(Void data) throws JSONException {
                if (view != null) {
                    view.hideLoading();
                    view.showSuccess("Input approved successfully");
                }

                getInputList();
            }

            @Override
            public void onError(int code, String message) {
                super.onError(code, message);
                if (view != null) {
                    view.hideLoading();
                    view.showError("Error approving input: " + message);
                    Log.e("InputPresenter", "Error approving input: " + message);
                }
            }
        });

    }

    @Override
    public void assignInput(String id, ArrayList<String> inventoryStaffIds, String fromDate, String toDate) throws JSONException {
        if (view != null) {
            view.showLoading();
        }

        inputRepository = new InputRepository(context);
        inputRepository.assignInput(id, inventoryStaffIds, fromDate, toDate).enqueue(new BaseCallback<>(context) {
            @Override
            public void onSuccess(Void data) throws JSONException {
                if (view != null) {
                    view.hideLoading();
                    view.showSuccess("Input assigned successfully");
                }

                getInputList();
            }

            @Override
            public void onError(int code, String message) {
                super.onError(code, message);
                if (view != null) {
                    view.hideLoading();
                    view.showError("Error assigning input: " + message);
                    Log.e("InputPresenter", "Error assigning input: " + message);
                }
            }
        });
    }

    @Override
    public void completeInput(String id) {
        if (view != null) {
            view.showLoading();
        }

        inputRepository = new InputRepository(context);
        inputRepository.completeInput(id).enqueue(new BaseCallback<>(context) {
            @Override
            public void onSuccess(Void data) throws JSONException {
                if (view != null) {
                    view.hideLoading();
                    view.showSuccess("Input completed successfully");
                }

                getInputList();
            }

            @Override
            public void onError(int code, String message) {
                super.onError(code, message);
                if (view != null) {
                    view.hideLoading();
                    view.showError("Error completing input: " + message);
                    Log.e("InputPresenter", "Error completing input: " + message);
                }
            }
        });

    }

    @Override
    public void getInventoryStaffList() throws JSONException {
        ArrayList<UserModel> inventoryStaffs = new ArrayList<>();
        if (view != null) {
            view.showLoading();
        }

        userRepository = new UserRepository(context);
        userRepository.getUsers("Inventory Staff").enqueue(new BaseCallback<>(context) {
            @Override
            public void onSuccess(GetUsersResponseDto data) {
                if (view != null) {
                    view.hideLoading();
                }

                if (data == null || data.getUsers() == null) {
                    view.showError("No inventory staff found");
                    return;
                }

                for (UserModel user : data.getUsers()) {
                    inventoryStaffs.add(user);
                }

                userViewModel.setToAssignInventoryStaffsList(inventoryStaffs);

            }

            @Override
            public void onError(int code, String message) {
                super.onError(code, message);
                if (view != null) {
                    view.hideLoading();
                    view.showError("Error fetching inventory staff: " + message);
                    Log.e("InputPresenter", "Error fetching inventory staff: " + message);
                }
            }
        });
    }

    @Override
    public void updateInputDetails(String id, UpdateInputDetailDto updateInputDetailDto) throws JSONException {
        if (view != null) {
            view.showLoading();
        }

        inputDetailRepository = new InputDetailRepository(context);
        inputDetailRepository.updateInputDetail(id, updateInputDetailDto).enqueue(new BaseCallback<>(context) {
            @Override
            public void onSuccess(Void data) {
                if (view != null) {
                    view.hideLoading();
                    view.showSuccess("Input details updated successfully");
                }
            }

            @Override
            public void onError(int code, String message) {
                super.onError(code, message);
                if (view != null) {
                    view.hideLoading();
                    view.showError("Error updating input details: " + message);
                    Log.e("InputPresenter", "Error updating input details: " + message);
                }
            }
        });
    }
}

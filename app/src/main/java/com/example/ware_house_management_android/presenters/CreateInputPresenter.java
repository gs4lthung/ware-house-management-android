package com.example.ware_house_management_android.presenters;

import android.content.Context;

import com.example.ware_house_management_android.contracts.CreateInputContract;
import com.example.ware_house_management_android.dtos.inputs.CreateInputDto;
import com.example.ware_house_management_android.dtos.base_items.GetBaseItemsResponseDto;
import com.example.ware_house_management_android.dtos.users.GetUsersResponseDto;
import com.example.ware_house_management_android.models.BaseItemModel;
import com.example.ware_house_management_android.models.UserModel;
import com.example.ware_house_management_android.repositories.BaseItemRepository;
import com.example.ware_house_management_android.repositories.InputRepository;
import com.example.ware_house_management_android.repositories.UserRepository;
import com.example.ware_house_management_android.view_models.BaseItemViewModel;
import com.example.ware_house_management_android.view_models.UserViewModel;
import com.example.ware_house_management_android.utils.BaseCallback;

import java.util.ArrayList;

public class CreateInputPresenter implements CreateInputContract.Presenter {
    private CreateInputContract.View view;
    private Context context;

    private BaseItemViewModel baseItemViewModel;

    private UserViewModel userViewModel;

    public CreateInputPresenter(Context context, BaseItemViewModel baseItemViewModel, UserViewModel userViewModel, CreateInputContract.View view) {
        this.context = context;
        this.baseItemViewModel = baseItemViewModel;
        this.userViewModel = userViewModel;
        this.view = view;
    }

    InputRepository inputRepository;
    BaseItemRepository baseItemRepository;

    UserRepository userRepository;

    @Override
    public void getBaseItemList() throws Exception {
        ArrayList<BaseItemModel> baseItems = new ArrayList<>();

        if (view != null) {
            view.showLoading();
        }

        baseItemRepository = new BaseItemRepository(context);
        baseItemRepository.getBaseItems().enqueue(new BaseCallback<>(context) {
            @Override
            public void onSuccess(GetBaseItemsResponseDto data) {
                if (view != null) {
                    view.hideLoading();
                    view.showSuccess("Base items fetched successfully");
                }

                if (data != null && data.getBaseItems() != null) {
                    for (BaseItemModel baseItemDto : data.getBaseItems()) {
                        BaseItemModel baseItem = new BaseItemModel(
                                baseItemDto.getId(),
                                baseItemDto.getName(),
                                baseItemDto.getBrand(),
                                baseItemDto.getCountryOfOrigin(),
                                baseItemDto.getStorageType()
                        );
                        baseItems.add(baseItem);
                    }

                    baseItemViewModel.setBaseItemList(baseItems);

                } else {
                    if (view != null) {
                        view.showError("No base items found");
                    }
                }
            }

            @Override
            public void onError(int code, String message) {
                if (view != null) {
                    view.hideLoading();
                    view.showError(message);
                }
            }
        });


    }

    @Override
    public void getSuppliers() throws Exception {
        if (view != null) {
            view.showLoading();
        }

        userRepository = new UserRepository(context);
        userRepository.getUsers("Supplier").enqueue(new BaseCallback<>(context) {
            @Override
            public void onSuccess(GetUsersResponseDto data) {
                if (view != null) {
                    view.hideLoading();
                    view.showSuccess("Suppliers fetched successfully");
                }

                if (data != null && data.getUsers() != null) {
                    ArrayList<UserModel> suppliers = new ArrayList<>();
                    for (UserModel userDto : data.getUsers()) {
                        UserModel user = new UserModel(userDto.getId(), userDto.getFullName(), userDto.getEmail(), userDto.getRole());
                        suppliers.add(user);
                    }

                    userViewModel.setSuppliersList(suppliers);

                } else {
                    if (view != null) {
                        view.showError("No supplier found");
                    }
                }
            }

            @Override
            public void onError(int code, String message) {
                if (view != null) {
                    view.hideLoading();
                    view.showError(message);
                }
            }
        });
    }

    @Override
    public void createInput(CreateInputDto createInputDto) throws Exception {
        if(view != null) {
            view.showLoading();
        }
        if (createInputDto == null) {
            view.showError("CreateInputDto cannot be null");
            throw new IllegalArgumentException("CreateInputDto cannot be null");
        }

        inputRepository = new InputRepository(context);
        inputRepository.createInput(createInputDto).enqueue(new BaseCallback<>(context) {
            @Override
            public void onSuccess(CreateInputDto data) {
                if (view != null) {
                    view.hideLoading();
                    view.showSuccess("Input created successfully");
                }
            }

            @Override
            public void onError(int code, String message) {
                if (view != null) {
                    view.hideLoading();
                    view.showError(message);
                }
            }
        });


    }
}

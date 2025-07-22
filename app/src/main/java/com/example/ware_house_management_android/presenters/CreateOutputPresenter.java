package com.example.ware_house_management_android.presenters;

import android.content.Context;
import android.util.Log;

import com.example.ware_house_management_android.contracts.CreateOutputContract;
import com.example.ware_house_management_android.dtos.output.CreateOutputDto;
import com.example.ware_house_management_android.dtos.items.GetItemsResponseDto;
import com.example.ware_house_management_android.dtos.users.GetUsersResponseDto;
import com.example.ware_house_management_android.models.ItemModel;
import com.example.ware_house_management_android.models.UserModel;
import com.example.ware_house_management_android.repositories.ItemRepository;
import com.example.ware_house_management_android.repositories.OutputRepository;
import com.example.ware_house_management_android.repositories.UserRepository;
import com.example.ware_house_management_android.utils.BaseCallback;
import com.example.ware_house_management_android.view_models.ItemViewModel;
import com.example.ware_house_management_android.view_models.UserViewModel;

import org.json.JSONException;

import java.util.ArrayList;

public class CreateOutputPresenter implements CreateOutputContract.Presenter {
    private CreateOutputContract.View view;
    private Context context;
    private UserViewModel userViewModel;
    private ItemViewModel itemViewModel;

    public CreateOutputPresenter(Context context, UserViewModel userViewModel, ItemViewModel itemViewModel, CreateOutputContract.View view) {
        this.context = context;
        this.userViewModel = userViewModel;
        this.itemViewModel = itemViewModel;
        this.view = view;
    }

    OutputRepository outputRepository;
    UserRepository userRepository;
    ItemRepository itemRepository;

    @Override
    public void getItemList() throws Exception {
        ArrayList<ItemModel> items = new ArrayList<>();

        if (view != null) {
            view.showLoading();
        }

        itemRepository = new ItemRepository(context);
        itemRepository.getItems().enqueue(new BaseCallback<>(context) {
            @Override
            public void onSuccess(GetItemsResponseDto data) throws JSONException {
                if (view != null) {
                    view.hideLoading();
                }

                if (data != null) {
                    for (ItemModel itemDto : data.getItems()) {
                        ItemModel item = new ItemModel(
                                itemDto.get_id(),
                                itemDto.getBaseItemId(),
                                itemDto.getCode(),
                                itemDto.getStatus(),
                                itemDto.getUnit(),
                                itemDto.getStorageQuantity(),
                                itemDto.getSuggestedOutputPrice()
                        );
                        items.add(item);
                    }
                    Log.i("CreateOutputPresenter", "Items fetched: " + items.size());
                    itemViewModel.setCreateOutputItems(items);
                }
            }

            @Override
            public void onError(int code, String message) {

            }
        });
    }

    @Override
    public void getCustomers() throws Exception {
        if (view != null) {
            view.showLoading();
        }

        userRepository = new UserRepository(context);
        userRepository.getUsers("Customer").enqueue(new BaseCallback<>(context) {
            @Override
            public void onSuccess(GetUsersResponseDto data) throws JSONException {
                if (view != null) {
                    view.hideLoading();
                }

                if (data != null && data.getUsers() != null) {
                    ArrayList<UserModel> customers = new ArrayList<>();
                    for (UserModel userDto : data.getUsers()) {
                        UserModel user = new UserModel(userDto.getId(),
                                userDto.getFullName(),
                                userDto.getEmail(),
                                userDto.getRole());
                        customers.add(user);
                    }
                    userViewModel.setCustomersList(customers);
                } else {
                    if (view != null) {
                        view.showError("No customers found");
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
    public void createOutput(CreateOutputDto createOutputDto) throws Exception {
        if (view != null) {
            view.showLoading();
        }

        if (createOutputDto == null) {
            view.showError("CreateOutputDto cannot be null");
            throw new IllegalArgumentException("CreateOutputDto cannot be null");
        }

        outputRepository = new OutputRepository(context);
        outputRepository.createOutput(createOutputDto).enqueue(new BaseCallback<CreateOutputDto>(context) {
            @Override
            public void onSuccess(CreateOutputDto data) {
                if (view != null) {
                    view.hideLoading();
                    view.showSuccess("Output created successfully");
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

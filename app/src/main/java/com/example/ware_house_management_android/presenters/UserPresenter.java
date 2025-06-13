package com.example.ware_house_management_android.presenters;

import android.content.Context;
import android.util.Log;

import com.example.ware_house_management_android.utils.BaseCallback;
import com.example.ware_house_management_android.contracts.UserContract;
import com.example.ware_house_management_android.dtos.GetUsersResponseDto;
import com.example.ware_house_management_android.models.UserModel;
import com.example.ware_house_management_android.repositories.UserRepository;
import com.example.ware_house_management_android.ui.user.UserViewModel;

import org.json.JSONException;

import java.util.ArrayList;

public class UserPresenter implements UserContract.Presenter {
    private UserViewModel userViewModel;
    private UserContract.View view;
    private Context context;

    public UserPresenter(Context context, UserViewModel userViewModel, UserContract.View view) {
        this.context = context;
        this.userViewModel = userViewModel;
        this.view = view;
    }

    UserRepository userRepository;

    @Override
    public void getUsersList() throws JSONException {
        ArrayList<UserModel> users = new ArrayList<>();

        if (view != null) {
            view.showLoading();
        }

        userRepository = new UserRepository(context);
        userRepository.getUsers("").enqueue(new BaseCallback<>() {
            @Override
            public void onSuccess(GetUsersResponseDto data) {
                if (view != null) {
                    view.hideLoading();
                    view.showSuccess("Users fetched successfully");
                }

                if (data != null && data.getUsers() != null) {
                    for (UserModel userDto : data.getUsers()) {
                        UserModel user = new UserModel(userDto.getId(), userDto.getFullName(), userDto.getEmail(), userDto.getRole());
                        users.add(user);
                    }

                    userViewModel.setUsersList(users);
                } else {
                    Log.e("UserPresenter", "No users found in response");
                    view.showError("No users found");
                }
            }

            @Override
            public void onError(int code, String message) {
                if (view != null) {
                    view.hideLoading();
                    view.showError("Error fetching users: " + message);
                }


            }
        });
    }
}

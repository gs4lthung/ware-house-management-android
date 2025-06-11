package com.example.ware_house_management_android.presenters;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.ware_house_management_android.BaseCallback;
import com.example.ware_house_management_android.contracts.UserContract;
import com.example.ware_house_management_android.dtos.GetUsersResponseDto;
import com.example.ware_house_management_android.enums.UserRoleEnum;
import com.example.ware_house_management_android.models.UserModel;
import com.example.ware_house_management_android.repositories.UserRepository;
import com.example.ware_house_management_android.ui.user.UserViewModel;

import java.util.ArrayList;

import retrofit2.Call;

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
    public void getUsersList() {
        ArrayList<UserModel> users = new ArrayList<>();

        if (view != null) {
            view.showLoading();
        }

        userRepository = new UserRepository(context);
        userRepository.getUsers().enqueue(new BaseCallback<>() {
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

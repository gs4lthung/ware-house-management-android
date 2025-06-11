package com.example.ware_house_management_android.presenters;

import android.content.Context;
import android.content.Intent;

import com.example.ware_house_management_android.LoginActivity;
import com.example.ware_house_management_android.contracts.auth.LogoutContract;

public class LogoutPresenter implements LogoutContract.Presenter {
    private final LogoutContract.View view;
    private final Context context;

    public LogoutPresenter(Context context, LogoutContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void logout() {
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                .edit()
                .remove("user")
                .remove("access_token")
                .apply();

        if (view != null) {
            view.showLogoutSuccess("Logout successful");
        }

        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void onDestroy() {

    }
}

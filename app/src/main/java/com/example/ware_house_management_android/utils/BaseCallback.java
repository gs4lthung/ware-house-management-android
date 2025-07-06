package com.example.ware_house_management_android.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.ware_house_management_android.LoginActivity;
import com.example.ware_house_management_android.dtos.APIErrorResponseDto;
import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.google.gson.Gson;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Response;

public abstract class BaseCallback<T> implements retrofit2.Callback<APIResponseDto<T>> {
    Context context;

    public BaseCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<APIResponseDto<T>> call, Response<APIResponseDto<T>> response) {
        if (response.isSuccessful() && response.body() != null) {
            try {
                onSuccess(response.body().getMetadata());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } else {
            APIErrorResponseDto error = parseError(response);
            onError(error.getCode(), error.getMessage());
        }
    }

    @Override
    public void onFailure(Call<APIResponseDto<T>> call, Throwable t) {
        onError(-1, t.getMessage() != null ? t.getMessage() : "Network error");
    }

    private APIErrorResponseDto parseError(Response<?> response) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(response.errorBody().charStream(), APIErrorResponseDto.class);
        } catch (Exception e) {
            APIErrorResponseDto fallback = new APIErrorResponseDto();
            fallback.setCode(response.code());
            fallback.setMessage("Unexpected error");
            return fallback;
        }
    }

    public abstract void onSuccess(T data) throws JSONException;

    public void onError(int code, String message) {
        if (message.contains("Token is expired")) {
            AppUtil.clearCurrentUser(context);
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }
    }
}

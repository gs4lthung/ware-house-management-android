package com.example.ware_house_management_android;

import com.example.ware_house_management_android.dtos.APIErrorResponseDto;
import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Response;

public abstract class BaseCallback<T> implements retrofit2.Callback<APIResponseDto<T>> {

    @Override
    public void onResponse(Call<APIResponseDto<T>> call, Response<APIResponseDto<T>> response) {
        if (response.isSuccessful() && response.body() != null) {
            onSuccess(response.body().getMetadata());
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

    public abstract void onSuccess(T data);

    public abstract void onError(int code, String message);
}

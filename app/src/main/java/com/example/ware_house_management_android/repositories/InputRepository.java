package com.example.ware_house_management_android.repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.inputs.ApproveInputDto;
import com.example.ware_house_management_android.dtos.inputs.AssignInputDto;
import com.example.ware_house_management_android.dtos.inputs.CreateInputDto;
import com.example.ware_house_management_android.dtos.inputs.GetInputByIdResponseDto;
import com.example.ware_house_management_android.dtos.inputs.GetInputsResponseDto;
import com.example.ware_house_management_android.services.InputService;
import com.example.ware_house_management_android.utils.APIClient;
import com.example.ware_house_management_android.utils.AppUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;

public class InputRepository {
    private static InputService inputService;
    private final Context context;

    private SharedPreferences sharedPreferences;

    public InputRepository(Context context) {
        this.context = context.getApplicationContext();
        inputService = APIClient.getClient().create(InputService.class);
        sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
    }

    public Call<APIResponseDto<GetInputsResponseDto>> getInputs() throws JSONException {
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;

        JSONObject filter = new JSONObject();
        String userRole = AppUtil.currentUser(context).getRole();
        switch (userRole) {
            case "Inventory Staff":
                Log.i("InputRepository", "User is Inventory Staff" +
                        AppUtil.currentUser(context).getId().toString());
                filter.put("inventoryStaffIds", AppUtil.currentUser(context).getId().toString());
                break;
        }

        return inputService.getInputs(authHeader, filter, InputService.SELECT_PATH, InputService.EXPAND_PATH);
    }

    public Call<APIResponseDto<GetInputByIdResponseDto>> getInputById(String id) {
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;
        return inputService.getInputById(authHeader, id);
    }

    public Call<APIResponseDto<CreateInputDto>> createInput(CreateInputDto createInputDto) {
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;
        return inputService.createInput(authHeader, createInputDto);
    }

    public Call<APIResponseDto<Void>> approveInput(String id) {
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;
        return inputService.approveInput(authHeader, id, new ApproveInputDto(AppUtil.currentUser(context).getId()));
    }

    public Call<APIResponseDto<Void>> assignInput(String id, ArrayList<String> inventoryStaffIds, String fromDate, String toDate) {
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;
        return inputService.assignInput(authHeader, id, new AssignInputDto(
                inventoryStaffIds,
                fromDate,
                toDate
        ));
    }
}

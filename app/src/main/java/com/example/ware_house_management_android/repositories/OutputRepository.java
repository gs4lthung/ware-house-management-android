package com.example.ware_house_management_android.repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.ware_house_management_android.dtos.APIResponseDto;
import com.example.ware_house_management_android.dtos.inputs.AssignInputDto;
import com.example.ware_house_management_android.dtos.output.ApproveOutputDto;
import com.example.ware_house_management_android.dtos.output.AssignOutputDto;
import com.example.ware_house_management_android.dtos.output.CreateOutputDto;
import com.example.ware_house_management_android.dtos.output.GetOutputByIdResponseDto;
import com.example.ware_house_management_android.dtos.output.GetOutputsResponseDto;
import com.example.ware_house_management_android.services.OutputService;
import com.example.ware_house_management_android.utils.APIClient;
import com.example.ware_house_management_android.utils.AppUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;

public class OutputRepository {
    private static OutputService outputService;
    private final Context context;
    private SharedPreferences sharedPreferences;

    public OutputRepository(Context context) {
        this.context = context.getApplicationContext();
        outputService = APIClient.getClient().create(OutputService.class);
        sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
    }

    public Call<APIResponseDto<GetOutputsResponseDto>> getOutputs() throws JSONException {
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

        return outputService.getOutputs(authHeader, filter, OutputService.SELECT_PATH, OutputService.EXPAND_PATH);
    }

    public Call<APIResponseDto<GetOutputByIdResponseDto>> getOutputById(String id) {
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;
        return outputService.getOutputById(authHeader, id);
    }

    public Call<APIResponseDto<CreateOutputDto>> createOutput(CreateOutputDto createOutputDto) {
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;
        return outputService.createOutput(authHeader, createOutputDto);
    }

    public Call<APIResponseDto<Void>> approveOutput(String id) {
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;
        return outputService.approveOutput(authHeader, id, new ApproveOutputDto(AppUtil.currentUser(context).getId().toString()));
    }

    public Call<APIResponseDto<Void>> assignOutput(String id, ArrayList<String> inventoryStaffIds, String fromDate, String toDate) {
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;
        return outputService.assignOutput(authHeader, id, new AssignOutputDto(
                inventoryStaffIds,
                fromDate,
                toDate
        ));
    }

    public Call<APIResponseDto<Void>> completeOutput(String id) {
        String accessToken = sharedPreferences.getString("access_token", "");

        if (accessToken == null) {
            throw new IllegalStateException("Access token is not available in SharedPreferences");
        }

        String authHeader = "Bearer " + accessToken;
        return outputService.completeOutput(authHeader, id);
    }
}

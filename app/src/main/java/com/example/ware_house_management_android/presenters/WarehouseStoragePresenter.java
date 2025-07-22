package com.example.ware_house_management_android.presenters;

import android.content.Context;
import android.util.Log;

import com.example.ware_house_management_android.contracts.WarehouseStorageContract;
import com.example.ware_house_management_android.dtos.warehouse_storages.GetWarehouseStoragesResponseDto;
import com.example.ware_house_management_android.models.WarehouseStorageModel;
import com.example.ware_house_management_android.repositories.WarehouseStorageRepository;
import com.example.ware_house_management_android.utils.BaseCallback;
import com.example.ware_house_management_android.view_models.WarehouseViewModel;

import org.json.JSONException;

import java.util.ArrayList;

public class WarehouseStoragePresenter implements WarehouseStorageContract.Presenter {
    private WarehouseViewModel warehouseViewModel;
    private WarehouseStorageContract.View view;
    private Context context;

    public WarehouseStoragePresenter(Context context, WarehouseViewModel warehouseViewModel, WarehouseStorageContract.View view) {
        this.context = context;
        this.warehouseViewModel = warehouseViewModel;
        this.view = view;
    }

    WarehouseStorageRepository warehouseStorageRepository;

    @Override
    public void getWarehouseStorageList() {
        ArrayList<WarehouseStorageModel> warehouseStorages = new ArrayList<>();

        if (view != null) {
            view.showLoading();
        }

        warehouseStorageRepository = new WarehouseStorageRepository(context);
        warehouseStorageRepository.getWarehouseStorages().enqueue(new BaseCallback<GetWarehouseStoragesResponseDto>(context) {
            @Override
            public void onSuccess(GetWarehouseStoragesResponseDto data) {
                if (view != null) {
                    view.hideLoading();
                    view.showSuccess("Warehouse Storages fetched successfully");
                }

                if (data != null && data.getWarehouseStorages() != null) {
                    for (WarehouseStorageModel warehouseStorageDto : data.getWarehouseStorages()) {
                        WarehouseStorageModel warehouseStorage = new WarehouseStorageModel(
                                warehouseStorageDto.get_id(),
                                warehouseStorageDto.getWarehouseId(),
                                warehouseStorageDto.getItemId(),
                                warehouseStorageDto.getBatchNumber(),
                                warehouseStorageDto.getQuantity()
                        );
                        warehouseStorages.add(warehouseStorage);
                    }
                    warehouseViewModel.setWarehouseStorageList(warehouseStorages);
                } else {
                    if (view != null) {
                        view.showError("No warehouse storages found");
                    }
                }
            }

            @Override
            public void onError(int code, String message) {
                if (view != null) {
                    view.hideLoading();
                    view.showError("Error fetching warehouse storages: " + message);
                    Log.e("WarehouseStoragePresenter", "Error code: " + code + ", message: " + message);
                }


            }
        });
    }
}

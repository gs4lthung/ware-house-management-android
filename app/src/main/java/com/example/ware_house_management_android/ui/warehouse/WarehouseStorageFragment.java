package com.example.ware_house_management_android.ui.warehouse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ware_house_management_android.adapters.WarehouseStorageAdapter;
import com.example.ware_house_management_android.contracts.WarehouseStorageContract;
import com.example.ware_house_management_android.databinding.FragmentWarehouseStorageBinding;
import com.example.ware_house_management_android.presenters.WarehouseStoragePresenter;
import com.example.ware_house_management_android.view_models.WarehouseViewModel;

public class WarehouseStorageFragment extends Fragment implements WarehouseStorageContract.View {
    private FragmentWarehouseStorageBinding binding;
    private RecyclerView listWarehouseStorage;
    private ProgressBar progressBar;
    private WarehouseStoragePresenter warehouseStoragePresenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWarehouseStorageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        WarehouseViewModel warehouseViewModel = new ViewModelProvider(this).get(WarehouseViewModel.class);


        progressBar = binding.progressBarWarehouseStorage;
        listWarehouseStorage = binding.recyclerViewWarehouseStorage;
        listWarehouseStorage.setLayoutManager(new GridLayoutManager(this.getContext(), 1));

        warehouseStoragePresenter = new WarehouseStoragePresenter(getContext(), warehouseViewModel, this);

        try {
            warehouseStoragePresenter.getWarehouseStorageList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        warehouseViewModel.getWarehouseStorageList().observe(getViewLifecycleOwner(), warehouseStorageList -> {
            if (warehouseStorageList != null) {
                listWarehouseStorage.setAdapter(new WarehouseStorageAdapter(getContext(), warehouseStorageList));
            } else {
                showError("Failed to load warehouse storage data.");
            }
        });
        return root;
    }


    @Override
    public void showSuccess(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        listWarehouseStorage.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        listWarehouseStorage.setVisibility(View.VISIBLE);

    }
}

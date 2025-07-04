package com.example.ware_house_management_android.ui.input;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ware_house_management_android.adapters.CreateInputBaseItemAdapter;
import com.example.ware_house_management_android.contracts.CreateInputContract;
import com.example.ware_house_management_android.databinding.FragmentCreateInputBinding;
import com.example.ware_house_management_android.dtos.inputs.CreateInputDto;
import com.example.ware_house_management_android.dtos.input_details.CreateInputDetailsDto;
import com.example.ware_house_management_android.models.UserModel;
import com.example.ware_house_management_android.presenters.CreateInputPresenter;
import com.example.ware_house_management_android.view_models.BaseItemViewModel;
import com.example.ware_house_management_android.view_models.UserViewModel;
import com.example.ware_house_management_android.utils.AppUtil;

import java.util.ArrayList;

public class CreateInputFragment extends Fragment implements CreateInputContract.View {

    private FragmentCreateInputBinding binding;

    private CreateInputPresenter createInputPresenter;

    private CreateInputBaseItemAdapter createInputBaseItemAdapter;

    private RecyclerView listBaseItems;

    private Spinner spinnerReportStaff;

    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        UserViewModel userViewModel =
                new ViewModelProvider(this).get(UserViewModel.class);
        BaseItemViewModel baseItemViewModel =
                new ViewModelProvider(this).get(BaseItemViewModel.class);

        binding = FragmentCreateInputBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        createInputPresenter = new CreateInputPresenter(this.getContext(), baseItemViewModel, userViewModel, this);
        try {
            if (!baseItemViewModel.hasLoadedBaseItems())
                createInputPresenter.getBaseItemList();
            if (!userViewModel.hasLoadedSuppliers())
                createInputPresenter.getSuppliers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        listBaseItems = binding.recyclerViewBaseItems;
        listBaseItems.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this.getContext()));
        spinnerReportStaff = binding.spinnerReportStaff;
        progressBar = binding.progressBar;

        createInputBaseItemAdapter = new CreateInputBaseItemAdapter(this.getContext(), null);
        baseItemViewModel.getBaseItemList().observe(getViewLifecycleOwner(), baseItems -> {
            createInputBaseItemAdapter.setBaseItemList(baseItems);
            listBaseItems.setAdapter(createInputBaseItemAdapter);
        });
        userViewModel.getSuppliersList().observe(getViewLifecycleOwner(), suppliers -> {
            setReportStaffSpinnerAdapter(suppliers);
        });


        UserModel reportStaff = AppUtil.currentUser(getContext());
        EditText descriptionEditText = binding.etDescription;
        Button createInputButton = binding.btnSubmit;
        createInputButton.setOnClickListener(v -> {
            ArrayList<CreateInputDetailsDto> inputDetails = new ArrayList<>();
            for (CreateInputDetailsDto inputDetail : createInputBaseItemAdapter.getInputDetails()) {
                if (inputDetail.getQuantity() > 0) {
                    inputDetails.add(inputDetail);
                }
            }


            CreateInputDto createInputDto = new CreateInputDto(
                    reportStaff.getId(),
                    userViewModel.getSuppliersList().getValue().get(spinnerReportStaff.getSelectedItemPosition()).getId(),
                    descriptionEditText.getText().toString(),
                    inputDetails
            );

            try {
                createInputPresenter.createInput(createInputDto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });

        return root;
    }

    private void setReportStaffSpinnerAdapter(ArrayList<UserModel> suppliers) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            ArrayAdapter<String> reportStaffSpinnerAdapter = new ArrayAdapter<>(
                    getContext(),
                    android.R.layout.simple_spinner_item,
                    suppliers.stream().map(UserModel::getFullName)
                            .toList()
            );
            reportStaffSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerReportStaff.setAdapter(reportStaffSpinnerAdapter);
            spinnerReportStaff.setSelection(0);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void showSuccess(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        Log.e("CreateInputFragment", "Error: " + error);
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
        if (listBaseItems != null) {
            listBaseItems.setVisibility(View.GONE);
        }
        if (spinnerReportStaff != null) {
            spinnerReportStaff.setVisibility(View.GONE);
        }
        if (binding != null) {
            binding.btnSubmit.setEnabled(false);
        }
        if (binding != null) {
            binding.etDescription.setEnabled(false);
        }
    }

    @Override
    public void hideLoading() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
        if (listBaseItems != null) {
            listBaseItems.setVisibility(View.VISIBLE);
        }
        if (spinnerReportStaff != null) {
            spinnerReportStaff.setVisibility(View.VISIBLE);
        }
        if (binding != null) {
            binding.btnSubmit.setEnabled(true);
        }
        if (binding != null) {
            binding.etDescription.setEnabled(true);
        }
    }
}
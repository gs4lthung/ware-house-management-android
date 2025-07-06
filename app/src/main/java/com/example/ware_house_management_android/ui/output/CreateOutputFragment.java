package com.example.ware_house_management_android.ui.output;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ware_house_management_android.adapters.CreateOutputItemAdapter;
import com.example.ware_house_management_android.contracts.CreateOutputContract;
import com.example.ware_house_management_android.databinding.FragmentCreateOutputBinding;
import com.example.ware_house_management_android.models.UserModel;
import com.example.ware_house_management_android.presenters.CreateOutputPresenter;
import com.example.ware_house_management_android.view_models.ItemViewModel;
import com.example.ware_house_management_android.view_models.UserViewModel;

import java.util.ArrayList;

public class CreateOutputFragment extends Fragment implements CreateOutputContract.View {
    private FragmentCreateOutputBinding binding;

    private CreateOutputPresenter createOutputPresenter;

    private CreateOutputItemAdapter createOutputItemAdapter;

    private RecyclerView listItems;
    private Spinner spinnerCustomer;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        ItemViewModel itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        binding = FragmentCreateOutputBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        createOutputPresenter = new CreateOutputPresenter(this.getContext(), userViewModel, itemViewModel, this);
        try {
            Log.i("CreateOutputFragment", "Initializing CreateOutputPresenter");
            createOutputPresenter.getItemList();
            if (!userViewModel.hasLoadedCustomers()) createOutputPresenter.getCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        listItems = binding.recyclerViewItems;
        listItems.setLayoutManager(new LinearLayoutManager(this.getContext()));
        spinnerCustomer = binding.spinnerCustomer;
        progressBar = binding.progressBar;

        createOutputItemAdapter = new CreateOutputItemAdapter(this.getContext(), null);
        itemViewModel.getCreateOutputItems().observe(getViewLifecycleOwner(), items -> {
            createOutputItemAdapter.setItemList(items);
            listItems.setAdapter(createOutputItemAdapter);
        });

        userViewModel.getCustomersList().observe(getViewLifecycleOwner(), customers -> {
            if (customers != null && !customers.isEmpty()) {
                setCustomerSpinnerAdapter(customers);
            } else {
                Log.w("CreateOutputFragment", "No customers found");
            }
        });


        return root;
    }

    private void setCustomerSpinnerAdapter(ArrayList<UserModel> customers) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            ArrayAdapter<String> customerSpinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, customers.stream().map(UserModel::getFullName).toList());
            customerSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCustomer.setAdapter(customerSpinnerAdapter);
            spinnerCustomer.setSelection(0);
        }

    }

    @Override
    public void showSuccess(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        Log.e("CreateOutputFragment", "Error: " + error);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}

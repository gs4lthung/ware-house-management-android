package com.example.ware_house_management_android.ui.output;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.ware_house_management_android.adapters.OutputAdapter;
import com.example.ware_house_management_android.contracts.OutputContract;
import com.example.ware_house_management_android.databinding.FragmentOutputBinding;
import com.example.ware_house_management_android.presenters.OutputPresenter;
import com.example.ware_house_management_android.view_models.OutputViewModel;
import com.example.ware_house_management_android.view_models.UserViewModel;

public class OutputFragment extends Fragment implements OutputContract.View {
    private FragmentOutputBinding binding;
    private OutputPresenter outputPresenter;
    private RecyclerView listOutputs;
    private ProgressBar progressBar;
    private Dialog currentDialog;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        OutputViewModel listOutputViewModel = new ViewModelProvider(this).get(OutputViewModel.class);
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding = FragmentOutputBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        progressBar = binding.progressBar;

        outputPresenter = new OutputPresenter(this.getContext(), listOutputViewModel, userViewModel, this);
        try {
            outputPresenter.getOutputList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        listOutputs = binding.recyclerViewOutput;
        listOutputs.setLayoutManager(new LinearLayoutManager(this.getContext()));
        listOutputViewModel.getOutputs().observe(getViewLifecycleOwner(), outputs -> {
            if (outputs != null) {
                listOutputs.setAdapter(new OutputAdapter(this.getContext(), outputs, item -> {
                    
                }));
            }
        });
        return root;
    }

    @Override
    public void showSuccess(String message) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}

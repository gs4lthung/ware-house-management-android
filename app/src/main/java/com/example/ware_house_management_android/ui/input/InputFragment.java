package com.example.ware_house_management_android.ui.input;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ware_house_management_android.adapters.InputAdapter;
import com.example.ware_house_management_android.contracts.InputContract;
import com.example.ware_house_management_android.databinding.FragmentInputBinding;
import com.example.ware_house_management_android.presenters.InputPresenter;

public class InputFragment extends Fragment implements InputContract.View {
    private FragmentInputBinding binding;
    private InputPresenter inputPresenter;
    private RecyclerView listInputs;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        InputViewModel listInputViewModel = new ViewModelProvider(this).get(InputViewModel.class);

        binding = FragmentInputBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        inputPresenter = new InputPresenter(this.getContext(), listInputViewModel, this);
        try {
            inputPresenter.getInputList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        listInputs = binding.recyclerViewInput;
        listInputs.setLayoutManager(new LinearLayoutManager(this.getContext()));
        listInputViewModel.getInputs().observe(getViewLifecycleOwner(), inputs -> {;
            if (inputs != null) {
                listInputs.setAdapter(new InputAdapter(this.getContext(), inputs));
            } else {
                Toast.makeText(getContext(), "No inputs found", Toast.LENGTH_SHORT).show();
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


    }

    @Override
    public void hideLoading() {

    }
}

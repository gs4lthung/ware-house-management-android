package com.example.ware_house_management_android.ui.input;


import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ware_house_management_android.R;
import com.example.ware_house_management_android.adapters.InputAdapter;
import com.example.ware_house_management_android.adapters.InputDetailsAdapter;
import com.example.ware_house_management_android.contracts.InputContract;
import com.example.ware_house_management_android.databinding.FragmentInputBinding;
import com.example.ware_house_management_android.presenters.InputPresenter;

import org.json.JSONException;

public class InputFragment extends Fragment implements InputContract.View {
    private FragmentInputBinding binding;
    private InputPresenter inputPresenter;
    private RecyclerView listInputs;
    private ProgressBar progressBar;
    private Dialog currentDialog;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        InputViewModel listInputViewModel = new ViewModelProvider(this).get(InputViewModel.class);

        binding = FragmentInputBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        progressBar = binding.progressBar;

        inputPresenter = new InputPresenter(this.getContext(), listInputViewModel, this);
        try {
            inputPresenter.getInputList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        listInputs = binding.recyclerViewInput;
        listInputs.setLayoutManager(new LinearLayoutManager(this.getContext()));
        listInputViewModel.getInputs().observe(getViewLifecycleOwner(), inputs -> {
            ;
            if (inputs != null) {
                listInputs.setAdapter(new InputAdapter(this.getContext(), inputs, item -> {
                    inputPresenter.getInputById(item.getId());

                    View dialogInputDetails = LayoutInflater.from(getContext()).inflate(R.layout.dialog_input_details, null);
                    RecyclerView listInputDetails = dialogInputDetails.findViewById(R.id.recycler_view_input_details);
                    listInputDetails.setLayoutManager(new LinearLayoutManager(getContext()));

                    Dialog dialog = new Dialog(requireContext());
                    dialog.setContentView(dialogInputDetails);

                    Window window = dialog.getWindow();
                    if (window != null) {
                        window.setLayout((int) (getResources().getDisplayMetrics().widthPixels * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
                    }

                    dialog.setTitle("Input Details");

                    Button actionButton = dialogInputDetails.findViewById(R.id.button_action);
                    if (item.getStatus().equals("Pending")) {
                        actionButton.setText("Approve");
                    } else {
                        actionButton.setText("View Details");
                    }

                    actionButton.setOnClickListener(v -> {
                        if (item.getStatus().equals("Pending")) {
                            inputPresenter.approveInput(item.getId());
                        } else {
                            Toast.makeText(getContext(), "Input is already approved", Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
                    });


                    dialog.setCancelable(true);
                    dialog.show();

                    currentDialog = dialog;
                }));
            } else {
                Toast.makeText(getContext(), "No inputs found", Toast.LENGTH_SHORT).show();
            }
        });

        listInputViewModel.getInputDetails().observe(getViewLifecycleOwner(), inputDetails -> {
            ;
            if (inputDetails != null && currentDialog != null) {
                RecyclerView listInputDetails = currentDialog.findViewById(R.id.recycler_view_input_details);
                listInputDetails.setAdapter(new InputDetailsAdapter(this.getContext(), inputDetails));
            } else {
                Toast.makeText(getContext(), "No input details found", Toast.LENGTH_SHORT).show();
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
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoading() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
}

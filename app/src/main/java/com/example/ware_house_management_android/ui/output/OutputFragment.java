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
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ware_house_management_android.R;
import com.example.ware_house_management_android.adapters.OutputAdapter;
import com.example.ware_house_management_android.adapters.OutputDetailsAdapter;
import com.example.ware_house_management_android.adapters.UserAdapter;
import com.example.ware_house_management_android.contracts.OutputContract;
import com.example.ware_house_management_android.databinding.FragmentOutputBinding;
import com.example.ware_house_management_android.dtos.output_details.UpdateOutputDetailDto;
import com.example.ware_house_management_android.presenters.OutputPresenter;
import com.example.ware_house_management_android.utils.AppUtil;
import com.example.ware_house_management_android.view_models.OutputViewModel;
import com.example.ware_house_management_android.view_models.UserViewModel;

import org.json.JSONException;

import java.util.ArrayList;

public class OutputFragment extends Fragment implements OutputContract.View {
    private FragmentOutputBinding binding;
    private OutputPresenter outputPresenter;
    private RecyclerView listOutputs;
    private ProgressBar progressBar;
    private Dialog currentDialog;
    private ArrayList<UpdateOutputDetailDto> updateOutputDetails = new ArrayList<>();

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
                    outputPresenter.getOutputById(item.get_id());
                    View dialogOutputDetails = LayoutInflater.from(getContext()).inflate(R.layout.dialog_output_details, null);
                    RecyclerView listOutputDetails = dialogOutputDetails.findViewById(R.id.recycler_view_output_details);
                    listOutputDetails.setLayoutManager(new LinearLayoutManager(getContext()));

                    RecyclerView listAssignedInventoryStaffs = dialogOutputDetails.findViewById(R.id.recycler_view_inventory_staffs);
                    listAssignedInventoryStaffs.setLayoutManager(new LinearLayoutManager(getContext()));
                    listOutputDetails.setAdapter(new UserAdapter(this.getContext(), item.getInventoryStaffIds()));

                    Dialog dialog = new Dialog(requireContext());
                    dialog.setContentView(dialogOutputDetails);

                    Window window = dialog.getWindow();
                    if (window != null) {
                        window.setLayout((int) (getResources().getDisplayMetrics().widthPixels * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
                    }

                    dialog.setTitle("Output Details");

                    String userRole = AppUtil.currentUser(this.getContext()).getRole();
                    Button actionButton = dialogOutputDetails.findViewById(R.id.button_action);
                    if (item.getStatus().equals("Pending")) {
                        if (userRole.equals("Report Staff")) actionButton.setText("Update");
                        else actionButton.setText("Approve");
                    } else if (item.getStatus().equals("Approved")) {
                        actionButton.setText("Assign Staffs");
                    } else if (item.getStatus().equals("Assigned")) {
                        if (userRole.equals("Inventory Staff")) {
                            actionButton.setText("Complete");
                        } else {
                            actionButton.setText("View Details");
                        }
                    } else {
                        actionButton.setVisibility(View.GONE);
                    }

                    actionButton.setOnClickListener(v -> {
                        if (item.getStatus().equals("Pending")) {
                            if (userRole.equals("Report Staff")) {
                                if (updateOutputDetails == null || updateOutputDetails.isEmpty()) {
                                    Toast.makeText(getContext(), "No output details to update", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                for (UpdateOutputDetailDto detail : updateOutputDetails) {
                                    try {
                                        outputPresenter.updateOutputDetails(item.get_id(), detail);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    });

                    dialog.setCancelable(true);
                    dialog.show();

                    currentDialog = dialog;

                }));
            } else {
                Toast.makeText(getContext(), "No outputs found", Toast.LENGTH_SHORT).show();
            }

            listOutputViewModel.getOutputDetails().observe(getViewLifecycleOwner(), outputDetails -> {
                if (outputDetails != null && currentDialog != null) {
                    RecyclerView listOutputDetails = currentDialog.findViewById(R.id.recycler_view_output_details);
                    OutputDetailsAdapter outputDetailsAdapter = new OutputDetailsAdapter(getContext(), outputDetails);
                    listOutputDetails.setAdapter(outputDetailsAdapter);

                    updateOutputDetails = outputDetailsAdapter.getUpdateOutputDetailsList();
                } else {
                    Toast.makeText(getContext(), "No output details found", Toast.LENGTH_SHORT).show();
                }
            });
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

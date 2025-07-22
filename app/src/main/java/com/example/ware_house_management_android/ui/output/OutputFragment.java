package com.example.ware_house_management_android.ui.output;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ware_house_management_android.R;
import com.example.ware_house_management_android.adapters.AssignStaffAdapter;
import com.example.ware_house_management_android.adapters.OutputAdapter;
import com.example.ware_house_management_android.adapters.OutputDetailsAdapter;
import com.example.ware_house_management_android.adapters.UserAdapter;
import com.example.ware_house_management_android.contracts.OutputContract;
import com.example.ware_house_management_android.databinding.FragmentOutputBinding;
import com.example.ware_house_management_android.dtos.inputs.AssignInputDto;
import com.example.ware_house_management_android.dtos.output.AssignOutputDto;
import com.example.ware_house_management_android.dtos.output_details.UpdateOutputDetailDto;
import com.example.ware_house_management_android.models.UserModel;
import com.example.ware_house_management_android.presenters.OutputPresenter;
import com.example.ware_house_management_android.utils.AppUtil;
import com.example.ware_house_management_android.view_models.OutputViewModel;
import com.example.ware_house_management_android.view_models.UserViewModel;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

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
            if (!userViewModel.hasLoadedToAssignInventoryStaffs())
                outputPresenter.getInventoryStaffList();
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
                    listAssignedInventoryStaffs.setAdapter(new UserAdapter(this.getContext(), item.getInventoryStaffIds()));

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
                            } else if (userRole.equals("Manager")) {
                                outputPresenter.approveOutput(item.get_id());
                            }
                        } else if (item.getStatus().equals("Approved")) {
                            dialog.dismiss();
                            ;

                            AppUtil.observeOnce(userViewModel.getToAssignInventoryStaffsList(), getViewLifecycleOwner(), inventoryStaffs -> {
                                if (inventoryStaffs != null) {
                                    showAssignStaffDialog(item.get_id(), inventoryStaffs);
                                } else {
                                    Toast.makeText(getContext(), "No inventory staffs found", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else if (item.getStatus().equals("Assigned")) {
                            if (userRole.equals("Inventory Staff")) {
                                outputPresenter.completeOutput(item.get_id());
                            } else {
                                Toast.makeText(getContext(), "Only Inventory Staff can complete the output", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "No action available for this status", Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
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
    public void showAssignStaffDialog(String outputId, ArrayList<UserModel> inventoryStaffs) {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_assign_inventory_staffs, null);
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(dialogView);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout((int) (getResources().getDisplayMetrics().widthPixels * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        RecyclerView recyclerView = dialogView.findViewById(R.id.recycler_view_inventory_staffs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AssignStaffAdapter adapter = new AssignStaffAdapter(getContext(), inventoryStaffs);
        recyclerView.setAdapter(adapter);

        TextView textFromDate = dialogView.findViewById(R.id.text_from_date);
        TextView textToDate = dialogView.findViewById(R.id.text_to_date);

        textFromDate.setOnClickListener(v -> showDatePickerDialog(textFromDate));
        textToDate.setOnClickListener(v -> showDatePickerDialog(textToDate));

        Button assignButton = dialogView.findViewById(R.id.button_assign);
        assignButton.setOnClickListener(v -> {
            String fromDate = textFromDate.getText().toString().trim();
            String toDate = textToDate.getText().toString().trim();

            ArrayList<UserModel> selectedStaffs = adapter.getSelectedStaffs();
            Log.i("OutputFragment", "Selected Staffs: " + selectedStaffs.size());

            ArrayList<String> inventoryStaffIds = new ArrayList<>();
            for (UserModel staff : selectedStaffs) {
                inventoryStaffIds.add(staff.getId());
            }

            try {
                Log.i("OutputFragment", "Inventory Staff IDs: " + inventoryStaffIds + ", From Date: " + fromDate + ", To Date: " + toDate + ", Input ID: " + outputId);
                AssignOutputDto assignOutputDto = new AssignOutputDto(inventoryStaffIds, AppUtil.convertToMongoDbIsoDate(fromDate), AppUtil.convertToMongoDbIsoDate(toDate));
                if (!assignOutputDto.isValid()) {
                    Toast.makeText(getContext(), assignOutputDto.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                outputPresenter.assignOutput(outputId, inventoryStaffIds, AppUtil.convertToMongoDbIsoDate(fromDate), AppUtil.convertToMongoDbIsoDate(toDate));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            dialog.dismiss();
        });

        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void showDatePickerDialog(TextView targetTextView) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view, year1, month1, dayOfMonth) -> {
            String date = String.format(Locale.getDefault(), "%04d-%02d-%02d", year1, month1 + 1, dayOfMonth);
            targetTextView.setText(date);
        }, year, month, day);

        datePickerDialog.show();
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

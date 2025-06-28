package com.example.ware_house_management_android.ui.input;


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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ware_house_management_android.R;
import com.example.ware_house_management_android.adapters.AssignStaffAdapter;
import com.example.ware_house_management_android.adapters.InputAdapter;
import com.example.ware_house_management_android.adapters.InputDetailsAdapter;
import com.example.ware_house_management_android.adapters.UserAdapter;
import com.example.ware_house_management_android.contracts.InputContract;
import com.example.ware_house_management_android.databinding.FragmentInputBinding;
import com.example.ware_house_management_android.dtos.input_details.UpdateInputDetailDto;
import com.example.ware_house_management_android.dtos.inputs.AssignInputDto;
import com.example.ware_house_management_android.models.UserModel;
import com.example.ware_house_management_android.presenters.InputPresenter;
import com.example.ware_house_management_android.utils.AppUtil;
import com.example.ware_house_management_android.view_models.input.InputViewModel;
import com.example.ware_house_management_android.view_models.user.UserViewModel;


import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class InputFragment extends Fragment implements InputContract.View {
    private FragmentInputBinding binding;
    private InputPresenter inputPresenter;
    private RecyclerView listInputs;
    private ProgressBar progressBar;
    private Dialog currentDialog;

    private ArrayList<UpdateInputDetailDto> updateInputDetails = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        InputViewModel listInputViewModel = new ViewModelProvider(this).get(InputViewModel.class);
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding = FragmentInputBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        progressBar = binding.progressBar;

        inputPresenter = new InputPresenter(this.getContext(), listInputViewModel, userViewModel, this);
        try {
            inputPresenter.getInputList();
            if (!userViewModel.hasLoadedToAssignInventoryStaffs())
                inputPresenter.getInventoryStaffList();

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

                    RecyclerView listAssignedInventoryStaffs = dialogInputDetails.findViewById(R.id.recycler_view_inventory_staffs);
                    listAssignedInventoryStaffs.setLayoutManager(new LinearLayoutManager(getContext()));
                    listAssignedInventoryStaffs.setAdapter(new UserAdapter(this.getContext(), item.getInventoryStaffIds()));

                    Dialog dialog = new Dialog(requireContext());
                    dialog.setContentView(dialogInputDetails);

                    Window window = dialog.getWindow();
                    if (window != null) {
                        window.setLayout((int) (getResources().getDisplayMetrics().widthPixels * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
                    }

                    dialog.setTitle("Input Details");

                    String userRole = AppUtil.currentUser(this.getContext()).getRole();
                    Button actionButton = dialogInputDetails.findViewById(R.id.button_action);
                    if (item.getStatus().equals("Pending")) {
                        if (userRole.equals("Report Staff"))
                            actionButton.setText("Update");
                        else
                            actionButton.setText("Approve");
                    } else if (item.getStatus().equals("Approved")) {
                        actionButton.setText("Assign Staffs");
                    } else {
                        actionButton.setText("View Details");
                    }

                    actionButton.setOnClickListener(v -> {
                        if (item.getStatus().equals("Pending")) {
                            if (userRole.equals("Report Staff")) {
                                if (updateInputDetails == null || updateInputDetails.isEmpty()) {
                                    Toast.makeText(getContext(), "No input details to update", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                for (UpdateInputDetailDto detail : updateInputDetails) {
                                    try {
                                        inputPresenter.updateInputDetails(item.getId(), detail);
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                }

                            } else if (userRole.equals("Manager")) {
                                inputPresenter.approveInput(item.getId());
                            }
                        } else if (item.getStatus().equals("Approved")) {
                            dialog.dismiss();

                            AppUtil.observeOnce(userViewModel.getToAssignInventoryStaffsList(), getViewLifecycleOwner(), inventoryStaffs -> {
                                ;
                                if (inventoryStaffs != null) {
                                    showAssignStaffDialog(item.getId(), inventoryStaffs);
                                } else {
                                    Toast.makeText(getContext(), "No inventory staffs found", Toast.LENGTH_SHORT).show();
                                }
                            });
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
                InputDetailsAdapter inputDetailsAdapter = new InputDetailsAdapter(getContext(), inputDetails);
                listInputDetails.setAdapter(inputDetailsAdapter);

                updateInputDetails = inputDetailsAdapter.getUpdateInputDetailsList();
            } else {
                Toast.makeText(getContext(), "No input details found", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    private void showAssignStaffDialog(String inputId, ArrayList<UserModel> inventoryStaffs) {
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
            Log.i("InputFragment", "Selected Staffs: " + selectedStaffs.size());

            ArrayList<String> inventoryStaffIds = new ArrayList<>();
            for (UserModel staff : selectedStaffs) {
                inventoryStaffIds.add(staff.getId());
            }

            try {
                Log.i("InputFragment", "Inventory Staff IDs: " + inventoryStaffIds + ", From Date: " + fromDate + ", To Date: " + toDate + ", Input ID: " + inputId);
                AssignInputDto assignInputDto = new AssignInputDto(inventoryStaffIds, AppUtil.convertToMongoDbIsoDate(fromDate), AppUtil.convertToMongoDbIsoDate(toDate));
                if (!assignInputDto.isValid()) {
                    Toast.makeText(getContext(), assignInputDto.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                inputPresenter.assignInput(inputId, inventoryStaffIds, AppUtil.convertToMongoDbIsoDate(fromDate), AppUtil.convertToMongoDbIsoDate(toDate));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            dialog.dismiss();
        });

        dialog.setCancelable(true);
        dialog.show();
    }

    private void showDatePickerDialog(TextView targetTextView) {
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

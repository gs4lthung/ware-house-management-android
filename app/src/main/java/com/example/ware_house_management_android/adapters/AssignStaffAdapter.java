package com.example.ware_house_management_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ware_house_management_android.R;
import com.example.ware_house_management_android.models.UserModel;

import java.util.ArrayList;

public class AssignStaffAdapter extends RecyclerView.Adapter<AssignStaffAdapter.ViewHolder> {
    private Context context;
    private ArrayList<UserModel> inventoryStaffList;
    private ArrayList<UserModel> selectedStaffs = new ArrayList<>();

    public AssignStaffAdapter(Context context, ArrayList<UserModel> inventoryStaffList) {
        this.context = context;
        this.inventoryStaffList = inventoryStaffList;
    }

    public ArrayList<UserModel> getSelectedStaffs() {
        return selectedStaffs;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView fullName;
        CheckBox checkBox;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.fullName);
            checkBox = itemView.findViewById(R.id.checkbox_select);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_assign_staff, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModel inventoryStaff = inventoryStaffList.get(position);
        holder.fullName.setText(inventoryStaff.getFullName());

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(selectedStaffs.contains(inventoryStaff));

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!selectedStaffs.contains(inventoryStaff)) {
                    selectedStaffs.add(inventoryStaff);
                }
            } else {
                selectedStaffs.remove(inventoryStaff);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inventoryStaffList.size();
    }
}

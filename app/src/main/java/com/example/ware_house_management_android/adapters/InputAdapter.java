package com.example.ware_house_management_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ware_house_management_android.R;
import com.example.ware_house_management_android.models.InputModel;


import java.util.ArrayList;

public class InputAdapter extends RecyclerView.Adapter<InputAdapter.ViewHolder> {
    Context context;
    ArrayList<InputModel> inputList;

    public InputAdapter(Context context, ArrayList<InputModel> inputList) {
        this.context = context;
        this.inputList = inputList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_input, parent, false);
        return new InputAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InputModel input = inputList.get(position);
        holder.requiredBy.setText(input.getSupplierId().getFullName());
        holder.writtenBy.setText(input.getReportStaffId().getFullName());
        holder.description.setText(input.getDescription());
        holder.status.setText(input.getStatus());
        holder.createdAt.setText(input.getCreatedAt().toString());
        holder.updatedAt.setText(input.getUpdatedAt().toString());

    }

    @Override
    public int getItemCount() {
        return inputList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView requiredBy, writtenBy, description, status, createdAt, updatedAt;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            requiredBy = itemView.findViewById(R.id.requiredBy);
            writtenBy = itemView.findViewById(R.id.writtenBy);
            description = itemView.findViewById(R.id.description);
            status = itemView.findViewById(R.id.status);
            createdAt = itemView.findViewById(R.id.createdAt);
            updatedAt = itemView.findViewById(R.id.updatedAt);
        }

    }
}

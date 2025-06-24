package com.example.ware_house_management_android.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ware_house_management_android.R;
import com.example.ware_house_management_android.interfaces.OnItemClickListener;
import com.example.ware_house_management_android.models.InputModel;
import com.example.ware_house_management_android.utils.AppUtil;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


import org.json.JSONException;

import java.text.DateFormat;
import java.util.ArrayList;

public class InputAdapter extends RecyclerView.Adapter<InputAdapter.ViewHolder> {
    Context context;
    ArrayList<InputModel> inputList;

    OnItemClickListener<InputModel> onItemClickListener;

    public InputAdapter(Context context, ArrayList<InputModel> inputList, OnItemClickListener<InputModel> onItemClickListener) {
        this.context = context;
        this.inputList = inputList;
        this.onItemClickListener = onItemClickListener;
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
        holder.createdAt.setText(AppUtil.dateToLocaleString(input.getCreatedAt()));
        holder.updatedAt.setText(AppUtil.dateToLocaleString(input.getUpdatedAt()));
        holder.detailButton.setOnClickListener(view ->
        {
            try {
                onItemClickListener.onItemClick(input);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inputList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView requiredBy, writtenBy, description, status, createdAt, updatedAt;
        Button detailButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            requiredBy = itemView.findViewById(R.id.requiredBy);
            writtenBy = itemView.findViewById(R.id.writtenBy);
            description = itemView.findViewById(R.id.description);
            status = itemView.findViewById(R.id.status);
            createdAt = itemView.findViewById(R.id.createdAt);
            updatedAt = itemView.findViewById(R.id.updatedAt);
            detailButton = itemView.findViewById(R.id.detailButton);
        }
    }
}

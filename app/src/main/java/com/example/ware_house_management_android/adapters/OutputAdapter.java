package com.example.ware_house_management_android.adapters;

import android.content.Context;
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
import com.example.ware_house_management_android.models.OutputModel;
import com.example.ware_house_management_android.utils.AppUtil;

import org.json.JSONException;

import java.util.ArrayList;

public class OutputAdapter extends RecyclerView.Adapter<OutputAdapter.ViewHolder> {
    Context context;
    ArrayList<OutputModel> outputList;
    OnItemClickListener<OutputModel> onItemClickListener;

    public OutputAdapter(Context context, ArrayList<OutputModel> outputList, OnItemClickListener<OutputModel> onItemClickListener) {
        this.context = context;
        this.outputList = outputList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_output, parent, false);
        return new OutputAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OutputAdapter.ViewHolder holder, int position) {
        OutputModel output = outputList.get(position);
        if (output.getCustomerId() != null)
            holder.requiredBy.setText(output.getCustomerId().getFullName());
        if (output.getReportStaffId() != null)
            holder.writtenBy.setText(output.getReportStaffId().getFullName());
        holder.description.setText(output.getDescription());
        holder.status.setText(output.getStatus());
        holder.fromDate.setText(AppUtil.dateToLocaleString(output.getFromDate()));
        holder.toDate.setText(AppUtil.dateToLocaleString(output.getToDate()));
        holder.createdAt.setText(AppUtil.dateToLocaleString(output.getCreatedAt()));
        holder.updatedAt.setText(AppUtil.dateToLocaleString(output.getUpdatedAt()));
        holder.detailButton.setOnClickListener(view ->
        {
            try {
                onItemClickListener.onItemClick(output);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public int getItemCount() {
        return outputList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView requiredBy, writtenBy, description, status, fromDate, toDate, createdAt, updatedAt;
        Button detailButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            requiredBy = itemView.findViewById(R.id.requiredBy);
            writtenBy = itemView.findViewById(R.id.writtenBy);
            description = itemView.findViewById(R.id.description);
            status = itemView.findViewById(R.id.status);
            fromDate = itemView.findViewById(R.id.fromDate);
            toDate = itemView.findViewById(R.id.toDate);
            createdAt = itemView.findViewById(R.id.createdAt);
            updatedAt = itemView.findViewById(R.id.updatedAt);
            detailButton = itemView.findViewById(R.id.detailButton);
        }
    }
}

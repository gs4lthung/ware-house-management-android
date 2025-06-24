package com.example.ware_house_management_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ware_house_management_android.R;
import com.example.ware_house_management_android.models.InputDetailsModel;
import com.example.ware_house_management_android.utils.AppUtil;

import java.util.ArrayList;

public class InputDetailsAdapter extends RecyclerView.Adapter<InputDetailsAdapter.ViewHolder> {
    Context context;
    ArrayList<InputDetailsModel> inputDetailsList;

    public InputDetailsAdapter(Context context, ArrayList<InputDetailsModel> inputDetailsList) {
        this.context = context;
        this.inputDetailsList = inputDetailsList;
    }


    @NonNull
    @Override
    public InputDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_input_details, parent, false);
        return new InputDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InputDetailsAdapter.ViewHolder holder, int position) {
        InputDetailsModel inputDetails = inputDetailsList.get(position);

        holder.name.setText(inputDetails.getItemId().getBaseItemId().getName() == null ? "N/A" : inputDetails.getItemId().getBaseItemId().getName());
        holder.requestQuantity.setText(String.valueOf(inputDetails.getRequestQuantity()));
        holder.actualQuantity.setText(String.valueOf(inputDetails.getActualQuantity()));
        holder.inputPrice.setText(String.valueOf(inputDetails.getInputPrice() == null ? "N/A" : inputDetails.getInputPrice()));
        holder.suggestedOutputPrice.setText(String.valueOf(inputDetails.getSuggestedOutputPrice() == null ? "N/A" : inputDetails.getSuggestedOutputPrice()));
        holder.status.setText(inputDetails.getStatus());
        if (inputDetails.getUpdatedBy() != null && inputDetails.getUpdatedBy().getFullName() != null)
            holder.updatedBy.setText(inputDetails.getUpdatedBy().getFullName().isEmpty() ? "N/A" : inputDetails.getUpdatedBy().getFullName());
        holder.createdAt.setText(AppUtil.dateToLocaleString(inputDetails.getCreatedAt()));
        holder.updatedAt.setText(AppUtil.dateToLocaleString(inputDetails.getUpdatedAt()));

    }

    @Override
    public int getItemCount() {
        return inputDetailsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, requestQuantity,
                actualQuantity,
                inputPrice,
                suggestedOutputPrice,
                status,
                updatedBy,
                createdAt,
                updatedAt;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            requestQuantity = itemView.findViewById(R.id.requestQuantity);
            actualQuantity = itemView.findViewById(R.id.actualQuantity);
            inputPrice = itemView.findViewById(R.id.inputPrice);
            suggestedOutputPrice = itemView.findViewById(R.id.suggestedOutputPrice);
            status = itemView.findViewById(R.id.status);
            updatedBy = itemView.findViewById(R.id.updatedBy);
            createdAt = itemView.findViewById(R.id.createdAt);
            updatedAt = itemView.findViewById(R.id.updatedAt);
        }
    }
}

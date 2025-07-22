package com.example.ware_house_management_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ware_house_management_android.R;
import com.example.ware_house_management_android.models.WarehouseStorageModel;

import java.util.ArrayList;

public class WarehouseStorageAdapter extends RecyclerView.Adapter<WarehouseStorageAdapter.ViewHolder> {
    Context context;
    ArrayList<WarehouseStorageModel> warehouseStorageModelList;

    public WarehouseStorageAdapter(Context context, ArrayList<WarehouseStorageModel> warehouseStorageModelList) {
        this.context = context;
        this.warehouseStorageModelList = warehouseStorageModelList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, batchNumber, quantity, warehouse;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            batchNumber = itemView.findViewById(R.id.batchNumber);
            quantity = itemView.findViewById(R.id.quantity);
            warehouse = itemView.findViewById(R.id.warehouse);
        }
    }

    @NonNull
    @Override
    public WarehouseStorageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_warehouse_storage, parent, false);
        return new WarehouseStorageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WarehouseStorageAdapter.ViewHolder holder, int position) {
        WarehouseStorageModel warehouseStorageModel = warehouseStorageModelList.get(position);
        holder.name.setText(warehouseStorageModel.getItemId().getBaseItemId().getName());
        holder.batchNumber.setText(warehouseStorageModel.getBatchNumber());
        holder.quantity.setText(String.valueOf(warehouseStorageModel.getQuantity()));
        holder.warehouse.setText(warehouseStorageModel.getWarehouseId().getName());
    }

    @Override
    public int getItemCount() {
        return warehouseStorageModelList.size();
    }
}

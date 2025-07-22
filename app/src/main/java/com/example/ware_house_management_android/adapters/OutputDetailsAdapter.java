package com.example.ware_house_management_android.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ware_house_management_android.R;
import com.example.ware_house_management_android.dtos.output_details.UpdateOutputDetailDto;
import com.example.ware_house_management_android.models.OutputDetailsModel;
import com.example.ware_house_management_android.utils.AppUtil;

import java.util.ArrayList;

public class OutputDetailsAdapter extends RecyclerView.Adapter<OutputDetailsAdapter.ViewHolder> {
    Context context;
    protected String userRole;
    ArrayList<OutputDetailsModel> outputDetailsList;
    ArrayList<UpdateOutputDetailDto> updateOutputDetailsList = new ArrayList<>();

    public OutputDetailsAdapter(Context context, ArrayList<OutputDetailsModel> outputDetailsList) {
        this.context = context;
        this.outputDetailsList = outputDetailsList;
        userRole = AppUtil.currentUser(context).getRole();
    }

    @NonNull
    @Override
    public OutputDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_output_details, parent, false);
        return new OutputDetailsAdapter.ViewHolder(view, userRole);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OutputDetailsModel outputDetails = outputDetailsList.get(position);
        holder.name.setText(outputDetails.getItemId().getBaseItemId().getName());
        holder.quantity.setText(String.valueOf(outputDetails.getQuantity()));
        holder.quantity.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(android.text.Editable s) {
                updateOutputDetailsList.removeIf(item -> item.getId() == outputDetails.get_id());

                int quantity = s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString());

                if (quantity > 0) {
                    updateOutputDetailsList.add(new UpdateOutputDetailDto(
                            outputDetails.get_id(),
                            quantity,
                            outputDetails.getOutputPrice(
                            ),
                            outputDetails.getStatus()
                    ));
                }
            }
        });
        holder.outputPrice.setText(String.valueOf(outputDetails.getOutputPrice()));
        holder.outputPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateOutputDetailsList.removeIf(item -> item.getId() == outputDetails.get_id());

                Double outputPrice = s.toString().isEmpty() ? 0 : Double.parseDouble(s.toString());
                if (outputPrice > 0) {
                    updateOutputDetailsList.add(new UpdateOutputDetailDto(
                            outputDetails.get_id(),
                            outputDetails.getQuantity(),
                            outputPrice,
                            outputDetails.getStatus()
                    ));
                }
            }
        });
        holder.status.setText(outputDetails.getStatus());
        holder.createdAt.setText(AppUtil.dateToLocaleString(outputDetails.getCreatedAt()));
        holder.updatedAt.setText(AppUtil.dateToLocaleString(outputDetails.getUpdatedAt()));

    }

    public ArrayList<UpdateOutputDetailDto> getUpdateOutputDetailsList() {
        return updateOutputDetailsList;
    }

    @Override
    public int getItemCount() {
        return outputDetailsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, status, createdAt, updatedAt;
        EditText quantity, outputPrice;

        ViewHolder(@NonNull View itemView, String userRole) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            quantity = itemView.findViewById(R.id.quantity);
            outputPrice = itemView.findViewById(R.id.outputPrice);
            status = itemView.findViewById(R.id.status);
            createdAt = itemView.findViewById(R.id.createdAt);
            updatedAt = itemView.findViewById(R.id.updatedAt);

            switch (userRole) {
                case "Report Staff":
                    quantity.setFocusable(true);
                    quantity.setFocusableInTouchMode(true);
                    outputPrice.setFocusable(true);
                    outputPrice.setFocusableInTouchMode(true);
                    break;
                default:
                    quantity.setFocusable(false);
                    quantity.setFocusableInTouchMode(false);
                    outputPrice.setFocusable(false);
                    outputPrice.setFocusableInTouchMode(false);
                    break;
            }
        }
    }
}

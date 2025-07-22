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
            public void afterTextChanged(Editable s) {
                try {
                    // Parse input or use existing value if empty
                    int quantity = s.toString().isEmpty() ? outputDetails.getQuantity() : Integer.parseInt(s.toString());

                    UpdateOutputDetailDto existingDto = findUpdateOutputDetailDto(outputDetails.get_id());
                    if (existingDto != null) {
                        existingDto.setQuantity(quantity);
                    } else if (quantity > 0) {
                        updateOutputDetailsList.add(new UpdateOutputDetailDto(
                                outputDetails.get_id(),
                                quantity,
                                outputDetails.getOutputPrice() != null ? outputDetails.getOutputPrice() : 0.0,
                                outputDetails.getStatus()
                        ));
                    }
                } catch (NumberFormatException e) {
                    holder.quantity.setText(String.valueOf(outputDetails.getQuantity()));
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
                try {
                    double outputPrice = s.toString().isEmpty() ? (outputDetails.getOutputPrice() != null ? outputDetails.getOutputPrice() : 0.0) : Double.parseDouble(s.toString());

                    UpdateOutputDetailDto existingDto = findUpdateOutputDetailDto(outputDetails.get_id());
                    if (existingDto != null) {
                        existingDto.setOutputPrice(outputPrice);
                    } else if (outputPrice > 0) {
                        updateOutputDetailsList.add(new UpdateOutputDetailDto(
                                outputDetails.get_id(),
                                outputDetails.getQuantity(),
                                outputPrice,
                                outputDetails.getStatus()
                        ));
                    }
                } catch (NumberFormatException e) {
                    // Reset to original value
                    holder.outputPrice.setText(outputDetails.getOutputPrice() != null ? String.valueOf(outputDetails.getOutputPrice()) : "0.0");
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

    private UpdateOutputDetailDto findUpdateOutputDetailDto(String id) {
        for (UpdateOutputDetailDto dto : updateOutputDetailsList) {
            if (dto.getId().equals(id)) {
                return dto;
            }
        }
        return null;
    }
}

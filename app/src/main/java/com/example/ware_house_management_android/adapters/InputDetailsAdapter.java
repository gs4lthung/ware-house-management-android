package com.example.ware_house_management_android.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ware_house_management_android.R;
import com.example.ware_house_management_android.dtos.input_details.UpdateInputDetailDto;
import com.example.ware_house_management_android.models.InputDetailsModel;
import com.example.ware_house_management_android.utils.AppUtil;

import java.util.ArrayList;

public class InputDetailsAdapter extends RecyclerView.Adapter<InputDetailsAdapter.ViewHolder> {
    Context context;

    protected String userRole;
    ArrayList<InputDetailsModel> inputDetailsList;

    ArrayList<UpdateInputDetailDto> updateInputDetailsList = new ArrayList<>();

    public InputDetailsAdapter(Context context, ArrayList<InputDetailsModel> inputDetailsList) {
        this.context = context;
        this.inputDetailsList = inputDetailsList;
        userRole = AppUtil.currentUser(context).getRole();
    }


    @NonNull
    @Override
    public InputDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_input_details, parent, false);
        return new InputDetailsAdapter.ViewHolder(view, userRole);
    }

    @Override
    public void onBindViewHolder(@NonNull InputDetailsAdapter.ViewHolder holder, int position) {
        InputDetailsModel inputDetails = inputDetailsList.get(position);

        holder.name.setText(inputDetails.getItemId().getBaseItemId().getName() == null ? "N/A" : inputDetails.getItemId().getBaseItemId().getName());
        holder.requestQuantity.setText(String.valueOf(inputDetails.getRequestQuantity()));
        holder.actualQuantity.setText(String.valueOf(inputDetails.getActualQuantity()));
        holder.actualQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateInputDetailsList.removeIf(item -> item.getId() == inputDetails.getId());

                int actualQuantity = s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString());

                if (actualQuantity > 0) {
                    updateInputDetailsList.add(new UpdateInputDetailDto(inputDetails.getId(), inputDetails.getRequestQuantity(), actualQuantity, inputDetails.getInputPrice(), inputDetails.getStatus()));
                }
            }
        });

        if (inputDetails.getInputPrice() != null)
            holder.inputPrice.setText(String.valueOf(inputDetails.getInputPrice()));
        else holder.inputPrice.setText("0.0");
        holder.inputPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateInputDetailsList.removeIf(item -> item.getId() == inputDetails.getId());

                Double inputPrice = s.toString().isEmpty() ? 0 : Double.parseDouble(s.toString());

                if (inputPrice > 0) {
                    updateInputDetailsList.add(new UpdateInputDetailDto(inputDetails.getId(), inputDetails.getRequestQuantity(), inputDetails.getActualQuantity(), inputPrice, inputDetails.getStatus()));
                }
            }
        });
        holder.suggestedOutputPrice.setText(String.valueOf(inputDetails.getSuggestedOutputPrice() == null ? "N/A" : inputDetails.getSuggestedOutputPrice()));
        holder.status.setText(inputDetails.getStatus());
        holder.createdAt.setText(AppUtil.dateToLocaleString(inputDetails.getCreatedAt()));
        holder.updatedAt.setText(AppUtil.dateToLocaleString(inputDetails.getUpdatedAt()));

    }

    public ArrayList<UpdateInputDetailDto> getUpdateInputDetailsList() {
        return updateInputDetailsList;
    }

    @Override
    public int getItemCount() {
        return inputDetailsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, requestQuantity, inputPrice, suggestedOutputPrice, status, createdAt, updatedAt;

        EditText actualQuantity;

        ViewHolder(@NonNull View itemView, String userRole) {
            super(itemView);
            Log.i("InputDetailsAdapter", "ViewHolder created with userRole: " + userRole);
            name = itemView.findViewById(R.id.name);
            requestQuantity = itemView.findViewById(R.id.requestQuantity);
            actualQuantity = itemView.findViewById(R.id.actualQuantity);
            inputPrice = itemView.findViewById(R.id.inputPrice);
            suggestedOutputPrice = itemView.findViewById(R.id.suggestedOutputPrice);
            status = itemView.findViewById(R.id.status);
            createdAt = itemView.findViewById(R.id.createdAt);
            updatedAt = itemView.findViewById(R.id.updatedAt);

            switch (userRole) {
                case "Report Staff":
                    actualQuantity.setFocusable(true);
                    actualQuantity.setFocusableInTouchMode(true);
                    inputPrice.setFocusable(true);
                    inputPrice.setFocusableInTouchMode(true);
                    break;
                default:
                    actualQuantity.setFocusable(false);
                    actualQuantity.setFocusableInTouchMode(false);
                    inputPrice.setFocusable(false);
                    inputPrice.setFocusableInTouchMode(false);
                    break;
            }

        }
    }
}

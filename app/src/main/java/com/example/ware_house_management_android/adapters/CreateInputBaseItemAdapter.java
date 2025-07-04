package com.example.ware_house_management_android.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ware_house_management_android.R;
import com.example.ware_house_management_android.dtos.input_details.CreateInputDetailsDto;
import com.example.ware_house_management_android.models.BaseItemModel;

import java.util.ArrayList;

public class CreateInputBaseItemAdapter extends RecyclerView.Adapter<CreateInputBaseItemAdapter.ViewHolder> {
    Context context;
    ArrayList<BaseItemModel> baseItemList;
    ArrayList<CreateInputDetailsDto> inputDetails = new ArrayList<>();


    public CreateInputBaseItemAdapter(Context context, ArrayList<BaseItemModel> baseItemList) {
        this.context = context;
        this.baseItemList = baseItemList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, brand, countryOfOrigin, storageType;
        EditText quantity;

        Button submitButton;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            brand = itemView.findViewById(R.id.brand);
            countryOfOrigin = itemView.findViewById(R.id.countryOfOrigin);
            storageType = itemView.findViewById(R.id.storageType);
            quantity = itemView.findViewById(R.id.quantity);
            submitButton = itemView.findViewById(R.id.btn_submit);
        }
    }

    @NonNull
    @Override
    public CreateInputBaseItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_create_input, parent, false);
        return new CreateInputBaseItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreateInputBaseItemAdapter.ViewHolder holder, int position) {
        BaseItemModel baseItem = baseItemList.get(position);
        holder.name.setText(baseItem.getName());
        holder.brand.setText(baseItem.getBrand());
        holder.countryOfOrigin.setText(baseItem.getCountryOfOrigin());
        holder.storageType.setText(baseItem.getStorageType());
        holder.quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed before text change
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // No action needed during text change
            }

            @Override
            public void afterTextChanged(Editable s) {
                inputDetails.removeIf(item -> item.getBaseItemId().equals(baseItem.getId()));

                int quantity = s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString());

                if (quantity > 0) {
                    inputDetails.add(new CreateInputDetailsDto(baseItem.getId(), quantity));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return baseItemList.size();
    }

    public ArrayList<CreateInputDetailsDto> getInputDetails() {
        return inputDetails;
    }

    public void setBaseItemList(ArrayList<BaseItemModel> baseItemList) {
        this.baseItemList = baseItemList;
        notifyDataSetChanged();
    }
}

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
import com.example.ware_house_management_android.dtos.output_details.CreateOutputDetailsDto;
import com.example.ware_house_management_android.models.ItemModel;

import java.util.ArrayList;

public class CreateOutputItemAdapter extends RecyclerView.Adapter<CreateOutputItemAdapter.ViewHolder> {
    Context context;
    ArrayList<ItemModel> itemList;

    ArrayList<CreateOutputDetailsDto> outputDetailsList = new ArrayList<>();

    public CreateOutputItemAdapter(Context context, ArrayList<ItemModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_create_output, parent, false);
        return new CreateOutputItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel item = itemList.get(position);
        holder.name.setText(item.getBaseItemId().getName());
        holder.brand.setText(item.getBaseItemId().getBrand());
        holder.countryOfOrigin.setText(item.getBaseItemId().getCountryOfOrigin());
        holder.storageQuantity.setText(String.valueOf(item.getStorageQuantity()));
        holder.suggestedOutputPrice.setText(String.valueOf(item.getSuggestedOutputPrice()));
        holder.outputQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                outputDetailsList.removeIf(outputDetail -> outputDetail.getItemId().equals(item.get_id()));

                int outputQty = s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString());

                if (outputQty > 0) {
                    outputDetailsList.add(new CreateOutputDetailsDto(item.get_id(), outputQty, 0.0  // Default output price is set to 0.0, will be updated later
                    ));
                }

            }
        });

        holder.outputPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                outputDetailsList.removeIf(outputDetails -> outputDetails.getItemId().equals(item.get_id()));
                Double outputPrice = s.toString().isEmpty() ? 0.0 : Double.parseDouble(s.toString());

                if (outputPrice > 0) {
                    outputDetailsList.add(new CreateOutputDetailsDto(item.get_id(), Integer.parseInt(holder.outputQuantity.getText().toString()), outputPrice));
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, brand, countryOfOrigin, storageQuantity, suggestedOutputPrice;
        EditText outputQuantity, outputPrice;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            brand = itemView.findViewById(R.id.brand);
            countryOfOrigin = itemView.findViewById(R.id.countryOfOrigin);
            storageQuantity = itemView.findViewById(R.id.storageQuantity);
            suggestedOutputPrice = itemView.findViewById(R.id.suggestedOutputPrice);
            outputQuantity = itemView.findViewById(R.id.outputQuantity);
            outputPrice = itemView.findViewById(R.id.outputPrice);
        }
    }

    public void setItemList(ArrayList<ItemModel> itemList) {
        itemList.removeIf(itemModel -> {
            return itemModel.getStorageQuantity() == 0 || itemModel.getSuggestedOutputPrice() == 0.0 || itemModel.getBaseItemId() == null;
        });
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public ArrayList<CreateOutputDetailsDto> getOutputDetails() {
        Log.i("CreateOutputItemAdapter", "Getting output details: " + outputDetailsList.size() + " items");
        return outputDetailsList;
    }
}

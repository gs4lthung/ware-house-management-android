package com.example.ware_house_management_android.interfaces;

import org.json.JSONException;

public interface OnItemClickListener<T> {
    void onItemClick(T item) throws JSONException;
}

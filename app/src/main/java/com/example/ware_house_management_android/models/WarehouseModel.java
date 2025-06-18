package com.example.ware_house_management_android.models;

public class WarehouseModel {
    private String _id;
    private String name;
    private String description;
    private String category;

    public WarehouseModel(String _id, String name, String description, String category) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

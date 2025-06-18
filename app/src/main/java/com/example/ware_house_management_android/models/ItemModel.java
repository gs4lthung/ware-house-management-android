package com.example.ware_house_management_android.models;

public class ItemModel {
    private String _id;
    private BaseItemModel baseItemId;
    private String code;
    private String status;
    private String unit;

    public ItemModel(String _id, BaseItemModel baseItemId, String code, String status, String unit) {
        this._id = _id;
        this.baseItemId = baseItemId;
        this.code = code;
        this.status = status;
        this.unit = unit;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public BaseItemModel getBaseItemId() {
        return baseItemId;
    }

    public void setBaseItemId(BaseItemModel baseItemId) {
        this.baseItemId = baseItemId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

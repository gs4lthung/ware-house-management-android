package com.example.ware_house_management_android.models;

public class BaseItemModel {
    private String _id;
    private String name;
    private String description;
    private String category;
    private String brand;
    private String countryOfOrigin;
    private String indication;
    private String contraindication;
    private String sideEffect;
    private String storageType;

    public BaseItemModel(String id, String name, String description, String category, String brand,
                         String countryOfOrigin, String indication, String contraindication,
                         String sideEffect, String storageType) {
        this._id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.countryOfOrigin = countryOfOrigin;
        this.indication = indication;
        this.contraindication = contraindication;
        this.sideEffect = sideEffect;
        this.storageType = storageType;
    }

    public BaseItemModel(String id, String name, String description, String brand, String countryOfOrigin, String storageType) {
        this._id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.countryOfOrigin = countryOfOrigin;
        this.storageType = storageType;
    }

    public BaseItemModel(String id, String name, String brand, String countryOfOrigin, String storageType) {
        this._id = id;
        this.name = name;
        this.brand = brand;
        this.countryOfOrigin = countryOfOrigin;
        this.storageType = storageType;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getContraindication() {
        return contraindication;
    }

    public void setContraindication(String contraindication) {
        this.contraindication = contraindication;
    }

    public String getSideEffect() {
        return sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }
}

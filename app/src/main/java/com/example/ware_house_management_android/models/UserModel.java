package com.example.ware_house_management_android.models;

import com.example.ware_house_management_android.enums.UserRoleEnum;

public class UserModel {
    private String id;
    private String fullName;
    private String email;
    private UserRoleEnum role;

    public UserModel(String id, String fullName, String email, UserRoleEnum role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

package com.example.ware_house_management_android.dtos;

import com.example.ware_house_management_android.enums.UserRoleEnum;

public class JWTPayloadDecoded {
    private String id;
    private String role;
    private String email;
    private String fullName;

    public JWTPayloadDecoded(String id, String role, String email, String fullName) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

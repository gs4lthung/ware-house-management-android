package com.example.ware_house_management_android.dtos.auth;

import android.text.TextUtils;

public class RegisterRequestDto {
    private String fullName;
    private String email;
    private String password;

    public RegisterRequestDto(String email, String password, String fullName) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isValid() {
        return !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(fullName);
    }

    public String getErrorMessage() {
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(fullName)) {
            return "Email, password, and full name cannot be empty.";
        } else if (TextUtils.isEmpty(email)) {
            return "Email cannot be empty.";
        } else if (TextUtils.isEmpty(password)) {
            return "Password cannot be empty.";
        } else if (TextUtils.isEmpty(fullName)) {
            return "Full name cannot be empty.";
        }
        return null;
    }
}
package com.example.ware_house_management_android.dtos;


import android.text.TextUtils;
import android.util.Log;

public class LoginRequestDto {
    private String email;
    private String password;

    public LoginRequestDto(String email, String password) {
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

    public boolean isValid() {
        return !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password);
    }

    public String getErrorMessage() {
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            return "Email and password cannot be empty.";
        } else if (TextUtils.isEmpty(email)) {
            return "Email cannot be empty.";
        } else if (TextUtils.isEmpty(password)) {
            return "Password cannot be empty.";
        }
        return null;
    }
}

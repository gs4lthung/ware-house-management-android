package com.example.ware_house_management_android.dtos;

import com.example.ware_house_management_android.models.UserModel;

import java.util.ArrayList;

public class GetUsersResponseDto {
    private ArrayList<UserModel> users;

    private int page;
    private int totalPages;

    public ArrayList<UserModel> getUsers() {
        return users;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }
}

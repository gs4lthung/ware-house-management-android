package com.example.ware_house_management_android.dtos;

public class APIResponseDto<T> {
    private int code;
    private String message;
    private T metadata;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getMetadata() {
        return metadata;
    }
}

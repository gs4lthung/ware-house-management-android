package com.example.ware_house_management_android.enums;

public enum SharedPreferenceEnum {
    APP_PREFS("app_prefs"),
    KEY_ACCESS_TOKEN("access_token"),
    KEY_ACCESS_USER("user");

    private final String key;

    public String getKey() {
        return key;
    }

    SharedPreferenceEnum(String key) {
        this.key = key;
    }
}

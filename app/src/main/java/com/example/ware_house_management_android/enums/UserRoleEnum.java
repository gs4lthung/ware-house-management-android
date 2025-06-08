package com.example.ware_house_management_android.enums;

public enum UserRoleEnum {
    ADMIN("Admin"),
    MANAGER("Manager"),
    REPORT_STAFF("Report Staff"),
    INVENTORY_STAFF("Inventory Staff"),
    SUPPLIER("Supplier"),
    CUSTOMER("Customer");

    private final String roleName;

    UserRoleEnum(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}

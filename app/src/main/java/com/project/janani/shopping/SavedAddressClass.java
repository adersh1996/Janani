package com.project.janani.shopping;

public class SavedAddressClass {
    String userName, userAddress, userPhoneNumber;

    public SavedAddressClass(String userName, String userAddress, String userPhoneNumber) {
        this.userName = userName;
        this.userAddress = userAddress;
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }
}

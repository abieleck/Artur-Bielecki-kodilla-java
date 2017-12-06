package com.kodilla.good.patterns.challenges;

public final class User {

    private final String name;
    private final String address;
    private final String mobileNumber;
    private final String emailAddress;

    public User(String name, String address, String mobileNumber, String emailAddress) {
        this.name = name;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return name;
    }
}

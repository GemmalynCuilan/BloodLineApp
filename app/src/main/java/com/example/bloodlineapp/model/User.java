package com.example.bloodlineapp.model;

public class User {
    public String userId, username,age, address, mobileNumber, bloodGroups, password;

    public User(String userId, String username, String age, String address, String mobileNumber, String bloodGroups, String password) {
        this.userId = userId;
        this.username = username;
        this.age = age;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.bloodGroups = bloodGroups;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getBloodGroups() {
        return bloodGroups;
    }

    public void setBloodGroups(String bloodGroups) {
        this.bloodGroups = bloodGroups;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {

    }
}

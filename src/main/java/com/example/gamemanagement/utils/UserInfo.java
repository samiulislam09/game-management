package com.example.gamemanagement.utils;

public class UserInfo {
    private static UserInfo instance;
    private String userId;

    public UserInfo() {
    }
    public static UserInfo getInstance(){
        if(instance == null){
            instance = new UserInfo();
        }
        return instance;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}

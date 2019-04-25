package com.example.bloodbank.models;

public class User {
    public String User_id;
    public String User_name;
    public String User_latitude;
    public String User_longitude;

    public User(){
    }

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String User_id) {
        this.User_id = User_id;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String User_name) {
        this.User_name = User_name;
    }


    public String getUser_latitude() {
        return User_latitude;
    }

    public void setUser_latitude(String User_latitude) {
        this.User_latitude = User_latitude;
    }

    public String getUser_longitude() {
        return User_longitude;
    }

    public void setUser_longitude(String User_longitude) {
        this.User_longitude = User_longitude;
    }

}

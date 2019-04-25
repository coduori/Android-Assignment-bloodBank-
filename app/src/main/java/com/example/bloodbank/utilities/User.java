package com.example.bloodbank.utilities;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("$oid")
    private String _id;
    private String first_name;
    private String other_names;
    private String username;
    private String phone_number;
    private String email;
    private String county;
    private String password;
    private String auth_token;

    public User(String _id, String first_name, String other_names, String username, String phone_number, String county, String email, String auth_token) {
        this._id = _id;
        this.first_name = first_name;
        this.other_names = other_names;
        this.username = username;
        this.phone_number = phone_number;
        this.county = county;
        this.email = email;
        this.auth_token = auth_token;
    }

    public User() {

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getOther_names() {
        return other_names;
    }

    public void setOther_names(String other_names) {
        this.other_names = other_names;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuth_token() {
        return auth_token;
    }

    public void setAuth_token(String auth_token) {
        this.auth_token = auth_token;
    }
}

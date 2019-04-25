package com.example.bloodbank.models;

public class Hospital {
    public String hospital_id;
    public String hospital_name;
    public String hospital_latitude;
    public String hospital_longitude;

    public Hospital(){
    }

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }


    public String getHospital_latitude() {
        return hospital_latitude;
    }

    public void setHospital_latitude(String hospital_latitude) {
        this.hospital_latitude = hospital_latitude;
    }

    public String getHospital_longitude() {
        return hospital_longitude;
    }

    public void setHospital_longitude(String hospital_longitude) {
        this.hospital_longitude = hospital_longitude;
    }

}

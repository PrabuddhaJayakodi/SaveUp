package com.myproject.myapplication3.ui.main;

public class Case {
    private String User_ID;
    private String Case_upload_date;
    private String Case_upload_time;
    private String Vehicle_Number;
    private String Case_Details;

    public Case() {
    }

    public Case(String user_ID, String case_upload_date, String case_upload_time, String vehicle_Number, String case_Details) {
        User_ID = user_ID;
        Case_upload_date = case_upload_date;
        Case_upload_time = case_upload_time;
        Vehicle_Number = vehicle_Number;
        Case_Details = case_Details;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getCase_upload_date() {
        return Case_upload_date;
    }

    public void setCase_upload_date(String case_upload_date) {
        Case_upload_date = case_upload_date;
    }

    public String getCase_upload_time() {
        return Case_upload_time;
    }

    public void setCase_upload_time(String case_upload_time) {
        Case_upload_time = case_upload_time;
    }

    public String getVehicle_Number() {
        return Vehicle_Number;
    }

    public void setVehicle_Number(String vehicle_Number) {
        Vehicle_Number = vehicle_Number;
    }

    public String getCase_Details() {
        return Case_Details;
    }

    public void setCase_Details(String case_Details) {
        Case_Details = case_Details;
    }
}

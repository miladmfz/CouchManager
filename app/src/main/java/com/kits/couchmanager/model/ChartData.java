package com.kits.couchmanager.model;

import com.google.gson.annotations.SerializedName;


public class ChartData {


    @SerializedName("WeightCode") private String WeightCode;
    @SerializedName("PersonRef") private String PersonRef;
    @SerializedName("WeightValue") private String WeightValue;
    @SerializedName("BMI") private String BMI;
    @SerializedName("Date") private String Date;
    @SerializedName("Duration") private String Duration;
    @SerializedName("Weeks") private String Weeks;


    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getWeeks() {
        return Weeks;
    }

    public void setWeeks(String weeks) {
        Weeks = weeks;
    }

    public String getWeightCode() {
        return WeightCode;
    }

    public void setWeightCode(String weightCode) {
        WeightCode = weightCode;
    }

    public String getPersonRef() {
        return PersonRef;
    }

    public void setPersonRef(String personRef) {
        PersonRef = personRef;
    }

    public String getWeightValue() {
        return WeightValue;
    }

    public void setWeightValue(String weightValue) {
        WeightValue = weightValue;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}

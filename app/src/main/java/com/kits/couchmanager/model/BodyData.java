package com.kits.couchmanager.model;

import com.google.gson.annotations.SerializedName;

public class BodyData {

     @SerializedName("BodyDataCode")  String BodyDataCode;
     @SerializedName("PersonRef")  String PersonRef;
     @SerializedName("FatPercentage")  String FatPercentage;
     @SerializedName("Abs")  String Abs;
     @SerializedName("Butt")  String Butt;
     @SerializedName("Chest")  String Chest;
     @SerializedName("ForeArm")  String ForeArm;
     @SerializedName("Leg")  String Leg;
     @SerializedName("Wrist")  String Wrist;
     @SerializedName("Ankle")  String Ankle;
     @SerializedName("Date")  String Date;
     @SerializedName("Duration")  String Duration;
     @SerializedName("Weeks")  String Weeks;


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

    public String getBodyDataCode() {
        return BodyDataCode;
    }

    public void setBodyDataCode(String bodyDataCode) {
        BodyDataCode = bodyDataCode;
    }

    public String getPersonRef() {
        return PersonRef;
    }

    public void setPersonRef(String personRef) {
        PersonRef = personRef;
    }

    public String getFatPercentage() {
        return FatPercentage;
    }

    public void setFatPercentage(String fatPercentage) {
        FatPercentage = fatPercentage;
    }

    public String getAbs() {
        return Abs;
    }

    public void setAbs(String abs) {
        Abs = abs;
    }

    public String getButt() {
        return Butt;
    }

    public void setButt(String butt) {
        Butt = butt;
    }

    public String getChest() {
        return Chest;
    }

    public void setChest(String chest) {
        Chest = chest;
    }

    public String getForeArm() {
        return ForeArm;
    }

    public void setForeArm(String foreArm) {
        ForeArm = foreArm;
    }

    public String getLeg() {
        return Leg;
    }

    public void setLeg(String leg) {
        Leg = leg;
    }

    public String getWrist() {
        return Wrist;
    }

    public void setWrist(String wrist) {
        Wrist = wrist;
    }

    public String getAnkle() {
        return Ankle;
    }

    public void setAnkle(String ankle) {
        Ankle = ankle;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}

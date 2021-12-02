package com.kits.couchmanager.model;

import com.google.gson.annotations.SerializedName;

public class TimeSheet {


    @SerializedName("TimeSheetCode")    private String TimeSheetCode;
    @SerializedName("PlanRef")          private String PlanRef;
    @SerializedName("TimeSheetDate")    private String TimeSheetDate;
    @SerializedName("DailyTime")        private String DailyTime;
    @SerializedName("State")            private String State;
    @SerializedName("Freeze")           private String Freeze;
    @SerializedName("Delay")            private String Delay;
    @SerializedName("WorkOutQuality")   private String WorkOutQuality;
    @SerializedName("WarmQuality")      private String WarmQuality;


    public String getTimeSheetCode() {
        return TimeSheetCode;
    }

    public void setTimeSheetCode(String timeSheetCode) {
        TimeSheetCode = timeSheetCode;
    }

    public String getPlanRef() {
        return PlanRef;
    }

    public void setPlanRef(String planRef) {
        PlanRef = planRef;
    }

    public String getTimeSheetDate() {
        return TimeSheetDate;
    }

    public void setTimeSheetDate(String timeSheetDate) {
        TimeSheetDate = timeSheetDate;
    }

    public String getDailyTime() {
        return DailyTime;
    }

    public void setDailyTime(String dailyTime) {
        DailyTime = dailyTime;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getFreeze() {
        return Freeze;
    }

    public void setFreeze(String freeze) {
        Freeze = freeze;
    }

    public String getDelay() {
        return Delay;
    }

    public void setDelay(String delay) {
        Delay = delay;
    }

    public String getWorkOutQuality() {
        return WorkOutQuality;
    }

    public void setWorkOutQuality(String workOutQuality) {
        WorkOutQuality = workOutQuality;
    }

    public String getWarmQuality() {
        return WarmQuality;
    }

    public void setWarmQuality(String warmQuality) {
        WarmQuality = warmQuality;
    }
}

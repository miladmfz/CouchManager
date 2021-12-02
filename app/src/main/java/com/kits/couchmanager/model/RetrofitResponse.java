package com.kits.couchmanager.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RetrofitResponse {



    @SerializedName("BodyDatas")
    private ArrayList<BodyData> BodyDatas;

    @SerializedName("BodyData")
    private BodyData BodyData;

    @SerializedName("TimeSheets")
    private ArrayList<TimeSheet> TimeSheets;

    @SerializedName("TimeSheet")
    private TimeSheet TimeSheet;


    @SerializedName("ChartDatas")
    private ArrayList<ChartData> chartDatas;

    @SerializedName("ChartData")
    private ChartData ChartData;


    @SerializedName("Plan")
    private Plan plan;

    @SerializedName("Plans")
    private ArrayList<Plan> plans;

    @SerializedName("Items")
    private ArrayList<Item> items;

    @SerializedName("Item")
    private Item item;

    @SerializedName("Persons")
    private ArrayList<Person> persons;

    @SerializedName("Person")
    private Person person;

    @SerializedName("ErrCode")
    private String ErrCode;

    @SerializedName("ErrMessage")
    private String ErrMessage;

    @SerializedName("message")
    private String message;

    @SerializedName("Text")
    private String Text;

    @SerializedName("path")
    private String path;


    public ArrayList<BodyData> getBodyDatas() {
        return BodyDatas;
    }

    public void setBodyDatas(ArrayList<BodyData> bodyDatas) {
        BodyDatas = bodyDatas;
    }

    public com.kits.couchmanager.model.BodyData getBodyData() {
        return BodyData;
    }

    public void setBodyData(BodyData bodyData) {
        BodyData = bodyData;
    }

    public ArrayList<ChartData> getChartDatas() {
        return chartDatas;
    }

    public void setChartDatas(ArrayList<ChartData> chartDatas) {
        this.chartDatas = chartDatas;
    }

    public ChartData getChartData() {
        return ChartData;
    }

    public void setChartData(ChartData chartData) {
        ChartData = chartData;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Plan> getPlans() {
        return plans;
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getErrCode() {
        return ErrCode;
    }

    public void setErrCode(String errCode) {
        ErrCode = errCode;
    }

    public String getErrMessage() {
        return ErrMessage;
    }

    public void setErrMessage(String errMessage) {
        ErrMessage = errMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<TimeSheet> getTimeSheets() {
        return TimeSheets;
    }

    public void setTimeSheets(ArrayList<TimeSheet> timeSheets) {
        TimeSheets = timeSheets;
    }

    public TimeSheet getTimeSheet() {
        return TimeSheet;
    }

    public void setTimeSheet(TimeSheet timeSheet) {
        TimeSheet = timeSheet;
    }
}

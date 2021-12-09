package com.kits.couchmanager.model;

import com.google.gson.annotations.SerializedName;

public class Plan {


    @SerializedName("PlanCode")    private String PlanCode;
    @SerializedName("PlanType")    private String PlanType;
    @SerializedName("PersonRef")    private String PersonRef;




    @SerializedName("StartDate")    private String StartDate;
    @SerializedName("EndDate")    private String EndDate;
    @SerializedName("WeekPeriod")    private String WeekPeriod;
    @SerializedName("DayPeriod")    private String DayPeriod;
    @SerializedName("TargetPlan")    private String TargetPlan;
    @SerializedName("Active")    private String Active;


    @SerializedName("PlanRef")    private String PlanRef;
    @SerializedName("PlanRowCode")    private String PlanRowCode;
    @SerializedName("ItemRef")    private String ItemRef;
    @SerializedName("Repeat")    private String Repeat;
    @SerializedName("Duration")    private String Duration;
    @SerializedName("DayInWeek")    private String DayInWeek;
    @SerializedName("PercentPower")    private String PercentPower;
    @SerializedName("Explain")    private String Explain;


    @SerializedName("ItemCode")    private String ItemCode;
    @SerializedName("ItemTypeCode")    private String ItemTypeCode;
    @SerializedName("ItemName")    private String ItemName;
    @SerializedName("ItemType1")    private String ItemType1;
    @SerializedName("ItemType2")    private String ItemType2;
    @SerializedName("ItemType3")    private String ItemType3;
    @SerializedName("ItemType4")    private String ItemType4;
    @SerializedName("ItemType5")    private String ItemType5;
    @SerializedName("ItemExplain1")    private String ItemExplain1;
    @SerializedName("ItemExplain2")    private String ItemExplain2;
    @SerializedName("ItemExplain3")    private String ItemExplain3;
    @SerializedName("ItemExplain4")    private String ItemExplain4;
    @SerializedName("ItemExplain5")    private String ItemExplain5;
    @SerializedName("ItemExplain6")    private String ItemExplain6;



    @SerializedName("personcode")   private String PersonCode;
    @SerializedName("FirstName")    private String FirstName;
    @SerializedName("LastName")     private String LastName;
    @SerializedName("MobileNumber") private String MobileNumber;
    @SerializedName("Email")        private String Email;
    @SerializedName("Address")      private String Address;
    @SerializedName("Weight")       private String LastWeight;
    @SerializedName("FirstWeight")  private String FirstWeight;
    @SerializedName("Height")       private String Height;
    @SerializedName("BloodType")    private String BloodType;
    @SerializedName("Age")          private String Age;
    @SerializedName("BirthDate")    private String BirthDate;
    @SerializedName("KodeMelli")    private String KodeMelli;
    @SerializedName("Introduction") private String Introduction;
    @SerializedName("workplace")    private String workplace;
    @SerializedName("SportsGoal")   private String SportsGoal;
    @SerializedName("HistoryWorkOut")       private String HistoryWorkOut;
    @SerializedName("HistoryComplement")    private String HistoryComplement;
    @SerializedName("HistoryEnergiDrugs")   private String HistoryEnergiDrugs;
    @SerializedName("HistoryHealthDrugs")   private String HistoryHealthDrugs;
    @SerializedName("HistoryLimitations")   private String HistoryLimitations;
    @SerializedName("HistoryFoodAllergies") private String HistoryFoodAllergies;
    @SerializedName("Image") private String Image;






    public String getTargetPlan() {
        return TargetPlan;
    }

    public void setTargetPlan(String targetPlan) {
        TargetPlan = targetPlan;
    }

    public String getPlanCode() {
        return PlanCode;
    }

    public void setPlanCode(String planCode) {
        PlanCode = planCode;
    }

    public String getPlanType() {
        return PlanType;
    }

    public void setPlanType(String planType) {
        PlanType = planType;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getWeekPeriod() {
        return WeekPeriod;
    }

    public void setWeekPeriod(String weekPeriod) {
        WeekPeriod = weekPeriod;
    }

    public String getDayPeriod() {
        return DayPeriod;
    }

    public void setDayPeriod(String dayPeriod) {
        DayPeriod = dayPeriod;
    }


    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public String getPlanRef() {
        return PlanRef;
    }

    public void setPlanRef(String planRef) {
        PlanRef = planRef;
    }

    public String getPlanRowCode() {
        return PlanRowCode;
    }

    public void setPlanRowCode(String planRowCode) {
        PlanRowCode = planRowCode;
    }

    public String getItemRef() {
        return ItemRef;
    }

    public void setItemRef(String itemRef) {
        ItemRef = itemRef;
    }

    public String getRepeat() {
        return Repeat;
    }

    public void setRepeat(String repeat) {
        Repeat = repeat;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getDayInWeek() {
        return DayInWeek;
    }

    public void setDayInWeek(String dayInWeek) {
        DayInWeek = dayInWeek;
    }

    public String getPercentPower() {
        return PercentPower;
    }

    public void setPercentPower(String percentPower) {
        PercentPower = percentPower;
    }

    public String getExplain() {
        return Explain;
    }

    public void setExplain(String explain) {
        Explain = explain;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemTypeCode() {
        return ItemTypeCode;
    }

    public void setItemTypeCode(String itemTypeCode) {
        ItemTypeCode = itemTypeCode;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemType1() {
        return ItemType1;
    }

    public void setItemType1(String itemType1) {
        ItemType1 = itemType1;
    }

    public String getItemType2() {
        return ItemType2;
    }

    public void setItemType2(String itemType2) {
        ItemType2 = itemType2;
    }

    public String getItemType3() {
        return ItemType3;
    }

    public void setItemType3(String itemType3) {
        ItemType3 = itemType3;
    }

    public String getItemType4() {
        return ItemType4;
    }

    public void setItemType4(String itemType4) {
        ItemType4 = itemType4;
    }

    public String getItemType5() {
        return ItemType5;
    }

    public void setItemType5(String itemType5) {
        ItemType5 = itemType5;
    }

    public String getItemExplain1() {
        return ItemExplain1;
    }

    public void setItemExplain1(String itemExplain1) {
        ItemExplain1 = itemExplain1;
    }

    public String getItemExplain2() {
        return ItemExplain2;
    }

    public void setItemExplain2(String itemExplain2) {
        ItemExplain2 = itemExplain2;
    }

    public String getItemExplain3() {
        return ItemExplain3;
    }

    public void setItemExplain3(String itemExplain3) {
        ItemExplain3 = itemExplain3;
    }

    public String getItemExplain4() {
        return ItemExplain4;
    }

    public void setItemExplain4(String itemExplain4) {
        ItemExplain4 = itemExplain4;
    }

    public String getItemExplain5() {
        return ItemExplain5;
    }

    public void setItemExplain5(String itemExplain5) {
        ItemExplain5 = itemExplain5;
    }

    public String getItemExplain6() {
        return ItemExplain6;
    }

    public void setItemExplain6(String itemExplain6) {
        ItemExplain6 = itemExplain6;
    }

    public String getPersonRef() {
        return PersonRef;
    }

    public void setPersonRef(String personRef) {
        PersonRef = personRef;
    }

    public String getPersonCode() {
        return PersonCode;
    }

    public void setPersonCode(String personCode) {
        PersonCode = personCode;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLastWeight() {
        return LastWeight;
    }

    public void setLastWeight(String lastWeight) {
        LastWeight = lastWeight;
    }

    public String getFirstWeight() {
        return FirstWeight;
    }

    public void setFirstWeight(String firstWeight) {
        FirstWeight = firstWeight;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getBloodType() {
        return BloodType;
    }

    public void setBloodType(String bloodType) {
        BloodType = bloodType;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getKodeMelli() {
        return KodeMelli;
    }

    public void setKodeMelli(String kodeMelli) {
        KodeMelli = kodeMelli;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getSportsGoal() {
        return SportsGoal;
    }

    public void setSportsGoal(String sportsGoal) {
        SportsGoal = sportsGoal;
    }

    public String getHistoryWorkOut() {
        return HistoryWorkOut;
    }

    public void setHistoryWorkOut(String historyWorkOut) {
        HistoryWorkOut = historyWorkOut;
    }

    public String getHistoryComplement() {
        return HistoryComplement;
    }

    public void setHistoryComplement(String historyComplement) {
        HistoryComplement = historyComplement;
    }

    public String getHistoryEnergiDrugs() {
        return HistoryEnergiDrugs;
    }

    public void setHistoryEnergiDrugs(String historyEnergiDrugs) {
        HistoryEnergiDrugs = historyEnergiDrugs;
    }

    public String getHistoryHealthDrugs() {
        return HistoryHealthDrugs;
    }

    public void setHistoryHealthDrugs(String historyHealthDrugs) {
        HistoryHealthDrugs = historyHealthDrugs;
    }

    public String getHistoryLimitations() {
        return HistoryLimitations;
    }

    public void setHistoryLimitations(String historyLimitations) {
        HistoryLimitations = historyLimitations;
    }

    public String getHistoryFoodAllergies() {
        return HistoryFoodAllergies;
    }

    public void setHistoryFoodAllergies(String historyFoodAllergies) {
        HistoryFoodAllergies = historyFoodAllergies;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }


}

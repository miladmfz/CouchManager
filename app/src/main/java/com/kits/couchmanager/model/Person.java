package com.kits.couchmanager.model;

import com.google.gson.annotations.SerializedName;


public class Person {


    @SerializedName("PersonCode")   private String PersonCode;
    @SerializedName("FirstName")    private String FirstName;
    @SerializedName("LastName")     private String LastName;
    @SerializedName("MobileNumber") private String MobileNumber;
    @SerializedName("Email")        private String Email;
    @SerializedName("Address")      private String Address;
    @SerializedName("WeightValue")  private String WeightValue;

    @SerializedName("Height")       private String Height;
    @SerializedName("BloodType")    private String BloodType;
    @SerializedName("Explain")      private String Explain;
    @SerializedName("Age")          private String Age;
    @SerializedName("BirthDate")    private String BirthDate;
    @SerializedName("KodeMelli")    private String KodeMelli;
    @SerializedName("Introduction") private String Introduction;
    @SerializedName("Workplace")    private String Workplace;
    @SerializedName("SportsGoal")   private String SportsGoal;
    @SerializedName("Homeplace")   private String Homeplace;
    @SerializedName("Instagram")   private String Instagram;
    @SerializedName("Phone")   private String Phone;
    @SerializedName("BMI")   private String BMI;
    @SerializedName("date")   private String date;
    @SerializedName("PlanCode")   private String PlanCode;
    @SerializedName("HistoryWorkOut")       private String HistoryWorkOut;
    @SerializedName("HistoryComplement")    private String HistoryComplement;
    @SerializedName("HistoryEnergiDrugs")   private String HistoryEnergiDrugs;
    @SerializedName("HistoryHealthDrugs")   private String HistoryHealthDrugs;
    @SerializedName("HistoryLimitations")   private String HistoryLimitations;
    @SerializedName("HistoryFoodAllergies") private String HistoryFoodAllergies;
    @SerializedName("HistoryCoach") private String HistoryCoach;
    @SerializedName("Image") private String Image;
    @SerializedName("SexGeneric") private String SexGeneric;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlanCode() {
        return PlanCode;
    }

    public void setPlanCode(String planCode) {
        PlanCode = planCode;
    }

    public String getWeightValue() {
        return WeightValue;
    }

    public void setWeightValue(String weightValue) {
        WeightValue = weightValue;
    }

    public String getSexGeneric() {
        return SexGeneric;
    }

    public void setSexGeneric(String sexGeneric) {
        SexGeneric = sexGeneric;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
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

    public String getExplain() {
        return Explain;
    }

    public void setExplain(String explain) {
        Explain = explain;
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


    public String getWorkplace() {
        return Workplace;
    }

    public void setWorkplace(String workplace) {
        Workplace = workplace;
    }

    public String getHomeplace() {
        return Homeplace;
    }

    public void setHomeplace(String homeplace) {
        Homeplace = homeplace;
    }

    public String getInstagram() {
        return Instagram;
    }

    public void setInstagram(String instagram) {
        Instagram = instagram;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getHistoryCoach() {
        return HistoryCoach;
    }

    public void setHistoryCoach(String historyCoach) {
        HistoryCoach = historyCoach;
    }
}

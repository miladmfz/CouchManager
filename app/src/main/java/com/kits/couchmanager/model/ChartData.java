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



    public void setDuration(String duration) {
        Duration = duration;
    }



    public void setWeeks(String weeks) {
        Weeks = weeks;
    }


    public void setWeightCode(String weightCode) {
        WeightCode = weightCode;
    }


    public void setPersonRef(String personRef) {
        PersonRef = personRef;
    }


    public void setWeightValue(String weightValue) {
        WeightValue = weightValue;
    }


    public void setBMI(String BMI) {
        this.BMI = BMI;
    }



    public void setDate(String date) {
        Date = date;
    }


    public String getBMI()         { if(BMI==null)         {BMI="";return BMI;}        else {return BMI;}     }
    public String getWeightValue() { if(WeightValue==null) {WeightValue="";return WeightValue;}else {return WeightValue;}}
    public String getPersonRef()   { if(PersonRef==null)   {PersonRef="";return PersonRef;}  else {return PersonRef;}    }
    public String getWeightCode()  { if(WeightCode==null)  {WeightCode="";return WeightCode;} else {return WeightCode;}    }
    public String getWeeks()       { if(Weeks==null)       {Weeks="";return Weeks;}      else {return Weeks;}    }
    public String getDuration()    { if(Duration==null)    {Duration="";return Duration;}   else {return Duration;}    }
    public String getDate()        { if(Date==null)        {Date="";return Date;}       else {return Date;}    }

}

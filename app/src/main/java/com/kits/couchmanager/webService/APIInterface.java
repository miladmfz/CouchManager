package com.kits.couchmanager.webService;//package com.kits.couchmanager.webService;


import com.kits.couchmanager.model.RetrofitResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {



    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> InsertPerson(@Field("tag") String tag
            ,@Field("FirstName") String FirstName
            ,@Field("LastName") String LastName
            ,@Field("SexGeneric") String SexGeneric
            ,@Field("MobileNumber") String MobileNumber
            ,@Field("KodeMelli") String KodeMelli
            ,@Field("Height") String Height
            ,@Field("BloodType") String BloodType
            ,@Field("Age") String Age
            ,@Field("BirthDate") String BirthDate
            ,@Field("SportsGoal") String SportsGoal
            ,@Field("Introduction") String Introduction
            ,@Field("Workplace") String Workplace
            ,@Field("Homeplace") String Homeplace
            ,@Field("Phone") String Phone
            ,@Field("Instagram") String Instagram
            ,@Field("Email") String Email
            ,@Field("Address") String Address
            ,@Field("HistoryWorkOut") String HistoryWorkOut
            ,@Field("HistoryComplement") String HistoryComplement
            ,@Field("HistoryEnergiDrugs") String HistoryEnergiDrugs
            ,@Field("HistoryHealthDrugs") String HistoryHealthDrugs
            ,@Field("HistoryFoodAllergies") String HistoryFoodAllergies
            ,@Field("HistoryLimitations") String HistoryLimitations
            ,@Field("HistoryCoach") String HistoryCoach
            ,@Field("Explain") String Explain
            ,@Field("WeightValue") String WeightValue
            ,@Field("Date") String Date
            ,@Field("PersonCode") String PersonCode
    );


    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> InsertTimeSheet(@Field("tag") String tag
            ,@Field("PlanRef") String PlanRef
            ,@Field("TimeSheetDate") String TimeSheetDate
            ,@Field("DailyTime") String DailyTime
            ,@Field("State") String State
            ,@Field("Freeze") String Freeze
            ,@Field("Delay") String Delay
            ,@Field("WorkOutQuality") String WorkOutQuality
            ,@Field("WarmQuality") String WarmQuality

    );


    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> GetPerson(@Field("tag") String tag
            ,@Field("Searchtarget") String SearchTarget
            ,@Field("PersonCode") String PersonCode);



    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> getImagetest(@Field("tag") String tag
            ,@Field("ImageName") String ImageName
            ,@Field("IMG") String IMG
            ,@Field("ClassName") String ClassName
            ,@Field("IType") String IType
            ,@Field("ObjectRef") String ObjectRef
            ,@Field("Date") String Date);




    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> InsertWeight(@Field("tag") String tag
            ,@Field("PersonCode") String PersonCode
            ,@Field("WeightValue") String WeightValue
            ,@Field("Date") String Date);

    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> Getweightdata(@Field("tag") String tag
            ,@Field("PersonCode") String PersonCode);



    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> Insertbodytdata(@Field("tag") String tag
            ,@Field("PersonCode") String PersonCode
            ,@Field("FatPercentage") String FatPercentage
            ,@Field("Abs") String Abs
            ,@Field("Butt") String Butt
            ,@Field("Chest") String Chest
            ,@Field("ForeArm") String ForeArm
            ,@Field("Leg") String Leg
            ,@Field("Wrist") String Wrist
            ,@Field("Ankle") String Ankle
            ,@Field("Date") String Date);

    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> Getbodytdata(@Field("tag") String tag
            ,@Field("PersonCode") String PersonCode);

    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> GetImage(@Field("tag") String tag
            ,@Field("PersonCode") String PersonCode
            ,@Field("IType") String IType
            ,@Field("ClassName") String ClassName);

    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> InsertImage(@Field("tag") String tag
            ,@Field("PersonCode") String PersonCode
            ,@Field("IType") String IType
            ,@Field("ClassName") String ClassName
            ,@Field("IMG") String IMG);



    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> NewPlan(@Field("tag") String tag
            ,@Field("PersonRef") String PersonRef
            ,@Field("StartDate") String StartDate
            ,@Field("EndDate") String EndDate
            ,@Field("WeekPeriod") String WeekPeriod
            ,@Field("DayPeriod") String DayPeriod
            ,@Field("TargetPlan") String TargetPlan
            ,@Field("Active") String Active);

    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> NewPlanRow(@Field("tag") String tag
            ,@Field("plancode") String plancode
            ,@Field("itemRef") String itemRef
            ,@Field("Repeat") String Repeat
            ,@Field("Duration") String Duration
            ,@Field("DayInWeek") String DayInWeek
            ,@Field("PercentPower") String PercentPower
            ,@Field("Explain") String Explain);



    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> GetPlanRow(@Field("tag") String tag
            ,@Field("plantype") String PlanType
            ,@Field("Searchtarget") String SearchTarget
            ,@Field("PersonCode") String PersonCode
            ,@Field("planCode") String planCode);


    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> GetPlan(@Field("tag") String tag
            ,@Field("Searchtarget") String SearchTarget
            ,@Field("PersonCode") String PersonCode
            ,@Field("planCode") String planCode);

    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> GetLastPersonPlan(@Field("tag") String tag
            ,@Field("PersonCode") String PersonCode);


    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> GetTimeSheet(@Field("tag") String tag
            ,@Field("planCode") String planCode);


    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> DeleteTimeSheetState(@Field("tag") String tag
            ,@Field("TimeSheetCode") String TimeSheetCode);



    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> GetItem(@Field("tag") String tag
            ,@Field("ItemType") String ItemType
            ,@Field("Searchtarget") String Searchtarget);



    @POST("index.php")
    @FormUrlEncoded
    Call<RetrofitResponse> GetPlanbycode(@Field("tag") String tag
            ,@Field("PlanCode") String PlanCode);


}


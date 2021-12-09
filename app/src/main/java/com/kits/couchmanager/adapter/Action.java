package com.kits.couchmanager.adapter;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.kits.couchmanager.R;
import com.kits.couchmanager.activity.CreatepersonActivity;
import com.kits.couchmanager.activity.CreateplanActivity;
import com.kits.couchmanager.activity.PersonDetailActivity;
import com.kits.couchmanager.model.BodyData;
import com.kits.couchmanager.model.RetrofitResponse;
import com.kits.couchmanager.webService.APIClient;
import com.kits.couchmanager.webService.APIInterface;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Action {

    PersianCalendar calendar1;

    String year;
    String mount;
    String day;
    String date;

    private final Context mContext;
    public APIInterface apiInterface = APIClient.getCleint().create(APIInterface.class);

    ArrayList<BodyData> bodyDatas;
    Intent intent;
    public Action(Context context) {
        this.mContext = context;

    }


    public void Insertweight(String PersonCode, String name) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.insertweight);

        Button boxbuy = dialog.findViewById(R.id.insertweightbtn);
        EditText weight = dialog.findViewById(R.id.insertweightvalue);
        TextView tvname = dialog.findViewById(R.id.insertweightname);
        TextView tvdate = dialog.findViewById(R.id.insertweightdate);

        calendar1 = new PersianCalendar();

        calendar1.setPersianDate(
                calendar1.getPersianYear(),
                calendar1.getPersianMonth()+1,
                calendar1.getPersianDay()
        );
        year="";
        mount="0";
        day="0";
        year=year+calendar1.getPersianYear();
        mount=mount+calendar1.getPersianMonth();
        day=day+(calendar1.getPersianDay());
        date = year+"/"+mount.substring(mount.length()-2)+"/"+day.substring(day.length()-2);



        tvname.setText(name);


        tvdate.setText(date);

        boxbuy.setOnClickListener(view -> {

            Call<RetrofitResponse> call = apiInterface.InsertWeight("InsertWeight",PersonCode,weight.getText().toString(),tvdate.getText().toString());
            call.enqueue(new Callback<RetrofitResponse>() {
                @Override
                public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
                    if(response.isSuccessful()) {
                        assert response.body() != null;
                        intent = new Intent(mContext, PersonDetailActivity.class);
                        intent.putExtra("personcode", response.body().getPersons().get(0).getPersonCode());
                        intent.putExtra("createplan", "0");
                        mContext.startActivity(intent);
                        ((Activity) mContext).finish();
                    }
                }
                @Override
                public void onFailure(Call<RetrofitResponse> call, Throwable t) {

                }
            });

        });
        dialog.show();


    }


    public void bodydatashow(String PersonCode, String name) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bodydata);

        Button insertbtn = dialog.findViewById(R.id.bodydatatoinsert_btn);
        TextView FatPercentage = dialog.findViewById(R.id.bodydata_fatpercentage);
        TextView Abs = dialog.findViewById(R.id.bodydata_abs);
        TextView Butt = dialog.findViewById(R.id.bodydata_butt);
        TextView Chest = dialog.findViewById(R.id.bodydata_chest);
        TextView ForeArm = dialog.findViewById(R.id.bodydata_forearm);
        TextView Leg = dialog.findViewById(R.id.bodydata_leg);
        TextView Wrist = dialog.findViewById(R.id.bodydata_wrist);
        TextView Ankle = dialog.findViewById(R.id.bodydata_ankle);
        TextView date = dialog.findViewById(R.id.bodydata_date);
        FatPercentage.setText("");
        Abs.setText("");
        Butt.setText("");
        Chest.setText("");
        ForeArm.setText("");
        Leg.setText("");
        Wrist.setText("");
        Ankle.setText("");
        date.setText("");

        Call<RetrofitResponse> call = apiInterface.Getbodytdata("Getbodydata",PersonCode);
        call.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    bodyDatas=response.body().getBodyDatas();
                    FatPercentage.setText(bodyDatas.get(0).getFatPercentage());
                    Abs.setText(bodyDatas.get(0).getAbs());
                    Butt.setText(bodyDatas.get(0).getButt());
                    Chest.setText(bodyDatas.get(0).getChest());
                    ForeArm.setText(bodyDatas.get(0).getForeArm());
                    Leg.setText(bodyDatas.get(0).getLeg());
                    Wrist.setText(bodyDatas.get(0).getWrist());
                    Ankle.setText(bodyDatas.get(0).getAnkle());
                    date.setText(bodyDatas.get(0).getDate());
                }
            }
            @Override
            public void onFailure(Call<RetrofitResponse> call, Throwable t) {
                FatPercentage.setText("");
                Abs.setText("");
                Butt.setText("");
                Chest.setText("");
                ForeArm.setText("");
                Leg.setText("");
                Wrist.setText("");
                Ankle.setText("");
                date.setText("");

            }
        });


        insertbtn.setOnClickListener(v -> {
            bodydatainsert(PersonCode,name);
            dialog.dismiss();
        });

        dialog.show();


    }




    public void bodydatainsert(String PersonCode, String name) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bodydatainsert);

        Button boxbuy = dialog.findViewById(R.id.bodydatainsert_btn);
        EditText FatPercentage = dialog.findViewById(R.id.bodydatainsert_fatpercentage);
        EditText Abs = dialog.findViewById(R.id.bodydatainsert_abs);
        EditText Butt = dialog.findViewById(R.id.bodydatainsert_butt);
        EditText Chest = dialog.findViewById(R.id.bodydatainsert_chest);
        EditText ForeArm = dialog.findViewById(R.id.bodydatainsert_forearm);
        EditText Leg = dialog.findViewById(R.id.bodydatainsert_leg);
        EditText Wrist = dialog.findViewById(R.id.bodydatainsert_wrist);
        EditText Ankle = dialog.findViewById(R.id.bodydatainsert_ankle);
        TextView date = dialog.findViewById(R.id.bodydatainsert_date);

        PersianCalendar calendar = new PersianCalendar();
        Call<RetrofitResponse> call = apiInterface.Getbodytdata("Getbodydata",PersonCode);
        call.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    bodyDatas=response.body().getBodyDatas();
                    FatPercentage.setText(bodyDatas.get(0).getFatPercentage());
                    Abs.setText(bodyDatas.get(0).getAbs());
                    Butt.setText(bodyDatas.get(0).getButt());
                    Chest.setText(bodyDatas.get(0).getChest());
                    ForeArm.setText(bodyDatas.get(0).getForeArm());
                    Leg.setText(bodyDatas.get(0).getLeg());
                    Wrist.setText(bodyDatas.get(0).getWrist());
                    Ankle.setText(bodyDatas.get(0).getAnkle());
                }
            }
            @Override
            public void onFailure(Call<RetrofitResponse> call, Throwable t) {
                FatPercentage.setText("");
                Abs.setText("");
                Butt.setText("");
                Chest.setText("");
                ForeArm.setText("");
                Leg.setText("");
                Wrist.setText("");
                Ankle.setText("");
                date.setText("");

            }
        });

        date.setText(calendar.getPersianShortDate());
        boxbuy.setOnClickListener(view -> {

            String PersonCode1= PersonCode;
            String FatPercentage1= FatPercentage.getText().toString();
            String Abs1= Abs.getText().toString();
            String Butt1= Butt.getText().toString();
            String Chest1= Chest.getText().toString();
            String ForeArm1= ForeArm.getText().toString();
            String Leg1= Leg.getText().toString();
            String Wrist1= Wrist.getText().toString();
            String Ankle1= Ankle.getText().toString();
            String calendar1= calendar.getPersianShortDate();

            Call<RetrofitResponse> call1 = apiInterface.Insertbodytdata("InsertBodyData"
                    ,PersonCode1
                    ,FatPercentage1
                    ,Abs1
                    ,Butt1
                    ,Chest1
                    ,ForeArm1
                    ,Leg1
                    ,Wrist1
                    ,Ankle1
                    ,calendar1

            );
            call1.enqueue(new Callback<RetrofitResponse>() {
                @Override
                public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
                    Log.e("test","0");

                    if(response.isSuccessful()) {
                        assert response.body() != null;
                        intent = new Intent(mContext, PersonDetailActivity.class);
                        intent.putExtra("personcode", response.body().getPersons().get(0).getPersonCode());
                        intent.putExtra("createplan", "0");
                        mContext.startActivity(intent);
                        ((Activity) mContext).finish();
                    }
                }
                @Override
                public void onFailure(Call<RetrofitResponse> call, Throwable t) {
                    Log.e("test","1");
                    Log.e("test",t.getMessage());

                }
            });
        });
        dialog.show();


    }



    public String CursorToJson(Cursor cursor) {
        JSONArray resultSet = new JSONArray();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();
            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                    } catch (Exception e) {
                        Log.d("CursorToJson_Error: ", Objects.requireNonNull(e.getMessage()));
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }
        cursor.close();
        return resultSet.toString();
    }

}

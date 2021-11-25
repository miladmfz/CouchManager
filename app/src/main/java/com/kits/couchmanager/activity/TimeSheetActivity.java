package com.kits.couchmanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kits.couchmanager.R;

public class TimeSheetActivity extends AppCompatActivity {
    String Personcode;
    String Freeze;
    String Plancode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_sheet);
        intent();
        init();

    }

    public void intent() {
        Bundle data = getIntent().getExtras();
        assert data != null;
        Personcode = data.getString("personcode");
        Freeze = data.getString("freeze");
        Plancode = data.getString("plancode");
    }

    public void init() {



    }



}
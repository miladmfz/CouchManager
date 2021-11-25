package com.kits.couchmanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.kits.couchmanager.R;

import java.util.ArrayList;

public class CreateitemActivity extends AppCompatActivity {

    String createplan="";
    String personcode="";
    LinearLayoutCompat mainline;
    ArrayList<String> arrayCat=new ArrayList<>() ;
    ArrayList<String[]> arraypersondata=new ArrayList<>() ;
    ArrayAdapter<String> spinner_adapter;
    Spinner spinner;

    String plantype;
    String itemtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createitem);

        intent();
        //init();

    }

    public void intent() {
        Bundle data = getIntent().getExtras();
        assert data != null;
        itemtype = data.getString("itemtype");
        createplan = data.getString("createplan");

    }

    public void init() {
        mainline=findViewById(R.id.create_mainline_plan);
        datainitial();


        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, arrayCat);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinner_adapter);
        spinner.setSelection(Integer.parseInt(itemtype));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mainline.removeAllViews();
                itemtype=String.valueOf(position);
                createview();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void createview() {

        switch (itemtype) {
            case "0":
                TextView extra_TextView1 = new TextView(this);
                extra_TextView1.setText("کاوه چی میخوای ایجاد کنی؟");
                extra_TextView1.setBackgroundResource(R.color.white);
                extra_TextView1.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.MATCH_PARENT));
                extra_TextView1.setTextSize(28);
                extra_TextView1.setPadding(2, 2, 2, 2);
                extra_TextView1.setGravity(Gravity.CENTER);
                extra_TextView1.setTextColor(getResources().getColor(R.color.grey_1000));
                mainline.addView(extra_TextView1);
                break;
            case "1":


                break;
            case "2":


                break;
            case "3":


                break;

        }
    }


    public void datainitial() {

        arrayCat.add("انتخاب چیز");
        arrayCat.add("حرکت ورزشی جدید");
        arrayCat.add("غذای جدید");
        arrayCat.add("مکمل جدید");

    }
}
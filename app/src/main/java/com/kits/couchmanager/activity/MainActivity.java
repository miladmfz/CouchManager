package com.kits.couchmanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.kits.couchmanager.R;
import com.kits.couchmanager.adapter.Action;
import com.kits.couchmanager.model.RetrofitResponse;
import com.kits.couchmanager.webService.APIClient;
import com.kits.couchmanager.webService.APIInterface;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public APIInterface apiInterface = APIClient.getCleint().create(APIInterface.class);

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }


    //***************************************************************************************

    public void init() {

        Button allperson =findViewById(R.id.main_allperson);
        Button workout_planlist =findViewById(R.id.main_workoutplanlist);
        Button workout_list =findViewById(R.id.main_workoutlist);
        Button food_planlist =findViewById(R.id.main_foodplanlist);
        Button food_list =findViewById(R.id.main_foodlist);
        Button complement_planlist =findViewById(R.id.main_complementplanlist);
        Button complement_list =findViewById(R.id.main_complementlist);
        Button createplan =findViewById(R.id.main_createplan);
        workout_planlist.setVisibility(View.GONE);
        food_planlist.setVisibility(View.GONE);
        complement_planlist.setVisibility(View.GONE);
        createplan.setVisibility(View.GONE);
        allperson.setOnClickListener(v -> {

            PersianCalendar calendar= new PersianCalendar();

//            //String strdate = calendar.getPersianShortDate();
//            Call<RetrofitResponse> call =apiInterface.getImagetest(
//                    "getImagetest"
//                    , "4_"+"Person"
//                    , getString(R.string.app_name)
//                    ,"Person"
//                    ,""
//                    ,"4"
//                    ,calendar.getPersianShortDate()
//                    );
//            call.enqueue(new Callback<RetrofitResponse>() {
//                @Override
//                public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
//
//                }
//
//                @Override
//                public void onFailure(Call<RetrofitResponse> call, Throwable t) {
//
//                }
//            });


            intent = new Intent(MainActivity.this, PersonActivity.class);
            intent.putExtra("createplan", "0");
            intent.putExtra("plantype", "0");
            intent.putExtra("personcode", "0");
            startActivity(intent);


        });

        workout_planlist.setVisibility(View.GONE);
        workout_planlist.setOnClickListener(v -> {
            intent = new Intent(MainActivity.this, PlanActivity.class);
            intent.putExtra("plantype", "1");
            intent.putExtra("personcode", "0");
            startActivity(intent);
        });


        food_planlist.setVisibility(View.GONE);
        food_planlist.setOnClickListener(v -> {
            intent = new Intent(MainActivity.this, PlanActivity.class);
            intent.putExtra("plantype", "2");
            intent.putExtra("personcode", "0");
            startActivity(intent);
        });


        complement_planlist.setVisibility(View.GONE);
        complement_planlist.setOnClickListener(v -> {
            intent = new Intent(MainActivity.this, PlanActivity.class);
            intent.putExtra("plantype", "3");
            intent.putExtra("personcode", "0");

            startActivity(intent);
        });



        workout_list.setVisibility(View.GONE);
        workout_list.setOnClickListener(v -> {
            intent = new Intent(MainActivity.this, CategoryActivity.class);
            intent.putExtra("itemtype", "1");
            startActivity(intent);
        });


        food_list.setVisibility(View.GONE);
        food_list.setOnClickListener(v -> {
            intent = new Intent(MainActivity.this, CategoryActivity.class);
            intent.putExtra("itemtype", "3");
            startActivity(intent);
        });


        complement_list.setVisibility(View.GONE);
        complement_list.setOnClickListener(v -> {
            intent = new Intent(MainActivity.this, CategoryActivity.class);
            intent.putExtra("itemtype", "2");
            startActivity(intent);
        });


        createplan.setOnClickListener(v -> {
            intent = new Intent(MainActivity.this, CreateplanActivity.class);
            intent.putExtra("plantype", "0");
            intent.putExtra("createplan", "0");
            intent.putExtra("personcode", "0");
            startActivity(intent);
        });


    }


}
package com.kits.couchmanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kits.couchmanager.R;
import com.kits.couchmanager.adapter.App;
import com.kits.couchmanager.adapter.PlanAdapter;
import com.kits.couchmanager.adapter.TimeSheetAdapter;
import com.kits.couchmanager.model.ChartData;
import com.kits.couchmanager.model.NumberFunctions;
import com.kits.couchmanager.model.Person;
import com.kits.couchmanager.model.Plan;
import com.kits.couchmanager.model.RetrofitResponse;
import com.kits.couchmanager.model.TimeSheet;
import com.kits.couchmanager.webService.APIClient;
import com.kits.couchmanager.webService.APIInterface;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class TimeSheetActivity extends AppCompatActivity {
    String Personcode;
    String Plancode;
    String Freeze="0";
    int state=0;
    String focus="";
    String warm="";
    ArrayList<TimeSheet> timeSheets;
    ArrayList<Plan> plans;
    Plan plan;
    ArrayAdapter spinner_adapter;
    RecyclerView recyclerView;
    String year;
    String mount;
    String day;
    String date;

    static String strSDCardPathName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/CouchManager" + "/";

    public APIInterface apiInterface = APIClient.getCleint().create(APIInterface.class);
    ArrayList<String> Data_spinnerstate =new ArrayList<>() ;
    ArrayList<String> Data_spinnerfocus =new ArrayList<>() ;
    ArrayList<String> Data_spinnerwarm =new ArrayList<>() ;



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


        recyclerView =findViewById(R.id.tsactivity_rc);




        Call<RetrofitResponse> call = apiInterface.GetLastPersonPlan("GetLastPersonPlan",Personcode);
        call.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(@NotNull Call<RetrofitResponse> call, @NotNull Response<RetrofitResponse> response) {
                assert response.body() != null;
                if (response.isSuccessful()) {
                    plans = response.body().getPlans();
                    Call<RetrofitResponse> call1 = apiInterface.GetTimeSheet("GetTimeSheet",plans.get(0).getPlanCode());
                    call1.enqueue(new Callback<RetrofitResponse>() {
                        @Override
                        public void onResponse(@NotNull Call<RetrofitResponse> call, @NotNull Response<RetrofitResponse> response) {
                            assert response.body() != null;
                            if (response.isSuccessful()) {
                                timeSheets=response.body().getTimeSheets();
                                TimeSheetAdapter planAdapter= new TimeSheetAdapter(timeSheets, TimeSheetActivity.this);
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(App.getContext(), 1);
                                recyclerView.setLayoutManager(gridLayoutManager);
                                recyclerView.setAdapter(planAdapter);
                                recyclerView.setItemAnimator(new FlipInTopXAnimator());
                                int activecount = 0;
                                int unactivecount = 0;
                                int frezecount = 0;

                                activecount=timeSheets.size();
                                int day=Integer.parseInt(plans.get(0).getDayPeriod());
                                int week=Integer.parseInt(plans.get(0).getWeekPeriod());
                                int session=0;
                                session=day*week;


                                for (TimeSheet ts : timeSheets) {
                                    if (ts.getFreeze().equals("1")) {
                                        frezecount++;
                                    }
                                }
                                for (TimeSheet ts : timeSheets) {
                                    if (ts.getState().equals("4")) {
                                        unactivecount++;
                                    }
                                }


                                if (activecount < session) {
                                    createview();
                                } else {
                                    if(unactivecount>0){
                                       if(frezecount!=unactivecount) {
                                           createview();
                                       }
                                    }else {
                                        Toast.makeText(TimeSheetActivity.this, "عضویت اتمام یافته است", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(@NotNull Call<RetrofitResponse> call, @NotNull Throwable t) {
                            Log.e("test",t.getMessage());

                            if(plans.get(0).getActive().equals("1")){
                                createview();
                            }
                        }
                    });


                }

            }
            @Override
            public void onFailure(@NotNull Call<RetrofitResponse> call, @NotNull Throwable t) {

            }
        });



    }

    public void createview() {



        TextView ed_date  =findViewById(R.id.tsactivity_date);
        EditText ed_delay =findViewById(R.id.tsactivity_delay);
        TextView ed_startdate =findViewById(R.id.tsactivity_startdate);
        TextView ed_enddate =findViewById(R.id.tsactivity_enddate);
        TextView ed_explain =findViewById(R.id.tsactivity_explain);

        Button btn_savedata =findViewById(R.id.tsactivity_btn);

        TextView ed_session =findViewById(R.id.tsactivity_session);

        Spinner spinnerstate =findViewById(R.id.tsactivity_spinnerstate);
        Spinner spinnerqualitywarm=findViewById(R.id.tsactivity_spinnerqualitywarm);
        Spinner spinnerfocus=findViewById(R.id.tsactivity_spinnerfocus);


        ed_startdate.setText(NumberFunctions.PerisanNumber(plans.get(0).getStartDate()));
        ed_enddate.setText(NumberFunctions.PerisanNumber(plans.get(0).getEndDate()));


        PersianCalendar calendar=new PersianCalendar();


        year="";
        mount="0";
        day="0";
        year=year+calendar.getPersianYear();
        mount=mount+(calendar.getPersianMonth()+1);
        day=day+(calendar.getPersianDay());
        date = year+"/"+mount.substring(mount.length()-2)+"/"+day.substring(day.length()-2);
        ed_date.setText(NumberFunctions.PerisanNumber(date));


        Data_spinnerstate.add("انتخاب");
        Data_spinnerstate.add("حاضر");
        Data_spinnerstate.add("غیبت");
        Data_spinnerstate.add("فریز");


        Data_spinnerfocus.add("انتخاب");
        Data_spinnerfocus.add("کم");
        Data_spinnerfocus.add("خوب");
        Data_spinnerfocus.add("عالی");

        Data_spinnerwarm.add("انتخاب");
        Data_spinnerwarm.add("کم");
        Data_spinnerwarm.add("خوب");
        Data_spinnerwarm.add("عالی");



        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Data_spinnerstate);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerstate.setAdapter(spinner_adapter);
        spinnerstate.setSelection(0);

        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Data_spinnerfocus);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerqualitywarm.setAdapter(spinner_adapter);
        spinnerqualitywarm.setSelection(0);

        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Data_spinnerwarm);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfocus.setAdapter(spinner_adapter);
        spinnerfocus.setSelection(0);

        try{
            int count=timeSheets.size()+1;
            ed_session.setText(NumberFunctions.PerisanNumber(String.valueOf(count)));
        }catch (Exception e){
            ed_session.setText(NumberFunctions.PerisanNumber("1"));
        }
        spinnerstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override  public void onNothingSelected(AdapterView<?> parent) {   }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state=position;
                if (state==3){
                    warm="";
                    focus="";
                }
                if (state==2){
                    warm="";
                    focus="";
                }
            }
        });

        spinnerqualitywarm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override  public void onNothingSelected(AdapterView<?> parent) {   }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                warm=Data_spinnerwarm.get(position);
            }
        });

        spinnerfocus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override  public void onNothingSelected(AdapterView<?> parent) {   }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                focus=Data_spinnerfocus.get(position);
            }
        });


        btn_savedata.setOnClickListener(v -> {
            if(focus.equals("انتخاب"))
                focus="";
            if(warm.equals("انتخاب"))
                warm="";

            if(state==0){
                Toast.makeText(this, "وضعیت را وارد کنید", Toast.LENGTH_SHORT).show();
            }else{
                Call<RetrofitResponse> call = apiInterface.InsertTimeSheet(
                        "InsertTimeSheet"
                        ,plans.get(0).getPlanCode()
                        ,NumberFunctions.EnglishNumber(ed_date.getText().toString())
                        ,NumberFunctions.EnglishNumber(ed_explain.getText().toString())
                        ,String.valueOf(state)
                        ,Freeze
                        ,NumberFunctions.EnglishNumber(ed_delay.getText().toString())
                        ,focus
                        ,warm
                );


                call.enqueue(new Callback<RetrofitResponse>() {
                    @Override
                    public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<RetrofitResponse> call, Throwable t) {

                        Log.e("test1",t.getMessage());
                    }
                });



            }





        });



    }

}
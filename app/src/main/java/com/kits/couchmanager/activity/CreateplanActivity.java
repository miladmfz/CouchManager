package com.kits.couchmanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
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
import com.kits.couchmanager.adapter.PersonAdapter;
import com.kits.couchmanager.model.NumberFunctions;
import com.kits.couchmanager.model.Plan;
import com.kits.couchmanager.model.RetrofitResponse;
import com.kits.couchmanager.webService.APIClient;
import com.kits.couchmanager.webService.APIInterface;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateplanActivity extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener{
    public APIInterface apiInterface = APIClient.getCleint().create(APIInterface.class);

    PersianCalendar pickcalender;
    LinearLayoutCompat mainline;
    LinearLayoutCompat createplan;
    TextView textView_do;
    TextView tv_personname;
    TextView tv_startdate;
    TextView tv_enddate;
    ArrayList<String> arrayCat=new ArrayList<>() ;
    ArrayList<String> spinner_adapter_week=new ArrayList<>() ;
    ArrayList<String> spinner_adapter_day=new ArrayList<>() ;
    ArrayList<String> spinner_adapter_target=new ArrayList<>() ;
    ArrayList<String[]> arraypersondata=new ArrayList<>() ;
    ArrayAdapter spinner_adapter;
    Spinner spinner;


    String year;
    String mount;
    String day;
    String date;

    Intent intent;


    PersianCalendar persianCalendar1;
    DatePickerDialog datePickerDialog;

    String personcode;
    String personname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createplan);

        intent();
        init();

    }

    public void intent() {
        Bundle data = getIntent().getExtras();
        assert data != null;
        personcode = data.getString("personcode");
        personname = data.getString("personname");

    }

    public void init() {
        pickcalender = new PersianCalendar();

        mainline=findViewById(R.id.create_mainline_plan);
        createplan=findViewById(R.id.create_plan);
        datainitial();
        createview();
    }

    public void createview() {


                createplan.setVisibility(View.VISIBLE);

                tv_personname =findViewById(R.id.create_plan_personname);
                tv_startdate =findViewById(R.id.create_plan_startdate);
                tv_enddate =findViewById(R.id.create_plan_enddate);
                tv_personname.setText(personname);
                Spinner spinner_week =findViewById(R.id.create_plan_spinner_week);
                Spinner spinner_day =findViewById(R.id.create_plan_spinner_day);
                Spinner spinner_target =findViewById(R.id.create_plan_spinner_target);

                tv_startdate.setText(NumberFunctions.PerisanNumber(date));

                spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, spinner_adapter_week);
                spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_week.setAdapter(spinner_adapter);
                spinner_week.setSelection(0);

                spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, spinner_adapter_day);
                spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_day.setAdapter(spinner_adapter);
                spinner_day.setSelection(0);

                spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, spinner_adapter_target);
                spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_target.setAdapter(spinner_adapter);
                spinner_target.setSelection(0);

                spinner_week.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        PersianCalendar calendar = new PersianCalendar();

                        calendar.setPersianDate(
                                pickcalender.getPersianYear(),
                                pickcalender.getPersianMonth(),
                                pickcalender.getPersianDay() +(position*7)
                        );



                        year="";
                        mount="0";
                        day="0";
                        year=year+calendar.getPersianYear();
                        mount=mount+((calendar.getPersianMonth())+1);
                        day=day+(calendar.getPersianDay());
                        date = year+"/"+mount.substring(mount.length()-2)+"/"+day.substring(day.length()-2);

                        tv_enddate.setText(NumberFunctions.PerisanNumber(date));

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                tv_startdate.setOnClickListener(v -> {
                    setdate();
                });
                tv_startdate.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        PersianCalendar calendar = new PersianCalendar();


                        if(spinner_week.getSelectedItemPosition()==0){
                            calendar.setPersianDate(
                                    pickcalender.getPersianYear(),
                                    pickcalender.getPersianMonth(),
                                    pickcalender.getPersianDay()
                            );
                        }else {
                            calendar.setPersianDate(
                                    pickcalender.getPersianYear(),
                                    pickcalender.getPersianMonth(),
                                    pickcalender.getPersianDay() +(spinner_week.getSelectedItemPosition() * 7)
                            );
                        }
                        Log.e("test",calendar.getPersianShortDate());
                        year="";
                        mount="0";
                        day="0";
                        year=year+calendar.getPersianYear();
                        mount=mount+(calendar.getPersianMonth()+1);
                        day=day+(calendar.getPersianDay());
                        date = year+"/"+mount.substring(mount.length()-2)+"/"+day.substring(day.length()-2);

                        tv_enddate.setText(NumberFunctions.PerisanNumber(date));
                    }
                });


                Button btn=findViewById(R.id.create_plan_btn);
                btn.setOnClickListener(v -> {

                    Call<RetrofitResponse> call = apiInterface.NewPlan(
                            "NewPlan",
                            personcode+"",
                            NumberFunctions.EnglishNumber(tv_startdate.getText().toString())+"",
                            NumberFunctions.EnglishNumber(tv_enddate.getText().toString())+"",
                            spinner_week.getSelectedItemPosition()+"",
                            spinner_day.getSelectedItemPosition()+"",
                            spinner_adapter_target.get(spinner_target.getSelectedItemPosition())+"",
                            "1");
                    call.enqueue(new Callback<RetrofitResponse>() {
                        @Override
                        public void onResponse(@NotNull Call<RetrofitResponse> call, @NotNull Response<RetrofitResponse> response) {
                            assert response.body() != null;
                            if (response.isSuccessful()) {
//                                Plan plan =response.body().getPlans().get(0);
//                                intent = new Intent(CreateplanActivity.this, CreaterowActivity.class);
//                                intent.putExtra("plancode", plan.getPlanCode());
//                                intent.putExtra("dayperiod", plan.getDayPeriod());
//                                startActivity(intent);

                                intent = new Intent(CreateplanActivity.this, PersonDetailActivity.class);
                                intent.putExtra("personcode", personcode);
                                startActivity(intent);



                            }
                        }
                        @Override
                        public void onFailure(@NotNull Call<RetrofitResponse> call, @NotNull Throwable t) {
                        }
                    });

                });




    }




    public void setdate() {
        persianCalendar1 = new PersianCalendar();
        datePickerDialog = DatePickerDialog.newInstance(
                CreateplanActivity.this::onDateSet,
                persianCalendar1.getPersianYear(),
                persianCalendar1.getPersianMonth(),
                persianCalendar1.getPersianDay()
        );
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }

    public void datainitial() {
        arrayCat.add("???????????? ????????????");
        arrayCat.add("???????????? ??????????");
        arrayCat.add("???????????? ??????????");
        arrayCat.add("???????????? ??????????");

        spinner_adapter_week.add("???????????? ????????");
        spinner_adapter_week.add("?? ????");
        spinner_adapter_week.add("?? ????");
        spinner_adapter_week.add("?? ????");
        spinner_adapter_week.add("?? ????????");
        spinner_adapter_week.add("?? ??????");
        spinner_adapter_week.add("?? ????");
        spinner_adapter_week.add("?? ??????");
        spinner_adapter_week.add("?? ??????");
        spinner_adapter_week.add("?? ????");
        spinner_adapter_week.add("???? ????");

        spinner_adapter_day.add("???????????? ??????");
        spinner_adapter_day.add("?? ?????? ???? ????????");
        spinner_adapter_day.add("?? ?????? ???? ????????");
        spinner_adapter_day.add("?? ?????? ???? ????????");
        spinner_adapter_day.add("?? ?????? ???? ????????");
        spinner_adapter_day.add("?? ?????? ???? ????????");
        spinner_adapter_day.add("?? ?????? ???? ????????");
        spinner_adapter_day.add("?? ?????? ???? ????????");



        spinner_adapter_target.add("???????????? ??????");
        spinner_adapter_target.add("???????????? ???????? ????????????");
        spinner_adapter_target.add("???????????? ???????? ?? ??????");
        spinner_adapter_target.add("???????? ???????? ?? ??????");
        spinner_adapter_target.add("???????????? ??????");
        spinner_adapter_target.add("???????? ??????");
        spinner_adapter_target.add("???????? ????");


        pickcalender = new PersianCalendar();
        year="";
        mount="0";
        day="0";
        year=year+pickcalender.getPersianYear();
        mount=mount+(pickcalender.getPersianMonth()+1);
        day=day+(pickcalender.getPersianDay());
        date = year+"/"+mount.substring(mount.length()-2)+"/"+day.substring(day.length()-2);

    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        pickcalender.setPersianDate(year,monthOfYear,dayOfMonth);
        mount="0";
        day="0";
        mount=mount+(monthOfYear+1);
        day=day+(dayOfMonth);
        date = year+"/"+mount.substring(mount.length()-2)+"/"+day.substring(day.length()-2);
        tv_startdate.setText(NumberFunctions.PerisanNumber(date));
    }
}
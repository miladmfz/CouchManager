package com.kits.couchmanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.kits.couchmanager.R;
import com.kits.couchmanager.model.NumberFunctions;
import com.kits.couchmanager.model.RetrofitResponse;
import com.kits.couchmanager.webService.APIClient;
import com.kits.couchmanager.webService.APIInterface;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatepersonActivity extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener {
    APIInterface apiInterface = APIClient.getCleint().create(APIInterface.class);
    PersianCalendar pickcalenderbirth;
    DatePickerDialog datePickerDialog;
    PersianCalendar nowcalender;
    String createplan="";
    String personcode="";
    LinearLayoutCompat mainline;
    ArrayList<String> arraybloodtype=new ArrayList<>() ;
    ArrayAdapter<String> spinner_adapter;
    Spinner spinner;

    Intent intent;
    String plantype;
    String itemtype;
    Spinner ed_BloodType;
    TextView tv_BirthDate;
    TextView tv_Age;

    String edFirstName;
    String edLastName;
    String edMobileNumber;
    String edKodeMelli;
    String edHeight;
    String edBloodType;
    String edAge;
    String edBirthDate;
    String edSportsGoal;
    String edIntroduction;
    String edWorkplace;
    String edHomeplace;
    String edPhone;
    String edInstagram;
    String edEmail;
    String edAddress;
    String edHistoryWorkOut;
    String edHistoryComplement;
    String edHistoryEnergiDrugs;
    String edHistoryHealthDrugs;
    String edHistoryFoodAllergies;
    String edHistoryLimitations;
    String edHistoryCoach;
    String edExplain;
    String edWeightValue;
    String edDate;
    String edgeneric;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createperson);
        intent();
        init();
    }
    public void intent() {
        Bundle data = getIntent().getExtras();
        assert data != null;
        createplan = data.getString("createplan");
        personcode = data.getString("personcode");
    }

    public void init() {
        nowcalender = new PersianCalendar();
        ed_BloodType =findViewById(R.id.nperson_spinner_bloodtype);

        arraybloodtype.add("O−");
        arraybloodtype.add("O+");
        arraybloodtype.add("A−");
        arraybloodtype.add("A+");
        arraybloodtype.add("B−");
        arraybloodtype.add("B+");
        arraybloodtype.add("AB-");
        arraybloodtype.add("AB+");




        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, arraybloodtype);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ed_BloodType.setAdapter(spinner_adapter);
        ed_BloodType.setSelection(0);

        EditText ed_FirstName = findViewById(R.id.nperson_firstname);
        EditText ed_LastName = findViewById(R.id.nperson_lastname);
        EditText ed_MobileNumber = findViewById(R.id.nperson_mobile);
        EditText ed_KodeMelli = findViewById(R.id.nperson_kodemelli);
        EditText ed_Height = findViewById(R.id.nperson_height);
        tv_Age = findViewById(R.id.nperson_age);
        tv_BirthDate = findViewById(R.id.nperson_birthdate);
        EditText ed_SportsGoal = findViewById(R.id.nperson_sportgoal);
        EditText ed_Introduction = findViewById(R.id.nperson_intro);
        EditText ed_Workplace = findViewById(R.id.nperson_workplace);
        EditText ed_Homeplace = findViewById(R.id.nperson_homeplace);
        EditText ed_Phone = findViewById(R.id.nperson_phone);
        EditText ed_Instagram = findViewById(R.id.nperson_instagram);
        EditText ed_Email = findViewById(R.id.nperson_email);
        EditText ed_Address = findViewById(R.id.nperson_address);
        EditText ed_HistoryWorkOut = findViewById(R.id.nperson_hworkout);
        EditText ed_HistoryComplement = findViewById(R.id.nperson_hcomplement);
        EditText ed_HistoryEnergiDrugs = findViewById(R.id.nperson_henergi);
        EditText ed_HistoryHealthDrugs = findViewById(R.id.nperson_hdrug);
        EditText ed_HistoryFoodAllergies = findViewById(R.id.nperson_hallergy);
        EditText ed_HistoryLimitations = findViewById(R.id.nperson_hlimit);
        EditText ed_HistoryCoach = findViewById(R.id.nperson_hcouch);
        EditText ed_Explain = findViewById(R.id.nperson_explain);
        EditText ed_WeightValue = findViewById(R.id.nperson_joinweight);
        TextView ed_Date = findViewById(R.id.nperson_joindate);

        SwitchMaterial sm_SexGeneric = findViewById(R.id.nperson_generic);
        Button addperson = findViewById(R.id.nperson_btn);

        PersianCalendar calendar1=new PersianCalendar();
        ed_Date.setText(calendar1.getPersianShortDate());
        tv_BirthDate.setText("انتخاب کنید");
        tv_BirthDate.setOnClickListener(v -> {
            setdate();

        });






        sm_SexGeneric.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                sm_SexGeneric.setText("مرد");//۱
            } else {
                sm_SexGeneric.setText("زن");//0
            }
        });

        if(!personcode.equals("0")){
            Call<RetrofitResponse> call1 =apiInterface.GetPerson("Getperson","",personcode);
            call1.enqueue(new Callback<RetrofitResponse>() {
                @Override
                public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {

                    ed_FirstName.setText(response.body().getPersons().get(0).getFirstName());
                    ed_LastName.setText(response.body().getPersons().get(0).getLastName());
                    ed_MobileNumber.setText(response.body().getPersons().get(0).getMobileNumber());
                    ed_KodeMelli.setText(response.body().getPersons().get(0).getKodeMelli());
                    ed_Height.setText(response.body().getPersons().get(0).getHeight());
                    int i=-1;
                    for(String s:arraybloodtype){
                        if(s.equals(response.body().getPersons().get(0).getBloodType())){
                            i++;
                        }
                    }
                    if(i>=0){
                        ed_BloodType.setSelection(i);
                    }
                    tv_Age.setText(response.body().getPersons().get(0).getAge());
                    tv_BirthDate.setText(response.body().getPersons().get(0).getBirthDate());
                    ed_SportsGoal.setText(response.body().getPersons().get(0).getSportsGoal());
                    ed_Introduction.setText(response.body().getPersons().get(0).getIntroduction());
                    ed_Workplace.setText(response.body().getPersons().get(0).getWorkplace());
                    ed_Homeplace.setText(response.body().getPersons().get(0).getHomeplace());
                    ed_Phone.setText(response.body().getPersons().get(0).getPhone());
                    ed_Instagram.setText(response.body().getPersons().get(0).getInstagram());
                    ed_Email.setText(response.body().getPersons().get(0).getEmail());
                    ed_Address.setText(response.body().getPersons().get(0).getAddress());
                    ed_HistoryWorkOut.setText(response.body().getPersons().get(0).getHistoryWorkOut());
                    ed_HistoryComplement.setText(response.body().getPersons().get(0).getHistoryComplement());
                    ed_HistoryEnergiDrugs.setText(response.body().getPersons().get(0).getHistoryEnergiDrugs());
                    ed_HistoryHealthDrugs.setText(response.body().getPersons().get(0).getHistoryHealthDrugs());
                    ed_HistoryFoodAllergies.setText(response.body().getPersons().get(0).getHistoryFoodAllergies());
                    ed_HistoryLimitations.setText(response.body().getPersons().get(0).getHistoryLimitations());
                    ed_HistoryCoach.setText(response.body().getPersons().get(0).getHistoryCoach());
                    ed_Explain.setText(response.body().getPersons().get(0).getExplain());
                    ed_WeightValue.setText(response.body().getPersons().get(0).getWeightValue());
                    ed_Date.setText(nowcalender.getPersianShortDate());
                    if(response.body().getPersons().get(0).getSexGeneric().equals("1")){
                        sm_SexGeneric.setText("مرد");
                        sm_SexGeneric.setChecked(true);
                    }else {
                        sm_SexGeneric.setText("زن");
                        sm_SexGeneric.setChecked(false);
                    }
                }
                @Override
                public void onFailure(Call<RetrofitResponse> call, Throwable t) {
                }
            });

        }

        sm_SexGeneric.setText("مرد");






        addperson.setOnClickListener(v -> {

            edFirstName =  NumberFunctions.EnglishNumber(ed_FirstName.getText().toString());
            edLastName =  NumberFunctions.EnglishNumber(ed_LastName.getText().toString());

            if (sm_SexGeneric.getText().toString().equals("مرد"))
                edgeneric="1";
            else
                edgeneric="0";
            edMobileNumber =  NumberFunctions.EnglishNumber(ed_MobileNumber.getText().toString());
            edKodeMelli =  NumberFunctions.EnglishNumber(ed_KodeMelli.getText().toString());
            edHeight =  NumberFunctions.EnglishNumber(ed_Height.getText().toString());
            edBloodType =  NumberFunctions.EnglishNumber(ed_BloodType.getSelectedItemPosition()+"");
            edAge =  NumberFunctions.EnglishNumber(tv_Age.getText().toString());
            edBirthDate = NumberFunctions.EnglishNumber( tv_BirthDate.getText().toString());
            edSportsGoal =  NumberFunctions.EnglishNumber(ed_SportsGoal.getText().toString());
            edIntroduction =  NumberFunctions.EnglishNumber(ed_Introduction.getText().toString());
            edWorkplace =  NumberFunctions.EnglishNumber(ed_Workplace.getText().toString());
            edHomeplace =  NumberFunctions.EnglishNumber(ed_Homeplace.getText().toString());
            edPhone =  NumberFunctions.EnglishNumber(ed_Phone.getText().toString());
            edInstagram =  NumberFunctions.EnglishNumber(ed_Instagram.getText().toString());
            edEmail =  NumberFunctions.EnglishNumber(ed_Email.getText().toString());
            edAddress =  NumberFunctions.EnglishNumber(ed_Address.getText().toString());
            edHistoryWorkOut =  NumberFunctions.EnglishNumber(ed_HistoryWorkOut.getText().toString());
            edHistoryComplement =  NumberFunctions.EnglishNumber(ed_HistoryComplement.getText().toString());
            edHistoryEnergiDrugs =  NumberFunctions.EnglishNumber(ed_HistoryEnergiDrugs.getText().toString());
            edHistoryHealthDrugs =  NumberFunctions.EnglishNumber(ed_HistoryHealthDrugs.getText().toString());
            edHistoryFoodAllergies =  NumberFunctions.EnglishNumber(ed_HistoryFoodAllergies.getText().toString());
            edHistoryLimitations =  NumberFunctions.EnglishNumber(ed_HistoryLimitations.getText().toString());
            edHistoryCoach =  NumberFunctions.EnglishNumber(ed_HistoryCoach.getText().toString());
            edExplain =  NumberFunctions.EnglishNumber(ed_Explain.getText().toString());
            edWeightValue =  NumberFunctions.EnglishNumber(ed_WeightValue.getText().toString());
            edDate =  NumberFunctions.EnglishNumber(ed_Date.getText().toString());



            Call<RetrofitResponse> call =apiInterface.InsertPerson(
                    "NewPerson",
                    edFirstName,
                    edLastName,
                    edgeneric,
                    edMobileNumber,
                    edKodeMelli,
                    edHeight,
                    edBloodType,
                    edAge,
                    edBirthDate,
                    edSportsGoal,
                    edIntroduction,
                    edWorkplace,
                    edHomeplace,
                    edPhone,
                    edInstagram,
                    edEmail,
                    edAddress,
                    edHistoryWorkOut,
                    edHistoryComplement,
                    edHistoryEnergiDrugs,
                    edHistoryHealthDrugs,
                    edHistoryFoodAllergies,
                    edHistoryLimitations,
                    edHistoryCoach,
                    edExplain,
                    edWeightValue,
                    edDate,
                    personcode);

            call.enqueue(new Callback<RetrofitResponse>() {
                @Override
                public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {


                    if(response.isSuccessful()) {

                        assert response.body() != null;
                        if(createplan.equals("0")){
                            intent = new Intent(CreatepersonActivity.this, PersonDetailActivity.class);
                            intent.putExtra("personcode", response.body().getPersons().get(0).getPersonCode());
                        }else {
                            intent = new Intent(CreatepersonActivity.this, CreateplanActivity.class);
                            intent.putExtra("personcode", response.body().getPersons().get(0).getPersonCode());
                            intent.putExtra("createplan", createplan);
                        }
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<RetrofitResponse> call, Throwable t) {
                    Log.e("test",t.getMessage());

                }
            });



        });


    }



    public void setdate() {
        pickcalenderbirth = new PersianCalendar();
        datePickerDialog = DatePickerDialog.newInstance(
                CreatepersonActivity.this::onDateSet,
                pickcalenderbirth.getPersianYear(),
                pickcalenderbirth.getPersianMonth(),
                pickcalenderbirth.getPersianDay()
        );
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        pickcalenderbirth.setPersianDate(year,monthOfYear,dayOfMonth);
        tv_BirthDate.setText(NumberFunctions.PerisanNumber(pickcalenderbirth.getPersianShortDate()));
        tv_Age.setText(NumberFunctions.PerisanNumber(String.valueOf(nowcalender.getPersianYear()-year)));
    }

}
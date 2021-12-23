package com.kits.couchmanager.activity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.button.MaterialButton;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.kits.couchmanager.BuildConfig;
import com.kits.couchmanager.R;
import com.kits.couchmanager.adapter.Action;
import com.kits.couchmanager.adapter.App;
import com.kits.couchmanager.adapter.PersonAdapter;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonDetailActivity extends AppCompatActivity {

    static String strSDCardPathName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/CouchManager" + "/";

    public APIInterface apiInterface = APIClient.getCleint().create(APIInterface.class);
    ArrayList<String> arraybloodtype=new ArrayList<>() ;
    CharSequence[] options;
    Person person;
    String Personcode;
    Intent intent;
    ArrayList<ChartData> chartDatas;
    Bitmap bitmapview;
    String bitmapviewString;
    String ClassName;
    List<Uri> list_imageUri=new ArrayList<>();
    String ImageOcrPath="";
    Uri photoURI;
    File photoFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);

        Dialog dialog1 = new Dialog(this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog1.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog1.setContentView(R.layout.rep_prog);

        dialog1.show();
        intent();

        Handler handler = new Handler();
        handler.postDelayed(this::init, 100);
        handler.postDelayed(dialog1::dismiss, 1000);

    }


    //************************************************************
    public void intent() {
        Bundle data = getIntent().getExtras();
        assert data != null;
        Personcode = data.getString("personcode");
    }


    public void init() {


        //options = new CharSequence[]{ "Take Photo", "Choose from Gallery","Cancel" };
        options = new CharSequence[]{"Choose from Gallery", "Cancel"};

        Call<RetrofitResponse> call = apiInterface.GetPerson("Getperson","",Personcode);
        call.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(@NotNull Call<RetrofitResponse> call, @NotNull Response<RetrofitResponse> response) {
                assert response.body() != null;
                if (response.isSuccessful()) {
                    person = response.body().getPersons().get(0);
                    Createview();
                }
            }
            @Override
            public void onFailure(@NotNull Call<RetrofitResponse> call, @NotNull Throwable t) {
            }
        });


        Call<RetrofitResponse> call1 = apiInterface.Getweightdata("Getweightdata",Personcode);
        call1.enqueue(new Callback<RetrofitResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NotNull Call<RetrofitResponse> call, @NotNull Response<RetrofitResponse> response) {
                assert response.body() != null;
                if (response.isSuccessful()) {
                    chartDatas = response.body().getChartDatas();
                    chartview();
                }
            }
            @Override
            public void onFailure(@NotNull Call<RetrofitResponse> call, @NotNull Throwable t) {
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void chartview() {


        GraphView graphView = findViewById(R.id.graph1);
        GraphView graphView2 = findViewById(R.id.graph2);
        float max_weight=0;
        float min_weight=0;
        float max_bmi=0;
        float min_bmi=0;
        try {
            max_weight=Float.parseFloat(chartDatas.get(0).getWeightValue());
        }catch (Exception e){ }
        try {
            min_weight=Float.parseFloat(chartDatas.get(0).getWeightValue());
        }catch (Exception e){ }
        try {
            max_bmi=Float.parseFloat(chartDatas.get(0).getBMI());
        }catch (Exception e){ }
        try {
            min_bmi=Float.parseFloat(chartDatas.get(0).getBMI());
        }catch (Exception e){ }


        if(max_weight>0) {
            for (int i = 0; i < chartDatas.size(); i++) {
                if (Float.parseFloat(chartDatas.get(i).getWeightValue()) > max_weight) {
                    max_weight = Float.parseFloat(chartDatas.get(i).getWeightValue());
                }
                if (Float.parseFloat(chartDatas.get(i).getWeightValue()) < min_weight) {
                    min_weight = Float.parseFloat(chartDatas.get(i).getWeightValue());
                }

            }
            graphView.getViewport().setMinX(Integer.parseInt(chartDatas.get(0).getWeeks()));
            graphView.getViewport().setMaxX(Integer.parseInt(chartDatas.get(chartDatas.size()-1).getWeeks())+2);
            graphView.getViewport().setMinY(min_weight-20);
            graphView.getViewport().setMaxY(max_weight+20);
            DataPoint[] dataPoints = new DataPoint[chartDatas.size()];
            for (int i = 0; i < chartDatas.size(); i++) {
                dataPoints[i] = new DataPoint(
                        Double.parseDouble(chartDatas.get(i).getWeeks()),
                        Double.parseDouble(chartDatas.get(i).getWeightValue())
                ); // not sure but I think the second argument should be of type double
            }

            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
            graphView.setTitle("نمودار وزن");
            graphView.getViewport().setYAxisBoundsManual(true);
            graphView.getViewport().setXAxisBoundsManual(true);
            graphView.setTitleColor(R.color.teal_200);
            graphView.setTitleTextSize(80);
            graphView.addSeries(series);

        }

        if(max_bmi>0) {
            for (int i = 0; i < chartDatas.size(); i++) {
                if (Float.parseFloat(chartDatas.get(i).getBMI()) > max_bmi) {
                    max_bmi = Float.parseFloat(chartDatas.get(i).getBMI());
                }
                if (Float.parseFloat(chartDatas.get(i).getBMI()) < min_bmi) {
                    min_bmi = Float.parseFloat(chartDatas.get(i).getBMI());
                }
            }

            graphView2.getViewport().setMinX(Integer.parseInt(chartDatas.get(0).getWeeks()));
            graphView2.getViewport().setMaxX(Integer.parseInt(chartDatas.get(chartDatas.size()-1).getWeeks())+2);
            graphView2.getViewport().setMinY(min_bmi-20);
            graphView2.getViewport().setMaxY(max_bmi+20);
            DataPoint[] dataPoints2 = new DataPoint[chartDatas.size()];
            for (int i = 0; i < chartDatas.size(); i++) {
                dataPoints2[i] = new DataPoint(
                        Double.parseDouble(chartDatas.get(i).getWeeks()),
                        Double.parseDouble(chartDatas.get(i).getBMI())
                ); // not sure but I think the second argument should be of type double
            }

            LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(dataPoints2);
            graphView2.setTitle("نمودار BMI");
            graphView2.getViewport().setYAxisBoundsManual(true);
            graphView2.getViewport().setXAxisBoundsManual(true);
            graphView2.setTitleColor(R.color.teal_200);
            graphView2.setTitleTextSize(80);
            graphView2.addSeries(series2);
        }




    }


    @SuppressLint({"SetTextI18n", "ResourceAsColor", "QueryPermissionsNeeded"})
    public void Createview() {

        arraybloodtype.add("انتخاب");
        arraybloodtype.add("O−");
        arraybloodtype.add("O+");
        arraybloodtype.add("A−");
        arraybloodtype.add("A+");
        arraybloodtype.add("B−");
        arraybloodtype.add("B+");
        arraybloodtype.add("AB-");
        arraybloodtype.add("AB+");

        TextView tv_fullname    = findViewById(R.id.persondetail_fullname);
        TextView tv_KodeMelli   = findViewById(R.id.persondetail_kodemelli);
        TextView tv_weight      = findViewById(R.id.person_weight);
        TextView tv_bmi         = findViewById(R.id.person_bmi);
        TextView tv_SportsGoal  = findViewById(R.id.person_sportgoal);
        TextView tv_age         = findViewById(R.id.person_age);
        TextView tv_height      = findViewById(R.id.person_height);
        TextView tv_mobile      = findViewById(R.id.person_mobile);
        TextView tv_email       = findViewById(R.id.person_email);
        TextView tv_BirthDate   = findViewById(R.id.person_birthdate);
        TextView tv_Instagram   = findViewById(R.id.person_instagram);
        TextView tv_Intro       = findViewById(R.id.person_introduction);
        TextView tv_workplace   = findViewById(R.id.person_workplace);
        TextView tv_homeplace   = findViewById(R.id.person_homeplace);
        TextView tv_blood       = findViewById(R.id.person_bloodtype);
        TextView tv_phone       = findViewById(R.id.person_phone);

        TextView tv_HWorkOut    = findViewById(R.id.person_history_workout);
        TextView tv_HCouch      = findViewById(R.id.person_historycoach);
        TextView tv_HComplement = findViewById(R.id.person_history_complement);
        TextView tv_HEnergiDrug = findViewById(R.id.person_history_energydrug);
        TextView tv_HHealthDrug = findViewById(R.id.person_history_healthdrug);
        TextView tv_HAllergie   = findViewById(R.id.person_history_foodalergi);
        TextView tv_HLimit      = findViewById(R.id.person_history_limit);


        TextView tv_explain     = findViewById(R.id.person_explain);
        TextView tv_address     = findViewById(R.id.person_address);


        CircleImageView CircleimageView =findViewById(R.id.personprofilepic);
        ImageView iv_personfront    = findViewById(R.id.personfront);
        ImageView iv_personback     = findViewById(R.id.personback);
        ImageView iv_personright    = findViewById(R.id.personright);
        ImageView iv_personleft     = findViewById(R.id.personleft);


        Button btn_allplan= findViewById(R.id.person_btn1);
        Button btn_Edit= findViewById(R.id.person_btn2);
        Button btn_weightinsert= findViewById(R.id.person_btn4);
        Button btn_bodydata= findViewById(R.id.person_btn5);
        Button btn_timesheet= findViewById(R.id.person_btntimesheet);

        Button btn_linedata= findViewById(R.id.person_btnline_data);
        Button btn_linehistory= findViewById(R.id.person_btnline_history);
        Button btn_lineimage= findViewById(R.id.person_btnline_image);
        Button btn_linegraph= findViewById(R.id.person_btnline_graph);


        LinearLayoutCompat line_data  = findViewById(R.id.person_line_data);
        LinearLayoutCompat line_history  = findViewById(R.id.person_line_history);
        LinearLayoutCompat line_image  = findViewById(R.id.person_line_image);
        LinearLayoutCompat line_graph  = findViewById(R.id.person_line_graph);


        tv_age.setText(NumberFunctions.PerisanNumber(person.getAge()));
        tv_height.setText(NumberFunctions.PerisanNumber(person.getHeight()));
        tv_fullname.setText(NumberFunctions.PerisanNumber(person.getFirstName()+" "+person.getLastName()));
        tv_mobile.setText(NumberFunctions.PerisanNumber(person.getMobileNumber()));
        tv_email.setText(person.getEmail());
        tv_blood.setText(arraybloodtype.get(Integer.parseInt(person.getBloodType())));
        tv_address.setText(person.getAddress());
        tv_explain.setText(person.getExplain());
        tv_weight.setText(NumberFunctions.PerisanNumber(person.getWeightValue()));
        tv_HWorkOut.setText(NumberFunctions.PerisanNumber(person.getHistoryWorkOut()));
        tv_HComplement.setText(NumberFunctions.PerisanNumber(person.getHistoryComplement()));
        tv_HEnergiDrug.setText(NumberFunctions.PerisanNumber(person.getHistoryEnergiDrugs()));
        tv_HHealthDrug.setText(NumberFunctions.PerisanNumber(person.getHistoryHealthDrugs()));
        tv_HLimit.setText(NumberFunctions.PerisanNumber(person.getHistoryLimitations()));
        tv_HAllergie.setText(NumberFunctions.PerisanNumber(person.getHistoryFoodAllergies()));
        tv_BirthDate.setText(NumberFunctions.PerisanNumber(person.getBirthDate()));
        tv_KodeMelli.setText(NumberFunctions.PerisanNumber(person.getKodeMelli()));
        tv_Intro.setText(NumberFunctions.PerisanNumber(person.getIntroduction()));
        tv_workplace.setText(NumberFunctions.PerisanNumber(person.getWorkplace()));
        tv_homeplace.setText(NumberFunctions.PerisanNumber(person.getHomeplace()));
        tv_SportsGoal.setText(NumberFunctions.PerisanNumber(person.getSportsGoal()));
        tv_bmi.setText(NumberFunctions.PerisanNumber(person.getBMI()));
        tv_Instagram.setText(person.getInstagram());
        tv_phone.setText(NumberFunctions.PerisanNumber(person.getPhone()));
        tv_HCouch.setText(NumberFunctions.PerisanNumber(person.getHistoryCoach()));


        CallImage(CircleimageView   ,"","Person");
        CallImage(iv_personfront    ,"","PersonFront");
        CallImage(iv_personback     ,"","PersonBack");
        CallImage(iv_personright    ,"","PersonRight");
        CallImage(iv_personleft     ,"","PersonLeft");

        line_data.setVisibility(View.VISIBLE);
        line_history.setVisibility(View.GONE);
        line_image.setVisibility(View.GONE);
        line_graph.setVisibility(View.GONE);
        btn_linedata.setBackgroundColor(R.color.mdtp_dark_gray);
        btn_linehistory.setBackgroundColor(R.color.graylight);
        btn_lineimage.setBackgroundColor(R.color.graylight);
        btn_linegraph.setBackgroundColor(R.color.graylight);

        CircleimageView.setOnClickListener(v -> {
            ClassName="Person";


            AlertDialog.Builder builder = new AlertDialog.Builder(PersonDetailActivity.this);
            builder.setTitle("Choose your profile picture");
            builder.setItems(options, (dialog, item) -> {
                if (options[item].equals("Take Photo")) {
                    dispatchTakePictureIntent();
                } else if (options[item].equals("Choose from Gallery")) {
                    intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent , 1);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            });
            builder.show();
        });

        iv_personfront.setOnClickListener(v -> {
            ClassName="PersonFront";


            AlertDialog.Builder builder = new AlertDialog.Builder(PersonDetailActivity.this);
            builder.setTitle("Choose your profile picture");
            builder.setItems(options, (dialog, item) -> {
                if (options[item].equals("Take Photo")) {
                    dispatchTakePictureIntent();
                } else if (options[item].equals("Choose from Gallery")) {
                    intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent , 1);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            });
            builder.show();
        });
        iv_personback.setOnClickListener(v -> {
            ClassName="PersonBack";


            AlertDialog.Builder builder = new AlertDialog.Builder(PersonDetailActivity.this);
            builder.setTitle("Choose your profile picture");
            builder.setItems(options, (dialog, item) -> {
                if (options[item].equals("Take Photo")) {
                    dispatchTakePictureIntent();
                } else if (options[item].equals("Choose from Gallery")) {
                    intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent , 1);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            });
            builder.show();
        });
        iv_personright.setOnClickListener(v -> {
            ClassName="PersonRight";


            AlertDialog.Builder builder = new AlertDialog.Builder(PersonDetailActivity.this);
            builder.setTitle("Choose your profile picture");
            builder.setItems(options, (dialog, item) -> {
                if (options[item].equals("Take Photo")) {
                    dispatchTakePictureIntent();
                } else if (options[item].equals("Choose from Gallery")) {
                    intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent , 1);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            });
            builder.show();
        });
        iv_personleft.setOnClickListener(v -> {
            ClassName="PersonLeft";


            AlertDialog.Builder builder = new AlertDialog.Builder(PersonDetailActivity.this);
            builder.setTitle("Choose your profile picture");
            builder.setItems(options, (dialog, item) -> {
                if (options[item].equals("Take Photo")) {
                    dispatchTakePictureIntent();
                } else if (options[item].equals("Choose from Gallery")) {
                    intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent , 1);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            });
            builder.show();
        });

        tv_Instagram.setOnClickListener(v -> {

            if(!tv_Instagram.getText().toString().equals(""))
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://instagram.com/"+tv_Instagram.getText().toString()));
                    intent.setPackage("com.instagram.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException anfe)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/_u/"+tv_Instagram.getText().toString())));
                }

        });
        tv_mobile.setOnClickListener(v -> {
            if(!tv_mobile.getText().toString().equals("")){
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+tv_mobile.getText().toString()));//change the number
                startActivity(callIntent);
            }
        });
        tv_phone.setOnClickListener(v -> {
            if(!tv_mobile.getText().toString().equals("")){
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+tv_phone.getText().toString()));//change the number
                startActivity(callIntent);
            }
        });

        tv_email.setOnClickListener(v -> {
            if(!tv_email.getText().toString().equals("")){
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"+tv_email.getText().toString())); // only email apps should handle this
                startActivity(intent);
            }
        });

        btn_allplan.setOnClickListener(v -> {
            intent = new Intent(PersonDetailActivity.this, PlanActivity.class);
            intent.putExtra("personname", person.getFirstName()+person.getLastName());
            intent.putExtra("personcode", person.getPersonCode());
            startActivity(intent);
        });
        btn_Edit.setOnClickListener(v -> {
            intent = new Intent(PersonDetailActivity.this, CreatepersonActivity.class);
            intent.putExtra("createplan", "0");
            intent.putExtra("personcode", person.getPersonCode());
            startActivity(intent);
        });
        btn_weightinsert.setOnClickListener(v -> {
            Action action=new Action(PersonDetailActivity.this);
            action.Insertweight(person.getPersonCode(),person.getFirstName()+person.getLastName());
        });
        btn_bodydata.setOnClickListener(v -> {
            Action action=new Action(PersonDetailActivity.this);
            action.bodydatashow(person.getPersonCode(),person.getFirstName()+person.getLastName());
        });

        btn_timesheet.setOnClickListener(v -> {
            Call<RetrofitResponse> call = apiInterface.GetLastPersonPlan("GetLastPersonPlan",Personcode);
            call.enqueue(new Callback<RetrofitResponse>() {
                @Override
                public void onResponse(@NotNull Call<RetrofitResponse> call, @NotNull Response<RetrofitResponse> response) {
                    assert response.body() != null;
                    if (response.isSuccessful()) {
                        Plan plan = response.body().getPlans().get(0);
                        Call<RetrofitResponse> call1 = apiInterface.GetTimeSheet("GetTimeSheet",plan.getPlanCode());
                        //if(plan.getActive().equals("1")){
                        intent = new Intent(PersonDetailActivity.this, TimeSheetActivity.class);
                        intent.putExtra("personcode", Personcode);
                        intent.putExtra("planCode", plan.getPlanCode());
                        intent.putExtra("personname", person.getFirstName()+" "+person.getLastName() );
                        intent.putExtra("freeze", "0");
                        startActivity(intent);
                        //}
//                        else {
//                            call1.enqueue(new Callback<RetrofitResponse>() {
//                                @Override
//                                public void onResponse(@NotNull Call<RetrofitResponse> call, @NotNull Response<RetrofitResponse> response) {
//                                    assert response.body() != null;
//                                    if (response.isSuccessful()) {
//                                        ArrayList<TimeSheet> timeSheets = response.body().getTimeSheets();
//                                        int activecount = 0;
//                                        int unactivecount = 0;
//                                        int frezecount = 0;
//                                        for (TimeSheet ts : timeSheets) {
//                                            if (!ts.getState().equals("1")) {
//                                                activecount++;
//                                            }
//                                        }
//
//                                        if (activecount < (Integer.parseInt(plan.getDayPeriod()) * Integer.parseInt(plan.getWeekPeriod()))) {
//
//                                            for (TimeSheet ts : timeSheets) {
//                                                if (ts.getState().equals("0")) {
//                                                    unactivecount++;
//                                                }
//                                                if (ts.getFreeze().equals("1")) {
//                                                    frezecount++;
//                                                }
//                                            }
//                                            if (unactivecount == frezecount) {
//                                                Toast.makeText(PersonDetailActivity.this, "عضویت اتمام یافته است", Toast.LENGTH_SHORT).show();
//                                            } else {
//                                                intent = new Intent(PersonDetailActivity.this, TimeSheetActivity.class);
//                                                intent.putExtra("personcode", Personcode);
//                                                intent.putExtra("planCode", plan.getPlanCode());
//                                                intent.putExtra("freeze", "1");
//                                                startActivity(intent);
//                                            }
//
//                                        } else {
//                                            Toast.makeText(PersonDetailActivity.this, "عضویت اتمام یافته است", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(@NotNull Call<RetrofitResponse> call, @NotNull Throwable t) {
//                                }
//                            });
//
//                            new android.app.AlertDialog.Builder(PersonDetailActivity.this)
//                                    .setTitle("اتمام عضویت")
//                                    .setMessage("برنامه جدید ایجاد شود؟")
//                                    .setPositiveButton("بله", (dialogInterface, i) -> {
//                                        intent = new Intent(PersonDetailActivity.this, CreateplanActivity.class);
//                                        intent.putExtra("personcode", person.getPersonCode());
//                                        intent.putExtra("personname", person.getFirstName()+" "+person.getLastName() );
//                                        startActivity(intent);
//                                    })
//                                    .setNegativeButton("خیر", (dialogInterface, i) -> {
//
//                                    })
//                                    .show();
//                        }
//
//

                    }
                }
                @Override
                public void onFailure(@NotNull Call<RetrofitResponse> call, @NotNull Throwable t) {
                    new android.app.AlertDialog.Builder(PersonDetailActivity.this)
                            .setTitle("عدم وجود برنامه")
                            .setMessage("برنامه جدید ایجاد شود؟")
                            .setPositiveButton("بله", (dialogInterface, i) -> {
                                intent = new Intent(PersonDetailActivity.this, CreateplanActivity.class);
                                intent.putExtra("personcode", person.getPersonCode());
                                intent.putExtra("personname", person.getFirstName()+" "+person.getLastName() );
                                startActivity(intent);
                            })
                            .setNegativeButton("خیر", (dialogInterface, i) -> {

                            })
                            .show();
                }
            });

        });

        btn_linedata.setOnClickListener(v -> {
            line_data.setVisibility(View.VISIBLE);
            line_history.setVisibility(View.GONE);
            line_image.setVisibility(View.GONE);
            line_graph.setVisibility(View.GONE);
            btn_linedata.setBackgroundColor(R.color.mdtp_dark_gray);
            btn_linehistory.setBackgroundColor(R.color.graylight);
            btn_lineimage.setBackgroundColor(R.color.graylight);
            btn_linegraph.setBackgroundColor(R.color.graylight);
        });
        btn_linehistory.setOnClickListener(v -> {
            line_data.setVisibility(View.GONE);
            line_history.setVisibility(View.VISIBLE);
            line_image.setVisibility(View.GONE);
            line_graph.setVisibility(View.GONE);
            btn_linedata.setBackgroundColor(R.color.graylight);
            btn_linehistory.setBackgroundColor(R.color.mdtp_dark_gray);
            btn_lineimage.setBackgroundColor(R.color.graylight);
            btn_linegraph.setBackgroundColor(R.color.graylight);
        });
        btn_lineimage.setOnClickListener(v -> {
            line_data.setVisibility(View.GONE);
            line_history.setVisibility(View.GONE);
            line_image.setVisibility(View.VISIBLE);
            line_graph.setVisibility(View.GONE);
            btn_linedata.setBackgroundColor(R.color.graylight);
            btn_linehistory.setBackgroundColor(R.color.graylight);
            btn_lineimage.setBackgroundColor(R.color.mdtp_dark_gray);
            btn_linegraph.setBackgroundColor(R.color.graylight);
        });
        btn_linegraph.setOnClickListener(v -> {
            line_data.setVisibility(View.GONE);
            line_history.setVisibility(View.GONE);
            line_image.setVisibility(View.GONE);
            line_graph.setVisibility(View.VISIBLE);
            btn_linedata.setBackgroundColor(R.color.graylight);
            btn_linehistory.setBackgroundColor(R.color.graylight);
            btn_lineimage.setBackgroundColor(R.color.graylight);
            btn_linegraph.setBackgroundColor(R.color.mdtp_dark_gray);
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                assert data != null;
                if(data.getClipData() != null) {
                    int count = data.getClipData().getItemCount();
                    for(int i = 0; i < count; i++) {
                        list_imageUri.add(data.getClipData().getItemAt(i).getUri());
                    }
                    ImageView imageView2 = new ImageView(getApplicationContext());
                    imageView2.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
                    imageView2.setImageURI(list_imageUri.get(0));

                    bitmapview=loadBitmapFromView(imageView2);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmapview.compress(Bitmap.CompressFormat.JPEG, 30, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    bitmapviewString= Base64.encodeToString(byteArray, Base64.DEFAULT);
                    Log.e("test",bitmapviewString);
                    SendImage();
                } else {

                    ImageView imageView2 = new ImageView(getApplicationContext());
                    imageView2.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
                    imageView2.setImageURI(data.getData());

                    bitmapview=loadBitmapFromView(imageView2);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmapview.compress(Bitmap.CompressFormat.JPEG, 30, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    bitmapviewString= Base64.encodeToString(byteArray, Base64.DEFAULT);
                    Log.e("test",bitmapviewString);
                    SendImage();
                }
            } else {
                Toast.makeText(this, "فایلی انتخاب نشد", Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode == 2 ){
            ImageView imageView2 = new ImageView(getApplicationContext());
            imageView2.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT));
            imageView2.setImageURI(photoURI);

            bitmapview=loadBitmapFromView(imageView2);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmapview.compress(Bitmap.CompressFormat.JPEG, 30, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            bitmapviewString= Base64.encodeToString(byteArray, Base64.DEFAULT);
            SendImage();
        }
    }
    public Bitmap loadBitmapFromView(View v) {
        v.measure(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        Bitmap b = Bitmap.createBitmap(v.getMeasuredWidth(), v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        v.draw(c);
        return b;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            photoFile= null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.e("intent_exception", ex.getMessage());
            }
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this, "com.kits.couchmanager.provider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 2);
            }
        }
    }
    private File createImageFile() throws IOException {

        File folder = new File(strSDCardPathName);
        try
        {
            if (!folder.exists()) {
                folder.mkdir();
            }
        }catch(Exception ignored){}

        String imageFileName = "profile";
        File storageDir = new File(strSDCardPathName);
        File image = File.createTempFile(imageFileName, ".jpg",storageDir);
        ImageOcrPath=image.getAbsolutePath();
        return image;
    }


    private void SendImage() {

        PersianCalendar calendar = new PersianCalendar();
        calendar.setDelimiter("_");
        Call<RetrofitResponse> call = apiInterface.getImagetest(
                "getImagetest"
                , person.getPersonCode() + "_" + ClassName+ "_" +calendar.getPersianShortDate()
                , bitmapviewString
                , ClassName
                , ""
                , person.getPersonCode()
                , calendar.getPersianShortDate()
        );
        call.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {

                if(response.isSuccessful()){
                    startActivity(getIntent());
                    finish();
                }
            }
            @Override
            public void onFailure(Call<RetrofitResponse> call, Throwable t) {

            }
        });
    }


    public void CallImage(View view,String IType,String ClassName) {

        Call<RetrofitResponse> call2 = apiInterface.GetImage(
                "getImage",
                person.getPersonCode(),
                IType,
                ClassName);
        call2.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call2, Response<RetrofitResponse> response) {
                if (response.isSuccessful()) {

                    assert response.body() != null;
                    try {
                        if(!response.body().getText().equals("no_photo")) {
                            Glide.with(view)
                                    .asBitmap()
                                    .load(Base64.decode(response.body().getText(), Base64.DEFAULT))
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .fitCenter()
                                    .into((ImageView) view);
                        }
                    } catch (Exception e) {
                        e.getMessage();
                    }


                }
            }
            @Override
            public void onFailure(Call<RetrofitResponse> call2, Throwable t) {
                Log.e("onFailure",""+t.toString());
            }
        });

    }

    @Override
    public void onRestart() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
        super.onRestart();

    }
}
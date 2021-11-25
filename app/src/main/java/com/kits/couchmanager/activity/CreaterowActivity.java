package com.kits.couchmanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.kits.couchmanager.R;
import com.kits.couchmanager.adapter.App;
import com.kits.couchmanager.adapter.PlanRowAdapter;
import com.kits.couchmanager.model.Item;
import com.kits.couchmanager.model.NumberFunctions;
import com.kits.couchmanager.model.Person;
import com.kits.couchmanager.model.Plan;
import com.kits.couchmanager.model.RetrofitResponse;
import com.kits.couchmanager.webService.APIClient;
import com.kits.couchmanager.webService.APIInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreaterowActivity extends AppCompatActivity {
    public APIInterface apiInterface = APIClient.getCleint().create(APIInterface.class);
    Call<RetrofitResponse> NewPlanRow;

    TextView tvplantype;
    EditText edpexplain;
    ArrayAdapter spinner_adapter;
    ArrayList<Plan> plans;

    LinearLayoutCompat create_row_newline;
    LinearLayoutCompat createlinedayinweek;
    LinearLayoutCompat createlinefilter1;
    LinearLayoutCompat createlinefilter2;
    LinearLayoutCompat createlinename;
    LinearLayoutCompat createlineprogram1;
    LinearLayoutCompat createlineprogram2;
    LinearLayoutCompat createlinepower;
    LinearLayoutCompat createlineexplain;

    Spinner spinnerdayinweek;

    Spinner spinnerfilter1;
    Spinner spinnerfilter2;
    Spinner spinnername;

    Spinner spinnerprogram1_r;
    Spinner spinnerprogram1_d;
    Spinner spinnerprogram2_r;
    Spinner spinnerprogram2_d;

    Spinner spinnerpowerpercent;

    Button btnshow;
    Button btncreate;

    Intent intent;
    RecyclerView recyclerView;

    ArrayList<String> Data_spinnerdayinweek =new ArrayList<>() ;
    ArrayList<String> Data_spinnerfilter1  =new ArrayList<>() ;
    ArrayList<String> Data_spinnerfilter2 =new ArrayList<>() ;
    ArrayList<String> Data_spinnername =new ArrayList<>() ;
    ArrayList<String> Data_spinnerprogram1_r=new ArrayList<>() ;
    ArrayList<String> Data_spinnerprogram1_d=new ArrayList<>() ;
    ArrayList<String> Data_spinnerprogram2_r=new ArrayList<>() ;
    ArrayList<String> Data_spinnerprogram2_d=new ArrayList<>() ;
    ArrayList<String> Data_spinnerpowerpercent=new ArrayList<>() ;


    ArrayList<Item> items;
    ArrayList<Item> items_test;
    ArrayList<Item> items_final;


    String filter1="" ;
    String filter2="" ;
    String dayinweek ;
    String itemcode ;
    String plancode ;
    String plantype ;
    String dayperiod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createrow);

        init();
    }
//--------------------------------------------------------------------------------


    public void init() {
        intent();
        findviewid();

        Call<RetrofitResponse> call1 = apiInterface.GetItem("GetItem",plantype,"");
        call1.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(@NotNull Call<RetrofitResponse> call, @NotNull Response<RetrofitResponse> response) {
                assert response.body() != null;
                if (response.isSuccessful()) {
                    items =response.body().getItems();

                    firstinitial();
                }
            }
            @Override
            public void onFailure(@NotNull Call<RetrofitResponse> call, @NotNull Throwable t) {
            }
        });
        Stringdata();
        actionalfunction();


        btnshow.setOnClickListener(v -> {
            btnshow.setVisibility(View.GONE);
            create_row_newline.setVisibility(View.VISIBLE);
        });



        btncreate.setOnClickListener(v -> {

            for (Item item: items){
                if(item.getItemName().equals(Data_spinnername.get(spinnername.getSelectedItemPosition()))){
                    itemcode=item.getItemCode();
                }
            }

            switch (plantype) {
                case "1":

                    NewPlanRow = apiInterface.NewPlanRow(
                            "NewPlanRow",
                            plancode,
                            itemcode,
                            Data_spinnerprogram1_r.get(spinnerprogram1_r.getSelectedItemPosition()),
                            Data_spinnerprogram1_d.get(spinnerprogram1_d.getSelectedItemPosition()),
                            Data_spinnerdayinweek.get(spinnerdayinweek.getSelectedItemPosition()),
                            Data_spinnerpowerpercent.get(spinnerpowerpercent.getSelectedItemPosition()),
                            ""+edpexplain.getText().toString());

                    break;
                case "2":
                case "3":
                    NewPlanRow = apiInterface.NewPlanRow(
                            "NewPlanRow",
                            plancode,
                            itemcode,
                            Data_spinnerprogram2_r.get(spinnerprogram2_r.getSelectedItemPosition()),
                            Data_spinnerprogram2_d.get(spinnerprogram2_d.getSelectedItemPosition()),
                            Data_spinnerdayinweek.get(spinnerdayinweek.getSelectedItemPosition()),
                            "%%",
                            ""+edpexplain.getText().toString());

                    break;
            }

            NewPlanRow.enqueue(new Callback<RetrofitResponse>() {
                @Override
                public void onResponse(@NotNull Call<RetrofitResponse> call, @NotNull Response<RetrofitResponse> response) {
                    assert response.body() != null;
                    if (response.isSuccessful()) {
                        plans =response.body().getPlans();
                        if(Integer.parseInt(plans.get(0).getPlanRowCode())>0){
                            Log.e("test_plans",""+plans.get(0).getPlanRowCode());
                            finish();
                        }
                    }

                }
                @Override
                public void onFailure(@NotNull Call<RetrofitResponse> call, @NotNull Throwable t) {
                }
            });


        });


    }






    public void intent() {
        Bundle data = getIntent().getExtras();
        assert data != null;
        plancode = data.getString("plancode");
        plantype = data.getString("plantype");
        dayperiod = data.getString("dayperiod");

    }


    public void callitemname() {
        Data_spinnername.clear();
        Data_spinnername.add("انتخاب کنید");
        for (Item item:items){
            Data_spinnername.add(item.getItemName());
        }
        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Data_spinnername);
        spinnername.setAdapter(spinner_adapter);
        spinnername.setSelection(0);
    }


    public void callitem1() {

        String searchtarget="";
        if(!filter1.equals("")) {
            searchtarget = searchtarget +" And ItemType1 like '%" + filter1 + "%' ";
        }
        if(!filter2.equals("")) {
            searchtarget = searchtarget +" And ItemType2 like '%"+filter2+"%' ";
        }

        Log.e("test0",""+searchtarget);
        Call<RetrofitResponse> call1 = apiInterface.GetItem("GetItem",plantype,searchtarget);
        call1.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(@NotNull Call<RetrofitResponse> call, @NotNull Response<RetrofitResponse> response) {
                assert response.body() != null;
                if (response.isSuccessful()) {
                    items =response.body().getItems();

                    callitemname();
                }

            }
            @Override
            public void onFailure(@NotNull Call<RetrofitResponse> call, @NotNull Throwable t) {
            }
        });


    }


    public void findviewid() {
        tvplantype =findViewById(R.id.create_row_plantype);

        create_row_newline = findViewById(R.id.create_row_newline);
        createlinedayinweek = findViewById(R.id.createlinedayinweek);
        createlinefilter1 = findViewById(R.id.createlinefilter1);
        createlinefilter2 = findViewById(R.id.createlinefilter2);
        createlinename = findViewById(R.id.createlinename);
        createlineprogram1 = findViewById(R.id.createlineprogram1);
        createlineprogram2 = findViewById(R.id.createlineprogram2);
        createlinepower = findViewById(R.id.createlinepower);
        createlineexplain = findViewById(R.id.createlineexplain);

        spinnerdayinweek = findViewById(R.id.create_row_dayinweek);
        spinnerfilter1 = findViewById(R.id.create_row_filtertype1);
        spinnerfilter2 = findViewById(R.id.create_row_filtertype2);
        spinnername = findViewById(R.id.create_row_itemname);
        spinnerprogram1_r = findViewById(R.id.create_row_reapet1);
        spinnerprogram1_d = findViewById(R.id.create_row_duration1);
        spinnerprogram2_r = findViewById(R.id.create_row_reapet2);
        spinnerprogram2_d = findViewById(R.id.create_row_duration2);
        spinnerpowerpercent = findViewById(R.id.create_row_powerpercent);
        edpexplain = findViewById(R.id.create_row_explain);

        btnshow = findViewById(R.id.create_plan_btn_showline);
        btncreate = findViewById(R.id.create_plan_btn);
        recyclerView = findViewById(R.id.rcplanrowbytype);

    }

    public void firstinitial() {


        switch (plantype) {
            case "1":
                tvplantype.setText("ورزشی");

                Data_spinnername.add("انتخاب کنید");
                createlineprogram2.setVisibility(View.GONE);
                for (Item item:items){
                    Data_spinnerfilter1.add(item.getItemType1());
                    Data_spinnerfilter2.add(item.getItemType2());
                    Data_spinnername.add(item.getItemName());
                }

                Data_spinnerfilter1=new ArrayList<String>(new HashSet<>(Data_spinnerfilter1));
                Data_spinnerfilter2=new ArrayList<String>(new HashSet<>(Data_spinnerfilter2));

                Data_spinnerfilter1.add(0,"انتخاب کنید");
                Data_spinnerfilter2.add(0,"انتخاب کنید");

                break;
            case "2":
                tvplantype.setText("مکمل");
                createlineprogram1.setVisibility(View.GONE);
                createlinefilter2.setVisibility(View.GONE);
                createlinepower.setVisibility(View.GONE);
                for (Item item:items){
                    Data_spinnerfilter1.add(item.getItemType1());
                    Data_spinnername.add(item.getItemName());
                }
                Data_spinnerfilter1=new ArrayList<String>(new HashSet<>(Data_spinnerfilter1));
                Data_spinnerfilter1.add(0,"انتخاب کنید");
                break;
            case "3":
                tvplantype.setText("غذایی");
                createlineprogram1.setVisibility(View.GONE);
                createlinefilter2.setVisibility(View.GONE);
                createlinepower.setVisibility(View.GONE);
                for (Item item:items){
                    Data_spinnerfilter1.add(item.getItemType1());
                    Data_spinnername.add(item.getItemName());
                }
                Data_spinnerfilter1=new ArrayList<String>(new HashSet<>(Data_spinnerfilter1));
                Data_spinnerfilter1.add(0,"انتخاب کنید");
                break;
        }

        Spinnerfunction();

    }


    public void Stringdata() {
        Data_spinnerprogram1_r.add("انتخاب");
        Data_spinnerprogram1_r.add("ثانیه");
        Data_spinnerprogram1_r.add("دقیقه");
        Data_spinnerprogram1_r.add("تکرار");

        Data_spinnerprogram1_d.add("انتخاب");
        Data_spinnerprogram1_d.add("1");
        Data_spinnerprogram1_d.add("2");
        Data_spinnerprogram1_d.add("3");
        Data_spinnerprogram1_d.add("4");
        Data_spinnerprogram1_d.add("5");
        Data_spinnerprogram1_d.add("6");
        Data_spinnerprogram1_d.add("7");
        Data_spinnerprogram1_d.add("8");
        Data_spinnerprogram1_d.add("10");
        Data_spinnerprogram1_d.add("11");
        Data_spinnerprogram1_d.add("12");
        Data_spinnerprogram1_d.add("13");
        Data_spinnerprogram1_d.add("14");
        Data_spinnerprogram1_d.add("15");
        Data_spinnerprogram1_d.add("16");
        Data_spinnerprogram1_d.add("17");
        Data_spinnerprogram1_d.add("18");
        Data_spinnerprogram1_d.add("19");
        Data_spinnerprogram1_d.add("20");
        Data_spinnerprogram1_d.add("21");
        Data_spinnerprogram1_d.add("30");
        Data_spinnerprogram1_d.add("40");
        Data_spinnerprogram1_d.add("50");
        Data_spinnerprogram1_d.add("60");
        Data_spinnerprogram1_d.add("70");
        Data_spinnerprogram1_d.add("80");
        Data_spinnerprogram1_d.add("90");
        Data_spinnerprogram1_d.add("100");


        Data_spinnerprogram2_r.add("انتخاب");
        Data_spinnerprogram2_r.add("اسکوپ");
        Data_spinnerprogram2_r.add("قرص");
        Data_spinnerprogram2_r.add("عدد");
        Data_spinnerprogram2_r.add("قاشق");


        Data_spinnerprogram2_d.add("انتخاب");
        Data_spinnerprogram2_d.add("1");
        Data_spinnerprogram2_d.add("2");
        Data_spinnerprogram2_d.add("3");
        Data_spinnerprogram2_d.add("4");
        Data_spinnerprogram2_d.add("5");


        Data_spinnerpowerpercent.add("انتخاب ");
        Data_spinnerpowerpercent.add("10");
        Data_spinnerpowerpercent.add("20");
        Data_spinnerpowerpercent.add("30");
        Data_spinnerpowerpercent.add("40");
        Data_spinnerpowerpercent.add("50");
        Data_spinnerpowerpercent.add("60");
        Data_spinnerpowerpercent.add("70");
        Data_spinnerpowerpercent.add("80");
        Data_spinnerpowerpercent.add("90");
        Data_spinnerpowerpercent.add("100");


        int i;
        Data_spinnerdayinweek.add("انتخاب روز");
        for(i=1;i<Integer.parseInt(dayperiod)+1;i++){
            Data_spinnerdayinweek.add(i+"");
        }

    }


    public void actionalfunction() {

        switch (plantype) {
            case "1":
                spinnerfilter1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if(position!=0){
                            filter1=Data_spinnerfilter1.get(position);
                            filter1=filter1.replace("\r\n","");
                        }
                        callitem1();

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {            }
                });
                spinnerfilter2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position!=0){
                            filter2=Data_spinnerfilter2.get(position);
                            filter2=filter2.replace("\r\n","");

                        }
                        callitem1();

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {            }
                });
                break;
            case "2":
            case "3":
                spinnerfilter1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position!=0){
                            filter1=Data_spinnerfilter1.get(position);
                            filter1=filter1.replace("\r\n","");

                        }
                        callitem1();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {            }
                });
                break;
        }

        spinnerdayinweek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){
                    btnshow.setVisibility(View.GONE);
                }else {
                    btnshow.setVisibility(View.VISIBLE);

                }
                create_row_newline.setVisibility(View.GONE);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }







    public void Spinnerfunction() {

        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Data_spinnerdayinweek);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerdayinweek.setAdapter(spinner_adapter);
        spinnerdayinweek.setSelection(0);
        spinner_adapter = new ArrayAdapter<>(CreaterowActivity.this,android.R.layout.simple_spinner_item, Data_spinnerfilter1);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfilter1.setAdapter(spinner_adapter);
        spinnerfilter1.setSelection(0);
        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Data_spinnerfilter2);
        spinnerfilter2.setAdapter(spinner_adapter);
        spinnerfilter2.setSelection(0);
        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Data_spinnername);
        spinnername.setAdapter(spinner_adapter);
        spinnername.setSelection(0);
        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Data_spinnerpowerpercent);
        spinnerpowerpercent.setAdapter(spinner_adapter);
        spinnerpowerpercent.setSelection(0);
        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Data_spinnerprogram1_r);
        spinnerprogram1_r.setAdapter(spinner_adapter);
        spinnerprogram1_r.setSelection(0);
        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Data_spinnerprogram1_d);
        spinnerprogram1_d.setAdapter(spinner_adapter);
        spinnerprogram1_d.setSelection(0);
        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Data_spinnerprogram2_r);
        spinnerprogram2_r.setAdapter(spinner_adapter);
        spinnerprogram2_r.setSelection(0);
        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, Data_spinnerprogram2_d);
        spinnerprogram2_d.setAdapter(spinner_adapter);
        spinnerprogram2_d.setSelection(0);

    }


}
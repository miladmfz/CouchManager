package com.kits.couchmanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kits.couchmanager.R;
import com.kits.couchmanager.adapter.App;
import com.kits.couchmanager.adapter.PlanAdapter;
import com.kits.couchmanager.model.NumberFunctions;
import com.kits.couchmanager.model.Plan;
import com.kits.couchmanager.model.RetrofitResponse;
import com.kits.couchmanager.webService.APIClient;
import com.kits.couchmanager.webService.APIInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanActivity extends AppCompatActivity {

    public APIInterface apiInterface = APIClient.getCleint().create(APIInterface.class);
    Call<RetrofitResponse> call;
    Handler handler= new Handler();

    RecyclerView recyclerView;
    EditText editText;
    Button button;
    ArrayList<Plan> plans;
    Intent intent;

    String Personcode;
    String personname;
    String searchtarget="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        intent();
        init();

    }


    //*******************************************************************************

    public void intent() {
        Bundle data = getIntent().getExtras();
        assert data != null;
        Personcode = data.getString("personcode");
        personname = data.getString("personname");

    }

    public void init() {

        recyclerView=findViewById(R.id.rcplan);
        editText=findViewById(R.id.searchplan);
        button=findViewById(R.id.addplan);

        Getplan();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(() -> {
                    searchtarget = NumberFunctions.EnglishNumber(s.toString());
                    searchtarget = searchtarget.replaceAll(" ","%");
                    Getplan();
                },1000);
                handler.removeCallbacks(null);
            }
        });


        button.setOnClickListener(v -> {
            if(Integer.parseInt(Personcode)>0){
                intent = new Intent(PlanActivity.this, CreateplanActivity.class);
            }else {
                intent = new Intent(PlanActivity.this, PersonActivity.class);
            }
            intent.putExtra("createplan", "1");
            intent.putExtra("personcode", Personcode);
            intent.putExtra("personname", personname);
            startActivity(intent);

        });

    }


    public void Getplan() {

        call = apiInterface.GetPlan("GetPlan",searchtarget,Personcode,"0");
        call.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(@NotNull Call<RetrofitResponse> call, @NotNull Response<RetrofitResponse> response) {
                assert response.body() != null;
                plans=response.body().getPlans();
                PlanAdapter planAdapter= new PlanAdapter(plans, PlanActivity.this);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(App.getContext(), 1);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(planAdapter);
                recyclerView.setItemAnimator(new FlipInTopXAnimator());
            }
            @Override
            public void onFailure(@NotNull Call<RetrofitResponse> call, @NotNull Throwable t) {
            }
        });

    }

}
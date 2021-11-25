package com.kits.couchmanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kits.couchmanager.R;
import com.kits.couchmanager.adapter.App;
import com.kits.couchmanager.adapter.PlanRowAdapter;
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

public class PlanDetailActivity extends AppCompatActivity {

    public APIInterface apiInterface = APIClient.getCleint().create(APIInterface.class);
    Call<RetrofitResponse> call;

    RecyclerView recyclerView;
    Button btnadd;
    ArrayList<Plan> plans;
    Plan plan;
    Intent intent;

    String plancode="0";
    String dayperiod="0";
    String plantype="0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);
        intent();
        init();

    }


    //***************************************************************************************

    public void intent() {
        Bundle data = getIntent().getExtras();
        assert data != null;
        plancode = data.getString("plancode");
        plantype = data.getString("plantype");
        dayperiod = data.getString("dayperiod");

    }

    public void init() {


        recyclerView=findViewById(R.id.rcplandetail);
        btnadd=findViewById(R.id.btnplandetailaddrow);
        Getplandetail();

        btnadd.setOnClickListener(v -> {
            intent = new Intent(this, CreaterowActivity.class);
            intent.putExtra("plancode", plancode);
            intent.putExtra("plantype", plantype);
            intent.putExtra("dayperiod", dayperiod);
            startActivity(intent);
        });
    }

    public void Getplandetail() {

        call = apiInterface.GetPlanbycode("GetPlanbyCode", plancode);
        call = apiInterface.GetPlanRow("GetPlan",plantype,"","0",plancode);
        call.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(@NotNull Call<RetrofitResponse> call, @NotNull Response<RetrofitResponse> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    plans= response.body().getPlans();
                    PlanRowAdapter planRowAdapter= new PlanRowAdapter(plans, PlanDetailActivity.this);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(App.getContext(), 1);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(planRowAdapter);
                    recyclerView.setItemAnimator(new FlipInTopXAnimator());
                }
            }
            @Override
            public void onFailure(@NotNull Call<RetrofitResponse> call, @NotNull Throwable t) {
            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
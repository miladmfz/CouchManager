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

import com.kits.couchmanager.R;
import com.kits.couchmanager.adapter.App;
import com.kits.couchmanager.adapter.ItemAdapter;
import com.kits.couchmanager.model.Item;
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

public class CategoryActivity extends AppCompatActivity {

    public APIInterface apiInterface = APIClient.getCleint().create(APIInterface.class);
    Call<RetrofitResponse> call;

    RecyclerView recyclerView;
    EditText editText;
    Button button;

    ArrayList<Item> Items;
    Item Item;
    Intent intent;
    String searchtarget="";
    Handler handler= new Handler();
    String itemtype="1";
    String Personcode="0";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        intent();
        init();

    }


    //**************************************************************

    public void intent() {
        Bundle data = getIntent().getExtras();
        assert data != null;
        itemtype = data.getString("itemtype");
    }

    public void init() {

        recyclerView=findViewById(R.id.recyclerView_category);
        editText=findViewById(R.id.searchcategory);
        button=findViewById(R.id.addcategory);

        Getcategory();

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
                    Getcategory();
                },1000);
                handler.removeCallbacks(null);
            }
        });


        button.setOnClickListener(v -> {
            intent = new Intent(CategoryActivity.this, CreateitemActivity.class);
            intent.putExtra("itemtype", itemtype);
            startActivity(intent);

        });

    }

    public void Getcategory() {

                call = apiInterface.GetItem("GetItem",itemtype,"");
                call.enqueue(new Callback<RetrofitResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<RetrofitResponse> call, @NotNull Response<RetrofitResponse> response) {
                        assert response.body() != null;
                        Items= response.body().getItems();
                        ItemAdapter adapter= new ItemAdapter(Items,CategoryActivity.this);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(App.getContext(), 1);
                        recyclerView.setLayoutManager(gridLayoutManager);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setItemAnimator(new FlipInTopXAnimator());
                    }
                    @Override
                    public void onFailure(@NotNull Call<RetrofitResponse> call, @NotNull Throwable t) {
                    }
                });

    }


}
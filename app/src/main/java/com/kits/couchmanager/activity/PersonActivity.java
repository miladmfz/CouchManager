package com.kits.couchmanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.kits.couchmanager.R;
import com.kits.couchmanager.adapter.App;
import com.kits.couchmanager.adapter.PersonAdapter;
import com.kits.couchmanager.model.NumberFunctions;
import com.kits.couchmanager.model.Person;
import com.kits.couchmanager.model.RetrofitResponse;
import com.kits.couchmanager.webService.APIClient;
import com.kits.couchmanager.webService.APIInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonActivity extends AppCompatActivity {

    public APIInterface apiInterface = APIClient.getCleint().create(APIInterface.class);
    Handler handler;

    RecyclerView rcperson;
    EditText editText;
    Button button;
    ArrayList<Person> persons;
    Person person;
    Intent intent;
    String searchtarget="";
    String createplan;
    String plantype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        intent();



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


    //***************************************************************************************




    public void intent() {
        Bundle data = getIntent().getExtras();
        assert data != null;
        createplan = data.getString("createplan");
        plantype = data.getString("plantype");

    }



    public void init() {

        handler= new Handler();
        rcperson=findViewById(R.id.rcperson);
        editText=findViewById(R.id.searchperson);
        button=findViewById(R.id.addperson);

        Getperson();

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
                    Getperson();
                },1000);
                handler.removeCallbacks(null);
            }
        });


        button.setOnClickListener(v -> {
            intent = new Intent(PersonActivity.this, CreatepersonActivity.class);
            intent.putExtra("createplan", createplan);
            intent.putExtra("personcode", "0");
            startActivity(intent);

        });



    }


    public void Getperson() {
        Log.e("1","0");
        Log.e("1",searchtarget);

        Call<RetrofitResponse> call = apiInterface.GetPerson("Getperson",searchtarget,"0");
        call.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(@NotNull Call<RetrofitResponse> call, @NotNull Response<RetrofitResponse> response) {
                assert response.body() != null;
                if (response.isSuccessful()) {
                    Log.e("1","1");
                    persons = response.body().getPersons();
                    Log.e("test1",persons.size()+"");
                    PersonAdapter adapter = new PersonAdapter(persons,createplan,plantype, PersonActivity.this);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(App.getContext(), 1);
                    rcperson.setLayoutManager(gridLayoutManager);
                    rcperson.setAdapter(adapter);
                    rcperson.setItemAnimator(new FlipInTopXAnimator());
                }
            }
            @Override
            public void onFailure(@NotNull Call<RetrofitResponse> call, @NotNull Throwable t) {
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
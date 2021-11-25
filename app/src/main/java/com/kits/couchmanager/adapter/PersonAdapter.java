package com.kits.couchmanager.adapter;


import android.annotation.SuppressLint;
import android.content.Context;

import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kits.couchmanager.R;
import com.kits.couchmanager.activity.CreateplanActivity;
import com.kits.couchmanager.activity.PersonDetailActivity;
import com.kits.couchmanager.model.NumberFunctions;
import com.kits.couchmanager.model.Person;
import com.kits.couchmanager.model.RetrofitResponse;
import com.kits.couchmanager.webService.APIClient;
import com.kits.couchmanager.webService.APIInterface;


import java.text.DecimalFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.GoodViewHolder>{
    public APIInterface apiInterface = APIClient.getCleint().create(APIInterface.class);

    DecimalFormat decimalFormat= new DecimalFormat("0,000");
    private List<Person> persons;
    Intent intent;
    Person person;
    private final Context mContext;
    String createplan;
    String plantype;
    public PersonAdapter(List<Person> personList,String createplan,String plantype, Context context)
    {
        this.mContext=context;
        this.persons = personList;
        this.createplan = createplan;
        this.plantype = plantype;
    }

    @NonNull
    @Override
    public GoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personcardview, parent, false);
        return new GoodViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final GoodViewHolder holder, final int position)
    {
        person=persons.get(position);

        holder.textView1.setText(person.getFirstName()+" "+person.getLastName());
        holder.textView2.setText(NumberFunctions.PerisanNumber(person.getMobileNumber()));
        holder.textView3.setText(NumberFunctions.PerisanNumber(person.getKodeMelli()));

        Call<RetrofitResponse> call2 = apiInterface.GetImage(
                "getImage",
                person.getPersonCode(),
                "",
                "Person");
        call2.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call2, Response<RetrofitResponse> response) {
                if (response.isSuccessful()) {

                    assert response.body() != null;
                    try {
                        if(!response.body().getText().equals("no_photo")) {
                            Glide.with(holder.imageView)
                                    .asBitmap()
                                    .load(Base64.decode(response.body().getText(), Base64.DEFAULT))
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .fitCenter()
                                    .into( holder.imageView);
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
        holder.rltv.setOnClickListener(v -> {
            if(createplan.equals("0")){
                intent = new Intent(mContext, PersonDetailActivity.class);
                intent.putExtra("personcode", persons.get(position).getPersonCode());
            }else {
                intent = new Intent(mContext, CreateplanActivity.class);
                intent.putExtra("personcode", persons.get(position).getPersonCode());
                intent.putExtra("createplan", createplan);
                intent.putExtra("plantype", plantype);
            }
            mContext.startActivity(intent);

        });
    }

    @Override
    public int getItemCount()
    {
        return persons.size();

    }




    class GoodViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView textView1;
        private final TextView textView2;
        private final TextView textView3;
        private final CircleImageView imageView;





        CardView rltv;

        GoodViewHolder(View itemView)
        {
            super(itemView);
            textView1 = itemView.findViewById(R.id.person_tv1);
            textView2 = itemView.findViewById(R.id.person_tv2);
            textView3 = itemView.findViewById(R.id.person_tv3);
            imageView = itemView.findViewById(R.id.person_profilepic);

            rltv =  itemView.findViewById(R.id.person_card);

        }
    }



}

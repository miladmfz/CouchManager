package com.kits.couchmanager.adapter;


import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.kits.couchmanager.R;
import com.kits.couchmanager.activity.MainActivity;
import com.kits.couchmanager.activity.PersonActivity;
import com.kits.couchmanager.activity.PersonDetailActivity;
import com.kits.couchmanager.model.Person;
import com.kits.couchmanager.model.Plan;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class PlanRowAdapter extends RecyclerView.Adapter<PlanRowAdapter.GoodViewHolder>{
    DecimalFormat decimalFormat= new DecimalFormat("0,000");
    private List<Plan> plans;
    Intent intent;
    Plan plan;
    private final Context mContext;


    public PlanRowAdapter(List<Plan> plans, Context context)
    {
        this.mContext=context;
        this.plans = plans;
    }

    @NonNull
    @Override
    public GoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.planrow, parent, false);
        return new GoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GoodViewHolder holder, final int position)
    {
        plan=plans.get(position);

        holder.textView1.setText(plan.getItemName());
        holder.textView2.setText(plan.getRepeat());
        holder.textView3.setText(plan.getDuration());
        holder.textView4.setText(plan.getDayInWeek());

    }

    @Override
    public int getItemCount()
    {
        return plans.size();

    }




    class GoodViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView textView1;
        private final TextView textView2;
        private final TextView textView3;
        private final TextView textView4;




        CardView rltv;

        GoodViewHolder(View itemView)
        {
            super(itemView);
            textView1 = itemView.findViewById(R.id.planrowname);
            textView2 = itemView.findViewById(R.id.planrowtekrar);
            textView3 = itemView.findViewById(R.id.planrowexplain);
            textView4 = itemView.findViewById(R.id.planrowdayinweek);


            rltv =  itemView.findViewById(R.id.planrow_card);

        }
    }



}

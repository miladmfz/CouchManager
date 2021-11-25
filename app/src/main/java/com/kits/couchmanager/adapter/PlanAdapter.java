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
import com.kits.couchmanager.activity.PlanActivity;
import com.kits.couchmanager.activity.PlanDetailActivity;
import com.kits.couchmanager.model.NumberFunctions;
import com.kits.couchmanager.model.Plan;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.GoodViewHolder>{
    DecimalFormat decimalFormat= new DecimalFormat("0,000");
    private List<Plan> plans;
    Intent intent;
    Plan plan;
    private final Context mContext;


    public PlanAdapter(List<Plan> PlanList, Context context)
    {
        this.mContext=context;
        this.plans = PlanList;
    }

    @NonNull
    @Override
    public GoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan, parent, false);
        return new GoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GoodViewHolder holder, final int position)
    {
        plan=plans.get(position);

        holder.textView1.setText(plan.getTargetPlan());
        holder.textView2.setText(NumberFunctions.PerisanNumber(plan.getMobileNumber()));
        holder.textView3.setText(NumberFunctions.PerisanNumber(plan.getStartDate()));
        holder.textView4.setText(NumberFunctions.PerisanNumber(plan.getEndDate()));

        holder.rltv.setOnClickListener(v -> {
            intent = new Intent(mContext, PlanDetailActivity.class);
            intent.putExtra("plancode", plans.get(position).getPlanCode());
            intent.putExtra("dayperiod", plans.get(position).getDayPeriod());
            mContext.startActivity(intent);
        });
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
            textView1 = itemView.findViewById(R.id.planfullname);
            textView2 = itemView.findViewById(R.id.planmobile);
            textView3 = itemView.findViewById(R.id.plancreationdate);
            textView4 = itemView.findViewById(R.id.plantype);


            rltv =  itemView.findViewById(R.id.plan_card);

        }
    }



}

package com.kits.couchmanager.adapter;


import android.app.Activity;
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
import com.kits.couchmanager.model.RetrofitResponse;
import com.kits.couchmanager.model.TimeSheet;
import com.kits.couchmanager.webService.APIClient;
import com.kits.couchmanager.webService.APIInterface;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TimeSheetAdapter extends RecyclerView.Adapter<TimeSheetAdapter.GoodViewHolder>{
    DecimalFormat decimalFormat= new DecimalFormat("0,000");
    private List<TimeSheet> timeSheets;
    Intent intent;
    TimeSheet timeSheet;
    private final Context mContext;
    ArrayList<String> Data_spinnerstate =new ArrayList<>() ;
    public APIInterface apiInterface = APIClient.getCleint().create(APIInterface.class);


    public TimeSheetAdapter(ArrayList<TimeSheet> timeSheets, Context context)
    {
        this.mContext=context;
        this.timeSheets = timeSheets;
        Data_spinnerstate.add("انتخاب");
        Data_spinnerstate.add("حاضر");
        Data_spinnerstate.add("غیبت");
        Data_spinnerstate.add("فریز");
    }

    @NonNull
    @Override
    public GoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timesheetcard, parent, false);
        return new GoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GoodViewHolder holder, final int position)
    {
        timeSheet=timeSheets.get(position);

        holder.textView1.setText(NumberFunctions.PerisanNumber(timeSheets.get(position).getTimeSheetDate()));
        holder.textView2.setText(NumberFunctions.PerisanNumber(Data_spinnerstate.get(Integer.parseInt(timeSheets.get(position).getState()))));
        holder.textView3.setText(NumberFunctions.PerisanNumber(timeSheets.get(position).getDailyTime()));
        holder.textView4.setText(NumberFunctions.PerisanNumber(timeSheets.get(position).getWorkOutQuality()));
        holder.textView5.setText(NumberFunctions.PerisanNumber((timeSheets.size()-position)+""));

        holder.rltv.setOnLongClickListener(v -> {
            new android.app.AlertDialog.Builder(mContext)
                    .setTitle("خذف رکورد")
                    .setMessage("رکورد مورد نظر خذف شود؟")
                    .setPositiveButton("بله", (dialogInterface, i) -> {

                        Call<RetrofitResponse> call = apiInterface.DeleteTimeSheetState("DeleteTimeSheetState",timeSheets.get(position).getTimeSheetCode());
                        call.enqueue(new Callback<RetrofitResponse>() {
                            @Override
                            public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
                                if(response.body().getText().equals("Done")) {
                                    ((Activity) mContext).finish();
                                    mContext.startActivity(((Activity) mContext).getIntent());
                                }
                            }

                            @Override
                            public void onFailure(Call<RetrofitResponse> call, Throwable t) {

                            }
                        });

                    })
                    .setNegativeButton("خیر", (dialogInterface, i) -> {
                    })
                    .show();
            return false;
        });

    }

    @Override
    public int getItemCount()
    {
        return timeSheets.size();
    }




    class GoodViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView textView1;
        private final TextView textView2;
        private final TextView textView3;
        private final TextView textView4;
        private final TextView textView5;
        CardView rltv;

        GoodViewHolder(View itemView)
        {
            super(itemView);
            textView1 = itemView.findViewById(R.id.tscard_date);
            textView2 = itemView.findViewById(R.id.tscard_state);
            textView3 = itemView.findViewById(R.id.tscard_time);
            textView4 = itemView.findViewById(R.id.tscard_focus);
            textView5 = itemView.findViewById(R.id.tscard_number);


            rltv =  itemView.findViewById(R.id.tscard);

        }
    }



}

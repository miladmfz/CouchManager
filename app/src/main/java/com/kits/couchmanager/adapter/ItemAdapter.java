package com.kits.couchmanager.adapter;


import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kits.couchmanager.R;

import com.kits.couchmanager.model.Item;



import java.text.DecimalFormat;
import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.GoodViewHolder>{
    DecimalFormat decimalFormat= new DecimalFormat("0,000");
    private List<Item> items;
    Intent intent;
    Item item;
    private final Context mContext;


    public ItemAdapter(List<Item> Items, Context context)
    {
        this.mContext=context;
        this.items = Items;
    }

    @NonNull
    @Override
    public GoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new GoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GoodViewHolder holder, final int position)
    {

        holder.textView1.setText(items.get(position).getItemName());


    }

    @Override
    public int getItemCount()
    {
        return items.size();

    }




    class GoodViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView textView1;

        GoodViewHolder(View itemView)
        {
            super(itemView);
            textView1 = itemView.findViewById(R.id.Item_name);
        }
    }



}

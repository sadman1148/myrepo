package com.redenvy.sqlitedbtestapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private static final String TAG = "R3DENVY";

    private ArrayList<String> localDataSet;
    private Context context;

    public myAdapter(ArrayList localDataSet, Context context) {
        this.localDataSet = localDataSet;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView cartextView;
        TextView brandtextView;
        TextView patchtextView;

        public ViewHolder(View view) {
            super(view);
            cartextView = (TextView) view.findViewById(R.id.carName);
            brandtextView = (TextView) view.findViewById(R.id.brandName);
            patchtextView = (TextView) view.findViewById(R.id.patch);
            // Define click listener for the ViewHolder's View here if needed
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datalayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        String[] temp = localDataSet.get(position).split("_");
        if(!temp[0].equals("")){
            viewHolder.cartextView.setText(temp[0]);
            viewHolder.brandtextView.setText(temp[1]);
            viewHolder.patchtextView.setText(temp[2]);
        }
        else{
            viewHolder.cartextView.setVisibility(View.GONE);
            viewHolder.brandtextView.setVisibility(View.GONE);
            viewHolder.patchtextView.setVisibility(View.GONE);
        }
    }

    public void cleanSlate() {
        localDataSet.clear();
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
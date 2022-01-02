package com.redenvy.sqlitedbtestapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter <myAdapter.ViewHolder>{

    private static final String TAG = "R3DENVY";
    
    private ArrayList<String> localDataSet;
    private Context context;

    public myAdapter(ArrayList<String> dataSet, Context context) {
        localDataSet = dataSet;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView cartextView;
        private final TextView brandtextView;
        private final TextView patchtextView;

        public ViewHolder(View view) {
            super(view);
            cartextView = (TextView) view.findViewById(R.id.carName);
            brandtextView = (TextView) view.findViewById(R.id.brandName);
            patchtextView = (TextView) view.findViewById(R.id.patch);

            // Define click listener for the ViewHolder's View here if needed
        }
        public TextView getCartextView(){
            return cartextView;
        }
        public TextView getBrandtextView(){
            return brandtextView;
        }
        public TextView getPatchtextView(){
            return patchtextView;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datalayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        String [] temp = localDataSet.get(position).split("_");
        Log.e(TAG, ""+localDataSet.get(position));
        viewHolder.getCartextView().setText(temp[0]);
        viewHolder.getBrandtextView().setText(temp[1]);
        viewHolder.getPatchtextView().setText(temp[2]);
    }

    public void cleanSlate(){
        localDataSet.clear();
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

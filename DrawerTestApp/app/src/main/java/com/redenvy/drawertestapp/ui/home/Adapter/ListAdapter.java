package com.redenvy.drawertestapp.ui.home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.redenvy.drawertestapp.R;
import com.redenvy.drawertestapp.ui.home.Model.Sample;

import java.util.List;

public class ListAdapter  extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private Context context;
    private List<Sample> stockList;
    public ListAdapter(Context c, List<Sample> l){
        context = c;
        stockList = l;
    }
    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.sample_adapter,null);
        return new ListAdapter.ListViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ListAdapter.ListViewHolder holder, int position){
        final Sample sample = stockList.get(position);
        holder.name.setText(sample.getName());
        holder.details.setText(sample.getDetails());
        holder.imageView.setImageResource(sample.getImage());
    }
    @Override
    public int getItemCount() {
        return stockList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView name, details;
        private ImageView imageView;

        public ListViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.list_title);
            details = itemView.findViewById(R.id.list_details);
            imageView = itemView.findViewById(R.id.list_imageview);
        }
    }
}
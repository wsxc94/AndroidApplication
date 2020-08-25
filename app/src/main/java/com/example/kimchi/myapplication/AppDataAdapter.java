package com.example.kimchi.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AppDataAdapter extends RecyclerView.Adapter<AppDataAdapter.MyViewHolder> implements Filterable {
    private List<AppData> mDataset;
    private List<AppData> exampleListFull;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView imageView;

        //ViewHolder
        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.AppName);
            imageView = (ImageView) view.findViewById(R.id.AppImage);
        }
    }

    public AppDataAdapter(List<AppData> myData, Context context){
        this.mDataset = myData;
        mContext = context;
        exampleListFull = new ArrayList<>(myData);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.recyclerview, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return this.mDataset.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final AppDataAdapter.MyViewHolder holder, final int position) {
        holder.name.setText(mDataset.get(position).getName());
        Picasso.with(mContext).load(mDataset.get(position).getPicture()).fit().centerInside().into(holder.imageView);
        //holder.imageView.setImageDrawable(mDataset.get(position).getPicture());


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<AppData> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(exampleListFull);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(AppData item : exampleListFull){
                    if(item.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values =filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mDataset.clear();
            mDataset.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}




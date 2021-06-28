package com.android.rohitt.asiancountriesinformation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class CountryAdapterOffline extends RecyclerView.Adapter<CountryAdapterOffline.CountryHolder> {
    private ArrayList<Country> files;

    public CountryAdapterOffline(ArrayList<Country> files) {
        this.files = files;
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listitem = inflater.inflate(R.layout.items, parent, false);
        CountryHolder countryHolder = new CountryHolder(listitem);
        return countryHolder;
    }

    public int getPosition(String name){
        int pos = 0;
        for(Country country: files){
            if(country.getName().equals(name)){
                return pos;
            }
            else{
                pos++;
            }
        }
        return -1;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {
        Country temp = files.get(position);
        holder.name.setText(temp.getName());
        holder.capital.setText(temp.getCapital());
        holder.region.setText(temp.getRegion());
        holder.subregion.setText(temp.getSubregion());
        holder.population.setText(temp.getPopulation());
        holder.borders.setText(temp.getBorders());
        holder.languages.setText(temp.getLanguages());
        Utils.fetchSVG(MainActivity.fcontext, temp.getFlag(), holder.flag);



    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public class CountryHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView capital;
        public TextView region;
        public TextView subregion;
        public TextView population;
        public TextView borders;
        public TextView languages;
        public final ImageView flag;
        public CountryHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.capital = itemView.findViewById(R.id.capital);
            this.region = itemView.findViewById(R.id.region);
            this.subregion = itemView.findViewById(R.id.subregion);
            this.population = itemView.findViewById(R.id.population);
            this.borders = itemView.findViewById(R.id.borders);
            this.languages = itemView.findViewById(R.id.languages);
            this.flag = itemView.findViewById(R.id.flag);
        }
    }


}


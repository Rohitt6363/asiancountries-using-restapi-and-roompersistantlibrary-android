package com.android.rohitt.asiancountriesinformation;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

public class Shelf {
    private ArrayList<File> mCountries;
    private CountryRoomDatabase db;
    private CountryDao countryDao;
    private Integer tcount = 0;
    private Context mcontext;

    private ArrayList<Country> allcountries = new ArrayList<>();



    public Shelf() {
        mCountries = new ArrayList<>();
    }

    public ArrayList<File> getmCountries() {
        return mCountries;
    }

    public void addFile(File file){
        mCountries.add(file);
    }

    public int getsize(){
        return mCountries.size();
    }

    public File getCountry(int index){
        return mCountries.get(index);
    }

    public ArrayList<Country> fetch(Context context){
        db = CountryRoomDatabase.getDatabase(context);
        countryDao = db.countryDao();
        new FetchAsyncTask(countryDao).execute();
        return allcountries;
    }

    public void fcount(Context context){
        mcontext = context;
        db = CountryRoomDatabase.getDatabase(context);
        countryDao = db.countryDao();
        count();
    }

    public void deleteAll(){
        new DeleteAsyncTak(countryDao).execute();
    }

    public void insertToRoom(Context context){
        Log.d("RRohit", String.valueOf(mCountries.size()));
        if(tcount > 0){
            return;
        }
        for(int i=0; i<mCountries.size(); i++){
            final String id = String.valueOf(i+1);
            File temp = mCountries.get(i);
            Country country = new Country(id, temp.getmName(), temp.getmCapital(), temp.getmFlag(), temp.getmRegion(), temp.getmSubregion(), temp.getmPopulation(), temp.getmBorders(), temp.getmLanguages());
            insert(country);
        }
    }

    public void insert(Country country){
        new InsertAsyncTask(countryDao).execute(country);
    }

    private class InsertAsyncTask extends AsyncTask<Country, Void, Void> {
        CountryDao countryDao;
        public InsertAsyncTask(CountryDao countryDao) {
            this.countryDao = countryDao;
        }

        @Override
        protected Void doInBackground(Country... countries) {
            countryDao.insert(countries[0]);
            return null;
        }
    }


    public void count(){
        new CountAsyncTask(countryDao).execute();
    }

    private class CountAsyncTask extends AsyncTask<Country, Void, Void> {
        CountryDao countryDao;
        public CountAsyncTask(CountryDao countryDao) {
            this.countryDao = countryDao;
        }

        @Override
        protected Void doInBackground(Country... countries) {
            tcount = countryDao.getCount();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            insertToRoom(mcontext);

        }
    }

    private class FetchAsyncTask extends AsyncTask<Void, Void, ArrayList<Country>> {
        CountryDao countryDao;
        public FetchAsyncTask(CountryDao countryDao) {
            this.countryDao = countryDao;
        }

        @Override
        protected ArrayList<Country> doInBackground(Void... voids) {
            for(Country country: countryDao.getAllCountries()){
                allcountries.add(country);
            }
            return allcountries;
        }

        @Override
        protected void onPostExecute(ArrayList<Country> countries) {
            allcountries = countries;
            MainActivity.countryAdapterOffline = new CountryAdapterOffline(allcountries);
            MainActivity.mRecyclerView.setAdapter(MainActivity.countryAdapterOffline);
        }
    }

    private class DeleteAsyncTak extends AsyncTask<Void, Void, Void>{
        CountryDao countryDao;
        public DeleteAsyncTak(CountryDao countryDao) {
            this.countryDao = countryDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            countryDao.deleteAll();
            return null;
        }
    }
}

package com.android.rohitt.asiancountriesinformation;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Country.class, version = 1)
public abstract class CountryRoomDatabase extends RoomDatabase {

    public abstract CountryDao countryDao();

    private static CountryRoomDatabase countryRoomInstance;

    public static synchronized CountryRoomDatabase getDatabase(final Context context){
        if(countryRoomInstance == null){
            countryRoomInstance = Room.databaseBuilder(context, CountryRoomDatabase.class, "country_database").build();

        }
        return countryRoomInstance;
    }
}

package com.android.rohitt.asiancountriesinformation;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CountryDao {

    @Insert
    void insert(Country country);

@Query("SELECT COUNT(id) FROM countries")
    Integer getCount();

@Query("SELECT * FROM countries")
    List<Country> getAllCountries();

@Query("DELETE FROM countries")
    void deleteAll();
}

package com.android.rohitt.asiancountriesinformation;

import android.os.Debug;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

public class File {
    private String mName;
    private String mCapital;
    private String mFlag;
    private String mRegion;
    private String mSubregion;
    private String mPopulation;
    private String mBorders;
    private String mLanguages;

    public File(String name, String capital, String flag, String region, String subregion, String population, String borders, String languages) {
        mName = name;
        mCapital = capital;
        mFlag = flag;
        mRegion = region;
        mSubregion = subregion;
        mPopulation = population;
        mBorders = borders;
        mLanguages = languages;

    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCapital() {
        return mCapital;
    }

    public void setmCapital(String mCapital) {
        this.mCapital = mCapital;
    }

    public String getmFlag() {
        return mFlag;
    }

    public void setmFlag(String mFlag) {
        this.mFlag = mFlag;
    }

    public String getmRegion() {
        return mRegion;
    }

    public void setmRegion(String mRegion) {
        this.mRegion = mRegion;
    }

    public String getmSubregion() {
        return mSubregion;
    }

    public void setmSubregion(String mSubregion) {
        this.mSubregion = mSubregion;
    }

    public String getmPopulation() {
        return mPopulation;
    }

    public void setmPopulation(String mPopulation) {
        this.mPopulation = mPopulation;
    }

    public String getmBorders() {
        return mBorders;
    }

    public void setmBorders(String mBorders) {
        this.mBorders = mBorders;
    }

    public String getmLanguages() {
        return mLanguages;
    }

    public void setmLanguages(String mLanguages) {
        this.mLanguages = mLanguages;
    }

    public void debugFile(){
        Log.d("RRohit", mName);
        Log.d("RRohit", mCapital);
        Log.d("RRohit", mFlag);
        Log.d("RRohit", mRegion);
        Log.d("RRohit", mSubregion);
        Log.d("RRohit", mPopulation);
        Log.d("RRohit", mBorders.toString());
        Log.d("RRohit", mLanguages.toString());
    }
}

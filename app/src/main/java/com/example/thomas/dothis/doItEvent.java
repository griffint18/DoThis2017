package com.example.thomas.dothis;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;
//import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Thomas on 6/1/2017.
 */


public class doItEvent implements Serializable {
//    private SharedPreferences mPrefs;

    public enum doItEventCategory {
        SCHOOL, WORK, PLAY, ENTERTAINMENT, OTHER
    }

    private String title;
    private String location;
    private doItEventCategory category = doItEventCategory.OTHER;
    private final String COLON = ":";

    private GregorianCalendar startDT;

    @Override
    public String toString() {
        String s = "Title: " + getTitle() + ", Location: " + getLocation() + ", On: " + startDT.MONTH + startDT.DAY_OF_MONTH + ", " + startDT.YEAR + " at: " + startDT.HOUR + COLON + startDT.MINUTE;
        return s;
    }

    public doItEventCategory getCategory() {
        return category;
    }

    public void setCategory(doItEventCategory category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void saveData(SharedPreferences mPrefs, String index) {
        //this.mPrefs = d;
        mPrefs.edit().putString(index + "_Location", location).apply();
        String savedLocation;
        mPrefs.getString(index+ "_Location", "");
        mPrefs.edit().putString(index + "_Title", title).apply();
        mPrefs.edit().putInt(index + "_Year", startDT.get(Calendar.YEAR)).apply();
        mPrefs.edit().putInt(index + "_Month", startDT.get(Calendar.MONTH)).apply();
        mPrefs.edit().putInt(index + "_Day", startDT.get(Calendar.DAY_OF_MONTH)).apply();
        mPrefs.edit().putInt(index + "_Hour", startDT.get(Calendar.HOUR_OF_DAY)).apply();
        mPrefs.edit().putInt(index + "_Minute", startDT.get(Calendar.MINUTE)).apply();
    }

    public void readData(SharedPreferences mPrefs, String index) {
//        this.mPrefs = d;
        this.location = mPrefs.getString(index + "_Location", "");
        this.title = mPrefs.getString(index + "_Title", "");
        int year = mPrefs.getInt(index + "_Year", startDT.get(Calendar.YEAR));
        int month = mPrefs.getInt(index + "_Month", startDT.get(Calendar.MONTH));
        int day = mPrefs.getInt(index + "_Day", startDT.get(Calendar.DAY_OF_MONTH));
        int hour = mPrefs.getInt(index + "_Hour", startDT.get(Calendar.HOUR_OF_DAY));
        int minute = mPrefs.getInt(index + "_Minute", startDT.get(Calendar.MINUTE));
        this.startDT = new GregorianCalendar(year, month, day, hour, minute);
    }

    public GregorianCalendar getStartDT() {
        return startDT;
    }

    public void setStartDT(GregorianCalendar startDT) {
        this.startDT = startDT;
    }

//    public void setStartDate(GregorianCalendar d) {
//        startDT.set(d.get(Calendar.YEAR), d.get(Calendar.MONTH), d.get(Calendar.DAY_OF_MONTH), d.get(Calendar.HOUR_OF_DAY), d.get(Calendar.MINUTE));
//    }

    public String getDateTime() {
        StringBuilder s = new StringBuilder();
        s.append(startDT.get(Calendar.MONTH) + 1);
        s.append("/");
        s.append(startDT.get(Calendar.DAY_OF_MONTH));
        s.append("/");
        s.append(startDT.get(Calendar.YEAR));
        s.append(" at: ");
        s.append(startDT.get(Calendar.HOUR_OF_DAY));
        s.append(COLON);
        if (startDT.get(Calendar.MINUTE) < 10) {
            s.append("0");
        }
        s.append(startDT.get(Calendar.MINUTE));
        System.out.println("in getdatetime day = " + startDT.get(Calendar.DAY_OF_MONTH));
        return s.toString();
    }

    public doItEvent() {
        startDT = new GregorianCalendar();
        System.out.println("Start date and time = " + startDT);
    }
}

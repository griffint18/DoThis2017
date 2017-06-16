package com.example.thomas.dothis;

import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Thomas on 6/1/2017.
 */

// Custom event, stored in the arrayAdapter
public class doItEvent implements Serializable {

    // Going to work on editing the events to be able to set a specific category and take other info
    public enum doItEventCategory {
        SCHOOL, WORK, PLAY, ENTERTAINMENT, OTHER
    }

    private String title;
    private String location;
    private final String COLON = ":";

    private GregorianCalendar startDT;

    // Overriding the toString method
    @Override
    public String toString() {
        String s = "Title: " + getTitle() + ", Location: " + getLocation() + ", On: " + startDT.MONTH + startDT.DAY_OF_MONTH + ", " + startDT.YEAR + " at: " + startDT.HOUR + COLON + startDT.MINUTE;
        return s;
    }

    // Getters and setters for the properties
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

    // Saves the data into mPrefs
    public void saveData(SharedPreferences mPrefs, String index) {
        mPrefs.edit().putString(index + "_Location", location).apply();
        mPrefs.getString(index+ "_Location", "");
        mPrefs.edit().putString(index + "_Title", title).apply();
        mPrefs.edit().putInt(index + "_Year", startDT.get(Calendar.YEAR)).apply();
        mPrefs.edit().putInt(index + "_Month", startDT.get(Calendar.MONTH)).apply();
        mPrefs.edit().putInt(index + "_Day", startDT.get(Calendar.DAY_OF_MONTH)).apply();
        mPrefs.edit().putInt(index + "_Hour", startDT.get(Calendar.HOUR_OF_DAY)).apply();
        mPrefs.edit().putInt(index + "_Minute", startDT.get(Calendar.MINUTE)).apply();
    }

    // Reads the data back from mPrefs
    public void readData(SharedPreferences mPrefs, String index) {
        this.location = mPrefs.getString(index + "_Location", "");
        this.title = mPrefs.getString(index + "_Title", "");
        int year = mPrefs.getInt(index + "_Year", startDT.get(Calendar.YEAR));
        int month = mPrefs.getInt(index + "_Month", startDT.get(Calendar.MONTH));
        int day = mPrefs.getInt(index + "_Day", startDT.get(Calendar.DAY_OF_MONTH));
        int hour = mPrefs.getInt(index + "_Hour", startDT.get(Calendar.HOUR_OF_DAY));
        int minute = mPrefs.getInt(index + "_Minute", startDT.get(Calendar.MINUTE));
        this.startDT = new GregorianCalendar(year, month, day, hour, minute);
    }

    // Getter and setter for the date and time
    public GregorianCalendar getStartDT() {
        return startDT;
    }

    public void setStartDT(GregorianCalendar startDT) {
        this.startDT = startDT;
    }

    // Default constructor for the event
    public doItEvent() {
        startDT = new GregorianCalendar();
    }
}

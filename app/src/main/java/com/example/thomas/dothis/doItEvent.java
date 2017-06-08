package com.example.thomas.dothis;

import java.io.Serializable;
//import java.sql.Time;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Thomas on 6/1/2017.
 */


public class doItEvent implements Serializable {

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

    public GregorianCalendar getStartDT() {
        return startDT;
    }

    public void setStartDT(GregorianCalendar startDT) {
        this.startDT = startDT;
    }

    public doItEvent() {
        startDT = new GregorianCalendar();
        System.out.println("Start date and time = " + startDT);
    }
}

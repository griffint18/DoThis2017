package com.example.thomas.dothis;

/**
 * Created by Thomas on 6/1/2017.
 */


public class doItEvent {
    public enum doItEventCategory {
        SCHOOL, WORK, PLAY, ENTERTAINMENT, OTHER
    }
    private String title = "Title";
    private String location = "Location";
    private doItEventCategory category = doItEventCategory.OTHER;

    @Override
    public String toString() {
        return getTitle();
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
}

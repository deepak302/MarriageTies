package com.mentobile.marriageties;

/**
 * Created by user on 11/15/2015.
 */
public class SpinnerItem {

    private String id;
    private String name;

    public SpinnerItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

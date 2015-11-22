package com.mentobile.marriageties;

import android.graphics.Bitmap;

/**
 * Created by user on 11/12/2015.
 */
public class SuccessStory {

    private int storyId;
    private String imgPath;
    private String brideName;
    private String groomName;
    private String weddingDate;
    private String yourExperience;


    public SuccessStory(int storyId, String imgPath, String brideName, String groomName, String weddingDate, String yourExperience) {
        this.storyId = storyId;
        this.imgPath = imgPath;
        this.brideName = brideName;
        this.groomName = groomName;
        this.weddingDate = weddingDate;
        this.yourExperience = yourExperience;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getBrideName() {
        return brideName;
    }

    public void setBrideName(String brideName) {
        this.brideName = brideName;
    }

    public String getGroomName() {
        return groomName;
    }

    public void setGroomName(String groomName) {
        this.groomName = groomName;
    }

    public String getWeddingDate() {
        return weddingDate;
    }

    public void setWeddingDate(String weddingDate) {
        this.weddingDate = weddingDate;
    }

    public String getYourExperience() {
        return yourExperience;
    }

    public void setYourExperience(String yourExperience) {
        this.yourExperience = yourExperience;
    }
}

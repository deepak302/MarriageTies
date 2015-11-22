package com.mentobile.marriageties;

/**
 * Created by Deepak Sharma on 7/18/2015.
 */
public class Profile {

    private String Name;
    private String EmailID;
    private String Phone;
    private String CityName;
    private String Photo;

    private static Profile profile = null;

    public static Profile getProfile() {
        if (profile == null) {
            profile = new Profile();
        }
        return profile;
    }

    public Profile() {
    }

    public Profile(String name, String emailID, String phone, String cityName, String photo) {
        Name = name;
        EmailID = emailID;
        Phone = phone;
        CityName = cityName;
        Photo = photo;
    }

    public static void emptyProfile() {
        if (profile != null) {
            profile = null;
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

}

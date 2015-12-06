package com.mentobile.marriageties;

/**
 * Created by user on 11/19/2015.
 */
public class ProfileShorted {

    private String photo;
    private String Id;
    private String name;
    private String age;
    private String height;
    private String religion;
    private String caste;
    private String gotra;
    private String education;
    private String city;
    private String state;
    private String country;
    private String shorted;
    private String blocked;

    public ProfileShorted(String photo, String id, String name, String age, String height, String religion, String caste, String gotra, String education, String city, String state, String country) {
        this.photo = photo;
        this.Id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.religion = religion;
        this.caste = caste;
        this.gotra = gotra;
        this.education = education;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public ProfileShorted(String photo, String id, String name, String age, String height, String religion, String caste, String gotra, String education, String city, String state, String country, String shorted, String blocked) {
        this.photo = photo;
        this.Id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.religion = religion;
        this.caste = caste;
        this.gotra = gotra;
        this.education = education;
        this.city = city;
        this.state = state;
        this.country = country;
        this.shorted = shorted;
        this.blocked = blocked;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getGotra() {
        return gotra;
    }

    public void setGotra(String gotra) {
        this.gotra = gotra;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getShorted() {
        return shorted;
    }

    public void setShorted(String shorted) {
        this.shorted = shorted;
    }

    public String getBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }
}
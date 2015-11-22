package com.mentobile.marriageties;

/**
 * Created by user on 11/19/2015.
 */
public class ProfileShorted {

    private int Id;
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

    public ProfileShorted(int id, String name, String age, String height, String religion, String caste, String gotra, String education, String city, String state, String country) {
        Id = id;
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

    public int getId() {
        return Id;
    }

    public void setId(int id) {
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
}
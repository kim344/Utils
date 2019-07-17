package com.kim344.utils.recycler;

public class RecyclerVerticalModel {

    private int dogImage;
    private String dogName;
    private int dogAge;
    private String dogGender;

    public int getDogImage() {
        return dogImage;
    }

    public void setDogImage(int dogImage) {
        this.dogImage = dogImage;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public int getDogAge() {
        return dogAge;
    }

    public void setDogAge(int dogAge) {
        this.dogAge = dogAge;
    }

    public String getDogGender() {
        return dogGender;
    }

    public void setDogGender(String dogGender) {
        this.dogGender = dogGender;
    }


    public RecyclerVerticalModel(int dogImage, String dogName, int dogAge, String dogGender) {
        this.dogImage = dogImage;
        this.dogName = dogName;
        this.dogAge = dogAge;
        this.dogGender = dogGender;
    }

}

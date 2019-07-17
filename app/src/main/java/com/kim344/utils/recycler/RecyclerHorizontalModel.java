package com.kim344.utils.recycler;

public class RecyclerHorizontalModel {

    private int dogImage;
    private String dogName;

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


    public RecyclerHorizontalModel(int dogImage, String dogName) {
        this.dogImage = dogImage;
        this.dogName = dogName;
    }

}

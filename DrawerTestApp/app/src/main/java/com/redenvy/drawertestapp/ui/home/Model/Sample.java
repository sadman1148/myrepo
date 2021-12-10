package com.redenvy.drawertestapp.ui.home.Model;

public class Sample {
    String name,details;
    int image;
    public Sample(String n, String d, int i){
        name = n;
        details = d;
        image = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

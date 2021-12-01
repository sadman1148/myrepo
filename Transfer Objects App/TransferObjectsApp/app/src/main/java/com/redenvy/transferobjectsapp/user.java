package com.redenvy.transferobjectsapp;

public class user {
    String name,address,phone,platform;
    int id;

    // constructor
    public user(){
        name = "Unset";
        id = -1;
        address = "Unset";
        phone = "Unset";
        platform = "Unset";
    }

    public user(String n, int id, String ad, String ph, String pl){
        name = n;
        this.id = id;
        address = ad;
        phone = ph;
        platform = pl;
    }

}

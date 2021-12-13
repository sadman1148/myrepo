package com.redenvy.drawertestapp;

public interface dataPasser {
    void receiveData(Object obj,int fragmentNo);
    Object sendData(int frag);
}
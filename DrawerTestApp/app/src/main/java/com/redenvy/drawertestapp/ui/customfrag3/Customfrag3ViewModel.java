package com.redenvy.drawertestapp.ui.customfrag3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Customfrag3ViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public Customfrag3ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Custom Fragment 3");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
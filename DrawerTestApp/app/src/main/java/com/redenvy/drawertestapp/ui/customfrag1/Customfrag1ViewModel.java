package com.redenvy.drawertestapp.ui.customfrag1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Customfrag1ViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public Customfrag1ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Custom Fragment 1");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
package com.redenvy.drawertestapp.ui.customfrag2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Customfrag2ViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public Customfrag2ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Custom Fragment 2");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
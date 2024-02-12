package com.example.convidados.ui.all;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllGuestViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AllGuestViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is All Guest fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
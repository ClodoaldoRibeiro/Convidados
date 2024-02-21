package com.example.convidados.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.convidados.model.GuestModel;
import com.example.convidados.repository.GuestRepository;

import java.util.List;

public class AllGuestViewModel extends AndroidViewModel {
    private GuestRepository mRepository;
    private MutableLiveData<List<GuestModel>> mGuestList = new MutableLiveData<List<GuestModel>>();
    public LiveData<List<GuestModel>> guestModel = this.mGuestList;

    public AllGuestViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = GuestRepository.getInstance(application.getApplicationContext());
    }

    public void getAll() {
        this.mGuestList.setValue(this.mRepository.getAll());
    }
}
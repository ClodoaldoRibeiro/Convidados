package com.example.convidados.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.convidados.model.GuestModel;
import com.example.convidados.repository.GuestRepository;

public class GuestViewModel extends AndroidViewModel {
    private final GuestRepository mGuestRepository;

    public GuestViewModel(@NonNull Application application) {
        super(application);
        this.mGuestRepository = GuestRepository.getInstance(application.getApplicationContext());
    }

    public void save(GuestModel guestModel) {
        this.mGuestRepository.insert(guestModel);
    }
}

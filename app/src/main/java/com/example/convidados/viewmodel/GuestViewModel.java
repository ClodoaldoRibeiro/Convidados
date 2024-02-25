package com.example.convidados.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.convidados.model.GuestModel;
import com.example.convidados.repository.GuestRepository;

public class GuestViewModel extends AndroidViewModel {
    private final GuestRepository mGuestRepository;

    private final MutableLiveData<GuestModel> mGuest = new MutableLiveData<>();
    public LiveData<GuestModel> guest = this.mGuest;

    private final MutableLiveData<Boolean> mFeedback = new MutableLiveData<>();
    public LiveData<Boolean> feedback = this.mFeedback;

    public GuestViewModel(@NonNull Application application) {
        super(application);
        this.mGuestRepository = GuestRepository.getInstance(application.getApplicationContext());
    }

    public void save(GuestModel guestModel) {
        if (guestModel.getId() == 0) {
            this.mFeedback.setValue(this.mGuestRepository.insert(guestModel));
        }

        if (guestModel.getId() > 0) {
            this.mFeedback.setValue(this.mGuestRepository.update(guestModel));
        }
    }

    public void guestById(int guestId) {
        this.mGuest.setValue(this.mGuestRepository.guestById(guestId));
    }
}

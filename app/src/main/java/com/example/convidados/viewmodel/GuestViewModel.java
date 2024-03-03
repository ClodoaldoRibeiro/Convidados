package com.example.convidados.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.convidados.model.FeedbackModel;
import com.example.convidados.model.GuestModel;
import com.example.convidados.repository.GuestRepository;

public class GuestViewModel extends AndroidViewModel {
    private final GuestRepository mGuestRepository;
    private final MutableLiveData<GuestModel> mGuest = new MutableLiveData<>();
    private final MutableLiveData<FeedbackModel> mFeedback = new MutableLiveData<>();

    public LiveData<GuestModel> guest = this.mGuest;
    public LiveData<FeedbackModel> feedback = this.mFeedback;

    public GuestViewModel(@NonNull Application application) {
        super(application);
        this.mGuestRepository = GuestRepository.getInstance(application.getApplicationContext());
    }

    public void save(GuestModel guestModel) {
        if (guestModel.getName().isEmpty()) {
            this.mFeedback.setValue(new FeedbackModel(false, "Name guest is required"));
            return;
        }

        if (guestModel.getId() == 0) {
            final boolean insert = this.mGuestRepository.insert(guestModel);

            if (insert) {
                this.mFeedback.setValue(new FeedbackModel("Guest insert with success"));
            } else {
                this.mFeedback.setValue(new FeedbackModel(false, "Error unexpected"));
            }

            return;
        }

        final boolean insert = this.mGuestRepository.update(guestModel);

        if (insert) {
            this.mFeedback.setValue(new FeedbackModel("Guest update with success"));
        } else {
            this.mFeedback.setValue(new FeedbackModel(false, "Error update data Guest"));
        }
    }

    public void guestById(int guestId) {
        this.mGuest.setValue(this.mGuestRepository.guestById(guestId));
    }
}

package com.example.convidados.repository;

import android.content.Context;

import com.example.convidados.database.GuestDataBaseHelper;
import com.example.convidados.model.GuestModel;

import java.util.ArrayList;
import java.util.List;

public class GuestRepository {
    final GuestDataBaseHelper mGuestDataBaseHelper;
    private static GuestRepository instance;

    private GuestRepository(Context context) {
        mGuestDataBaseHelper = new GuestDataBaseHelper(context);
    }

    public static GuestRepository getInstance(Context context) {
        if (instance == null) {
            instance = new GuestRepository(context);
        }

        return instance;
    }

    public List<GuestModel> getAll() {
        return new ArrayList<GuestModel>();
    }

    public void insert(GuestModel guest) {

    }

    public void update(GuestModel guest) {
    }

    public void delete(GuestModel guest) {
    }
}

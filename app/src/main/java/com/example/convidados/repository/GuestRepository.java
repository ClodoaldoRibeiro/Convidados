package com.example.convidados.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.convidados.constants.DataBaseConstants;
import com.example.convidados.database.GuestDataBaseHelper;
import com.example.convidados.model.GuestModel;

import java.util.ArrayList;
import java.util.List;

public class GuestRepository {
    final GuestDataBaseHelper mGuestDataBaseHelper;
    private static GuestRepository instance;

    private GuestRepository(Context context) {
        this.mGuestDataBaseHelper = new GuestDataBaseHelper(context);
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

    public boolean insert(GuestModel guest) {
        try {
            final SQLiteDatabase db = this.mGuestDataBaseHelper.getWritableDatabase();
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, getGuestContentValues(guest));
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private ContentValues getGuestContentValues(GuestModel guest) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.getName());
        contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.getConfirmation());

        return contentValues;
    }


    public void update(GuestModel guest) {
    }

    public void delete(GuestModel guest) {
    }
}

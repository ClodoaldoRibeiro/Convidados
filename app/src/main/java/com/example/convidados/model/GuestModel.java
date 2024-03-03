package com.example.convidados.model;

import android.database.Cursor;

import com.example.convidados.constants.DataBaseConstants;

public class GuestModel {
    private int id;
    private String name;
    private int confirmation;

    public GuestModel(int id, String name, int confirmation) {
        this.id = id;
        this.name = name;
        this.confirmation = confirmation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConfirmation() {
        return confirmation;
    }

    public static GuestModel getGuestModelFromCursor(Cursor cursorGuest) {
        try {
            int guestIdIndex = cursorGuest.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID);
            int guestIdValue = cursorGuest.getInt(guestIdIndex);

            int nameIndex = cursorGuest.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME);
            String nameValue = cursorGuest.getString(nameIndex);

            int presenceIndex = cursorGuest.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE);
            int presenceValue = cursorGuest.getInt(presenceIndex);

            return new GuestModel(guestIdValue, nameValue, presenceValue);
        } catch (Exception e) {
            return null;
        }
    }

}

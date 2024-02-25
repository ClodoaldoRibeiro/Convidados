package com.example.convidados.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        ArrayList<GuestModel> guests = new ArrayList<>();

        try {
            final SQLiteDatabase db = this.mGuestDataBaseHelper.getReadableDatabase();

            final String table = DataBaseConstants.GUEST.TABLE_NAME;
            final String[] columns = {DataBaseConstants.GUEST.COLUMNS.ID, DataBaseConstants.GUEST.COLUMNS.NAME, DataBaseConstants.GUEST.COLUMNS.PRESENCE,};

            final Cursor cursor = db.query(table, columns, null, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    guests.add(GuestModel.getGuestModelFromCursor(cursor));
                }
            }

            if (cursor != null) {
                cursor.close();
            }

            return guests;
        } catch (Exception e) {
            return null;
        }

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

    public boolean update(GuestModel guest) {
        try {
            final SQLiteDatabase db = this.mGuestDataBaseHelper.getWritableDatabase();
            String whereClause = DataBaseConstants.GUEST.COLUMNS.ID + " = ?";
            String[] whereArgs = {String.valueOf(guest.getId())};

            db.update(DataBaseConstants.GUEST.TABLE_NAME, getGuestContentValues(guest), whereClause, whereArgs);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(int guestId) {
        try {
            final SQLiteDatabase db = this.mGuestDataBaseHelper.getWritableDatabase();
            String whereClause = DataBaseConstants.GUEST.COLUMNS.ID + " = ?";
            String[] whereArgs = {String.valueOf(guestId)};

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, whereClause, whereArgs);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public GuestModel guestById(int guestId) {
        try {
            final SQLiteDatabase db = this.mGuestDataBaseHelper.getReadableDatabase();

            final String table = DataBaseConstants.GUEST.TABLE_NAME;
            final String[] columns = {DataBaseConstants.GUEST.COLUMNS.ID, DataBaseConstants.GUEST.COLUMNS.NAME, DataBaseConstants.GUEST.COLUMNS.PRESENCE,};

            final String selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?";
            final String[] selectionArgs = {String.valueOf(guestId)};

            final Cursor cursor = db.query(table, columns, selection, selectionArgs, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                return GuestModel.getGuestModelFromCursor(cursor);
            }

            if (cursor != null) {
                cursor.close();
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private ContentValues getGuestContentValues(GuestModel guest) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.getName());
        contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.getConfirmation());

        return contentValues;
    }
}

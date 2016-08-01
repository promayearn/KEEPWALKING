package com.augmentis.ayp.keepwalking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Chayanit on 7/27/2016.
 */
public class KeepWalkingLab {

    protected static final String TAG = "KeepWalkingLab";
    private Context context;
    private SQLiteDatabase database;

    List<KeepWalking> keepWalkingsList;

    private static KeepWalkingLab instance;

    public static KeepWalkingLab getInstance(Context context) {
        if (instance == null) {
            instance = new KeepWalkingLab(context);
        }
        return instance;
    }

    private KeepWalkingLab(Context context) {
        this.context = context;
        KeepWalkingBaseHelper keepWalkingBaseHelper = new KeepWalkingBaseHelper(context);
        database = keepWalkingBaseHelper.getWritableDatabase();
    }

    public static ContentValues getContentValues(KeepWalking keepWalking) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KeepWalkingSchema.KeepWalkingTable.Cols.UUID, keepWalking.getUuid().toString());
        contentValues.put(KeepWalkingSchema.KeepWalkingTable.Cols.TITLE, keepWalking.getTitle());
        contentValues.put(KeepWalkingSchema.KeepWalkingTable.Cols.DATE, keepWalking.getDate().getTime());
        return contentValues;
    }

    public KeepWalking getKeepWalkingById(UUID uuid) {
        KeepWalkingCursorWrapper cursor = queryKeepWalking(KeepWalkingSchema.KeepWalkingTable.Cols.UUID + " = ? ", new String[]{uuid.toString()});

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getKeepWalking();
        } finally {
            cursor.close();
        }
    }

    public void addKeepWalking(KeepWalking keepWalking) {
        Log.d(TAG, "Add KeepWalking" + keepWalking.toString());
        ContentValues contentValues = getContentValues(keepWalking);

        database.insert(KeepWalkingSchema.KeepWalkingTable.NAME, null, contentValues);
    }

    public KeepWalkingCursorWrapper queryKeepWalking(String whereCause, String[] whereArgs) {
        Cursor cursor = database.query(KeepWalkingSchema.KeepWalkingTable.NAME, null, whereCause, whereArgs, null, null, null);

        return new KeepWalkingCursorWrapper(cursor);
    }

    public List<KeepWalking> getKeepWalking() {
        List<KeepWalking> keepWalking = new ArrayList<>();

        KeepWalkingCursorWrapper cursor = queryKeepWalking(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                keepWalking.add(cursor.getKeepWalking());

                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return keepWalking;
    }

    public void updateKeepWalking(KeepWalking keepWalking) {
        Log.d(TAG, "Update KeepWalking : " + keepWalking.getTitle());
        String uuidStr = keepWalking.getUuid().toString();
        ContentValues contentValues = getContentValues(keepWalking);

        database.update(KeepWalkingSchema.KeepWalkingTable.NAME, contentValues,
                KeepWalkingSchema.KeepWalkingTable.Cols.UUID + " = ?", new String[]{uuidStr});
    }

    public void deleteKeepWalking(UUID id) {
        database.delete(KeepWalkingSchema.KeepWalkingTable.NAME, KeepWalkingSchema.KeepWalkingTable.Cols.UUID + " = ?", new String[]{id.toString()});
    }
}

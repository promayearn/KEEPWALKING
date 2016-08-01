package com.augmentis.ayp.keepwalking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Chayanit on 8/1/2016.
 */
public class KeepWalkingBaseHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;

    private static final String DATABASE_NAME = "KeepWalking.db";
    private static final String TAG = "KeepWalkingBaseHelper";

    public KeepWalkingBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "Create DataBase");

        sqLiteDatabase.execSQL("Create Table " + KeepWalkingSchema.KeepWalkingTable.NAME + "(" + "_id integer primary key autoincrement, "
                + KeepWalkingSchema.KeepWalkingTable.Cols.UUID + ","
                + KeepWalkingSchema.KeepWalkingTable.Cols.TITLE + ","
                + KeepWalkingSchema.KeepWalkingTable.Cols.DATE + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

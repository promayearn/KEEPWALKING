package com.augmentis.ayp.keepwalking;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.support.annotation.Keep;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Chayanit on 8/1/2016.
 */
public class KeepWalkingCursorWrapper extends CursorWrapper{

    public KeepWalkingCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public KeepWalking getKeepWalking() {
        String uuidString = getString(getColumnIndex(KeepWalkingSchema.KeepWalkingTable.Cols.UUID));
        long date = getLong(getColumnIndex(KeepWalkingSchema.KeepWalkingTable.Cols.DATE));
        String title = getString(getColumnIndex(KeepWalkingSchema.KeepWalkingTable.Cols.TITLE));

        KeepWalking keepWalking = new KeepWalking(UUID.fromString(uuidString));
        keepWalking.setTitle(title);
        keepWalking.setDate(new Date(date));

        return keepWalking;
    }
}

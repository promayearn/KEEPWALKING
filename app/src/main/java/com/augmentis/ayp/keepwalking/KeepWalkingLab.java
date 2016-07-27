package com.augmentis.ayp.keepwalking;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Chayanit on 7/27/2016.
 */
public class KeepWalkingLab {

    protected static final String TAG = "KeepWalkingLab";

    List<KeepWalking> keepWalkingsList;

    private static KeepWalkingLab instance;

    public static KeepWalkingLab getInstance(Context context) {
        if (instance == null) {
            instance = new KeepWalkingLab();
        }
        return instance;
    }

    private KeepWalkingLab() {
        keepWalkingsList = new ArrayList<>();

        KeepWalking keepWalking = new KeepWalking();
        keepWalking.setTitle("Title #1");

        KeepWalking keepWalking1 = new KeepWalking();
        keepWalking1.setTitle("Title #2");

        keepWalkingsList.add(keepWalking);
        keepWalkingsList.add(keepWalking1);
    }

    public KeepWalking getKeepWalkingById(UUID uuid) {
        for (KeepWalking keepWalking : keepWalkingsList) {
            if (keepWalking.getUuid().equals(uuid)) {
                return keepWalking;
            }
        }
        return null;
    }

    public int getKeepWalkingPositionById(UUID uuid) {
        int size = keepWalkingsList.size();
        for (int i = 0; i < size; i++) {
            if (keepWalkingsList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public List<KeepWalking> getKeepWalking() {
        return this.keepWalkingsList;
    }

}

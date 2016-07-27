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

        for (int i = 1; i <= 100; i++) {
            KeepWalking keepWalking = new KeepWalking();
            keepWalking.setTitle("Title #" + i);

            keepWalkingsList.add(keepWalking);
        }
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

    public static void main(String[] args) {
        KeepWalkingLab keepWalkingLab = KeepWalkingLab.getInstance(null);
        List<KeepWalking> keepWalkingsList = keepWalkingLab.getKeepWalking();
        int size = keepWalkingsList.size();
        for (int i = 0; i < size; i++) {
            System.out.println(keepWalkingsList.get(i));
        }
        System.out.println(keepWalkingsList.toString());
    }
}

package com.augmentis.ayp.keepwalking;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Chayanit on 7/27/2016.
 */
public class KeepWalking {

    protected static final String TAG = "KeepWalking";

    private UUID uuid;
    private String title;
    private Date date;

    public KeepWalking() {
        this(UUID.randomUUID());
    }

    public KeepWalking(UUID uuid) {
        this.uuid = uuid;
        date = new Date();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UUID=").append(uuid);
        builder.append(", Title=").append(title);
        builder.append(", Date=").append(date);
        return builder.toString();
    }
}


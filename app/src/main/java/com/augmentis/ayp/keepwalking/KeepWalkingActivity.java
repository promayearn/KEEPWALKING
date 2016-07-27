package com.augmentis.ayp.keepwalking;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.util.UUID;

/**
 * Created by Chayanit on 7/27/2016.
 */
public class KeepWalkingActivity extends SingleFragmentActivity {

    protected static final String KEEP_WALKING_UUID = "keepWalkingActivity.keepWalkingUuid";
    protected static final String KEEP_WALKING_POSITION = "keepWalkingActivity.keepWalkingPosition";

    protected static final String TAG = "KeepWalkingActivity";

    @Override
    protected Fragment onCreateFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(KEEP_WALKING_UUID);
        int position = (int) getIntent().getExtras().get(KEEP_WALKING_POSITION);
        Fragment fragment = KeepWalkingFragment.newInstance(crimeId, position);
        return fragment;
    }

    public static Intent newIntend(Context context, UUID uuid, int keepWalkingPosition) {
        Intent intent = new Intent(context, KeepWalkingActivity.class);
        intent.putExtra(KEEP_WALKING_UUID, uuid);
        intent.putExtra(KEEP_WALKING_POSITION, keepWalkingPosition);
        return intent;
    }
}
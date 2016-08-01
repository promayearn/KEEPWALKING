package com.augmentis.ayp.keepwalking;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;

/**
 * Created by Chayanit on 7/27/2016.
 */
public class KeepWalkingActivity extends SingleFragmentActivity {

    protected static final String KEEP_WALKING_ID = "KeepWalkingActivity.KeepWalkingUuid";

    protected static final String TAG = "KeepWalkingActivity";
    private UUID _keepWalkingId;

    @Override
    protected Fragment onCreateFragment() {
        _keepWalkingId = (UUID) getIntent().getSerializableExtra(KEEP_WALKING_ID);
        return KeepWalkingFragment.newInstance(_keepWalkingId);
    }

    public static Intent newIntend(Context context, UUID id) {
        Intent intent = new Intent(context, KeepWalkingActivity.class);
        intent.putExtra(KEEP_WALKING_ID, id);
        return intent;
    }


}

package com.augmentis.ayp.keepwalking;

import android.support.v4.app.Fragment;

/**
 * Created by Chayanit on 7/26/2016.
 */
public class KeepWalkingListActivity extends SingleFragmentActivity {

    protected static final String TAG = "KeepWalkingListActivity";

    @Override
    protected Fragment onCreateFragment() {
        return new KeepWalkingListFragment();
    }
}

package com.augmentis.ayp.keepwalking;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Chayanit on 7/26/2016.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    public static final String TAG = "SingleFragmentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_walking_single_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.fragment_container);

        if (f == null) {
            f = onCreateFragment();

            fm.beginTransaction()
                    .add(R.id.fragment_container, f)
                    .commit();
            Log.d(KeepWalkingListFragment.TAG, "Fragment is created");
        } else {
            Log.d(KeepWalkingListFragment.TAG, "Fragment didnt create");
        }
    }

    protected abstract Fragment onCreateFragment();
}

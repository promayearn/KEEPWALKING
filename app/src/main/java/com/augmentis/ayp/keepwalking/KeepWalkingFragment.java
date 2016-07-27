package com.augmentis.ayp.keepwalking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Chayanit on 7/27/2016.
 */
public class KeepWalkingFragment extends Fragment {
    private static final String KEEP_WALKING_UUID = "KeepWalkingFragment.KEEP_WALKING_UUID";
    private static final String KEEP_WALKING_POSITION = "KeepWalkingFragment.KEEP_WALKING_POSITION";

    protected static final String TAG = "KeepWalkingFragment";

    private KeepWalking keepWalking;
    private EditText keepWalkingEditText;
    private TextView keepWalkingTextView;
    private Button keepWalkingSaveButton;

    private int position;

    public KeepWalkingFragment() {
    }

    public static KeepWalkingFragment newInstance(UUID crimeID, int position) {
        Bundle args = new Bundle();
        args.putSerializable(KEEP_WALKING_UUID, crimeID);
        args.putInt(KEEP_WALKING_POSITION, position);

        KeepWalkingFragment keepWalkingFragment = new KeepWalkingFragment();
        keepWalkingFragment.setArguments(args);
        return keepWalkingFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID keepWalkingUuid = (UUID) getArguments().getSerializable(KEEP_WALKING_UUID);
        position = getArguments().getInt(KEEP_WALKING_POSITION);
        keepWalking = KeepWalkingLab.getInstance(getActivity()).getKeepWalkingById(keepWalkingUuid);
        Log.d(KeepWalkingListFragment.TAG, "keepWalking.getUuid() = " + keepWalking.getUuid());
        Log.d(KeepWalkingListFragment.TAG, "keepWalking.getTitle() = " + keepWalking.getTitle());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_keep_walking, container, false);

        keepWalkingEditText = (EditText) v.findViewById(R.id.keep_walking_title);
        keepWalkingEditText.setText(keepWalking.getTitle());
        keepWalkingEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                keepWalking.setTitle(s.toString());
//                addThisPositionToResult(position);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        keepWalkingTextView = (TextView) v.findViewById(R.id.keep_walking_present_date);
        keepWalkingTextView.setText(getFormattedDate(keepWalking.getDate()));

        keepWalkingSaveButton = (Button) v.findViewById(R.id.keep_walking_save);

        Intent intent = new Intent();
        intent.putExtra("position", position);
        Log.d(KeepWalkingListFragment.TAG, "send position back: " + position);
        getActivity().setResult(Activity.RESULT_OK, intent);
        return v;
    }


    private String getFormattedDate(Date date) {
        return new SimpleDateFormat("dd MMMM yyyy").format(date);
    }

//    private void addThisPositionToResult(int position) {
//        if (getActivity() instanceof KeepWalkingActivity) {
//            ((KeepWalkingActivity) getActivity()).addPageUpdate(position);
//        }
//    }
}
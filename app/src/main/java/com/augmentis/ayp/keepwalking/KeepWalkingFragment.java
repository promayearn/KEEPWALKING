package com.augmentis.ayp.keepwalking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    protected static final String TAG = "KeepWalkingFragment";

    private EditText keepWalkingTitleEditText;
    private TextView keepWalkingDateTextView;
    private Button keepWalkingSaveButton;

    private KeepWalking keepWalking;
    private String newTitleText;
    private boolean isNewKeepWalking;

    public KeepWalkingFragment() {
    }

    public static KeepWalkingFragment newInstance(UUID keepWalkingUuid) {
        Log.d(TAG, "new Instance");
        Bundle args = new Bundle();
        args.putSerializable(KEEP_WALKING_UUID, keepWalkingUuid);
        KeepWalkingFragment keepWalkingFragment = new KeepWalkingFragment();
        keepWalkingFragment.setArguments(args);
        return keepWalkingFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID keepWalkingUuid = (UUID) getArguments().getSerializable(KEEP_WALKING_UUID);
        keepWalking = KeepWalkingLab.getInstance(getActivity()).getKeepWalkingById(keepWalkingUuid);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_keep_walking_new_data, container, false);

        keepWalkingTitleEditText = (EditText) v.findViewById(R.id.keep_walking_title);
        if (keepWalking == null) {
            isNewKeepWalking = true;
            keepWalking = new KeepWalking();
            keepWalking.setTitle("");
        } else {
            isNewKeepWalking = false;
        }

        keepWalkingTitleEditText.setText(keepWalking.getTitle());
        keepWalkingTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                newTitleText = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        keepWalkingDateTextView = (TextView) v.findViewById(R.id.keep_walking_present_date);
        Date date = new Date();
        keepWalkingDateTextView.setText(getFormattedDate(date));

        keepWalkingSaveButton = (Button) v.findViewById(R.id.keep_walking_save);
        keepWalkingSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeepWalkingLab keepWalkingLab = KeepWalkingLab.getInstance(getActivity());
                if (isNewKeepWalking) {
                    keepWalkingLab.keepWalkingsList.add(keepWalking);
                }
                Date date = new Date();
                keepWalking.setTitle(newTitleText);
                keepWalking.setDate(date);

                Intent intent = new Intent(getContext(), KeepWalkingListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        return v;
    }

    private String getFormattedDate(Date date) {
        return new SimpleDateFormat("dd MMMM yyyy").format(date);
    }

    @Override
    public void onPause() {
        super.onPause();

        KeepWalkingLab.getInstance(getActivity()).updateKeepWalking(keepWalking);
    }
}
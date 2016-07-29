package com.augmentis.ayp.keepwalking;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Chayanit on 7/29/2016.
 */
public class KeepWalkingDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private static final String KEEP_WALKING_UUID = "KeepWalkingDialogFragment.KEEP_WALKING_UUID";

    protected static final String TAG = "DialogFragment";

    private EditText keepWalkingTitleEditText;
    private TextView keepWalkingDateTextView;

    private KeepWalking keepWalking;
    private String newTitleText;
    private static String isEdit;

    public static KeepWalkingDialogFragment newInstance(UUID uuid, String edit) {
        isEdit = edit;
        KeepWalkingDialogFragment df = new KeepWalkingDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEEP_WALKING_UUID, uuid);
        df.setArguments(args);
        return df;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID keepWalkingUuid = (UUID) getArguments().getSerializable(KEEP_WALKING_UUID);
        keepWalking = KeepWalkingLab.getInstance(getActivity()).getKeepWalkingById(keepWalkingUuid);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_keep_walking_new_data, null);

        keepWalkingTitleEditText = (EditText) v.findViewById(R.id.keep_walking_title);
        if (keepWalking == null) {
            Log.d(TAG, "KEEPWALKING = NULL");
            keepWalking = new KeepWalking();
            keepWalking.setTitle("");
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

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        builder.setTitle(R.string.add_dialog_title);
        builder.setPositiveButton(android.R.string.ok, this);

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        KeepWalkingLab keepWalkingLab = KeepWalkingLab.getInstance(getActivity());
        if (isEdit.equals("false")) {
            keepWalkingLab.keepWalkingsList.add(keepWalking);
        }
        Date date = new Date();
        keepWalking.setTitle(newTitleText);
        keepWalking.setDate(date);

        Intent intent = new Intent(getContext(), KeepWalkingListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private String getFormattedDate(Date date) {
        return new SimpleDateFormat("dd MMMM yyyy").format(date);
    }
}

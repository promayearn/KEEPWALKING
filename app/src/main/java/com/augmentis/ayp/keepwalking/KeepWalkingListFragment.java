package com.augmentis.ayp.keepwalking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Chayanit on 7/26/2016.
 */
public class KeepWalkingListFragment extends Fragment {

    protected static final String TAG = "KeepWalkingListFragment";
    private static final int REQUEST_UPDATE_CRIME = 23340;

    private RecyclerView keepWalkingRecycleView;
    private KeepWalkingAdapter keepWalkingAdapter;
    private Integer[] keepWalkingPosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_keep_walking_list, container, false);


        keepWalkingRecycleView = (RecyclerView) v.findViewById(R.id.keep_walking_recycler_view);
        keepWalkingRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    /**
     * Update UI
     */
    private void updateUI() {
        KeepWalkingLab keepWalkingLab = KeepWalkingLab.getInstance(getActivity());
        List<KeepWalking> keepWalkings = keepWalkingLab.getKeepWalking();
        if (keepWalkingAdapter == null) {
            keepWalkingAdapter = new KeepWalkingAdapter(keepWalkings);
            keepWalkingRecycleView.setAdapter(keepWalkingAdapter);
        } else {
            // _adapter.notifyDataSetChanged();
            if (keepWalkingPosition != null) {
                for (int pos : keepWalkingPosition) {
                    keepWalkingAdapter.notifyItemChanged(pos);
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Resume list");
        updateUI();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_UPDATE_CRIME) {
            keepWalkingPosition = (Integer[]) data.getExtras().get("position");
            Log.d(TAG, "get crimePos =" + keepWalkingPosition);

            Log.d(TAG, "Return form crime fragment");
        }
    }

    private class KeepWalkingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView keepWalkingTitleTextView;
        public TextView keepWalkingDateTextView;
        public Button keepWalkingAddButton;

        KeepWalking _keepWalking;
        int _keepWalkingPosition;

        public KeepWalkingHolder(View itemView) {
            super(itemView);

            keepWalkingTitleTextView = (TextView) itemView.findViewById(R.id.list_item_keep_walking_title_text_view);
            keepWalkingDateTextView = (TextView) itemView.findViewById(R.id.list_item_keep_walking_date_text_view);
            keepWalkingAddButton = (Button) itemView.findViewById(R.id.list_item_keep_walking_add_button);

            itemView.setOnClickListener(this);
        }

        public void bind(KeepWalking keepWalking, int position) {
            _keepWalking = keepWalking;
            _keepWalkingPosition = position;
            keepWalkingTitleTextView.setText(_keepWalking.getTitle());
            keepWalkingDateTextView.setText(_keepWalking.getDate().toString());
        }

        @Override
        public void onClick(View v) {
            Intent intent = KeepWalkingActivity.newIntend(getActivity(), _keepWalking.getUuid(), _keepWalkingPosition);
            startActivityForResult(intent, REQUEST_UPDATE_CRIME);
            /*Toast.makeText(getActivity(), "Press!" + titleTextView.getText(),
                    Toast.LENGTH_SHORT)
                    .show();*/
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private class KeepWalkingAdapter extends RecyclerView.Adapter<KeepWalkingHolder> {
        private List<KeepWalking> keepWalkings;
        private int _viewCreatingCount;

        public KeepWalkingAdapter(List<KeepWalking> crimes) {
            this.keepWalkings = crimes;
        }

        @Override
        public KeepWalkingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            _viewCreatingCount++;
            Log.d(TAG, "Create view holder for CrimeList: creating view time = " + _viewCreatingCount);

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.list_item_keep_walking, parent, false);
            return new KeepWalkingHolder(v);
        }

        @Override
        public void onBindViewHolder(KeepWalkingHolder holder, int position) {
            Log.d(TAG, "Bind view holder for crimList : position = " + position);

            KeepWalking keepWalking = keepWalkings.get(position);
            holder.bind(keepWalking, position);
        }

        @Override
        public int getItemCount() {
            return keepWalkings.size();
        }
    }

}

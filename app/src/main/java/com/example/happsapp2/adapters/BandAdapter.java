package com.example.happsapp2.adapters;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.happsapp2.R;
import com.example.happsapp2.models.Band;

public class BandAdapter extends ListAdapter<Band, BandAdapter.BandHolder> {

    private OnItemClickListener listener;

    public BandAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Band> DIFF_CALLBACK = new DiffUtil.ItemCallback<Band>() {
        @Override
        public boolean areItemsTheSame(@NonNull Band band, @NonNull Band t1) {
            return band.getBandID() == t1.getBandID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Band band, @NonNull Band t1) {
            return band.getBandName().equals(t1.getBandName()) &&
                    band.getGenreTags().equals(t1.getGenreTags());
        }
    };

    @NonNull
    @Override
    public BandHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.concert_item, parent, false);
        return new BandHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BandHolder bandHolder, int position) {
        Band currentBand = getItem(position);
        bandHolder.textViewBandName.setText(currentBand.getBandName());
        bandHolder.textViewLocation.setText(currentBand.getGenreTags());
        bandHolder.textViewBandID.setText(String.valueOf(currentBand.getBandID()));
    }

    public Band getBandAt(int position) {
        return getItem(position);
    }

    class BandHolder extends RecyclerView.ViewHolder {
        private TextView textViewBandName;
        private TextView textViewLocation;
        private TextView textViewBandID;


        public BandHolder(View itemView) {
            super(itemView);
            textViewBandName = itemView.findViewById(R.id.text_view_band_name);
            textViewLocation = itemView.findViewById(R.id.text_view_location);
            //textViewBandID = itemView.findViewById(R.id.text_view_bandID);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Band band);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
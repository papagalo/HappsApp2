package com.example.happsapp2.adapters;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.happsapp2.R;
import com.example.happsapp2.models.Concert;

import java.util.ArrayList;
import java.util.List;

public class ConcertAdapter extends ListAdapter<Concert, ConcertAdapter.ConcertHolder> {

    private OnItemClickListener listener;

    public ConcertAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Concert> DIFF_CALLBACK = new DiffUtil.ItemCallback<Concert>() {
        @Override
        public boolean areItemsTheSame(@NonNull Concert concert, @NonNull Concert t1) {
            return concert.getConcertID() == t1.getConcertID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Concert concert, @NonNull Concert t1) {
            return concert.getBandName().equals(t1.getBandName()) &&
                    concert.getGenre().equals(t1.getGenre()) &&
                    concert.getLocation().equals(t1.getLocation()) &&
                    concert.getStartTime().equals(t1.getStartTime()) &&
                    concert.getEndTime().equals(t1.getEndTime());
        }
    };

    @NonNull
    @Override
    public ConcertHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.concert_item, parent, false);
        return new ConcertHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConcertHolder concertHolder, int position) {
        Concert currentConcert = getItem(position);
        concertHolder.textViewBandName.setText(currentConcert.getBandName());
        concertHolder.textViewLocation.setText(currentConcert.getLocation());
    }

    public Concert getConcertAt(int position) {
        return getItem(position);
    }

    class ConcertHolder extends RecyclerView.ViewHolder {
        private TextView textViewBandName;
        private TextView textViewLocation;
        private TextView textViewConcertID;


        public ConcertHolder(View itemView) {
            super(itemView);
            textViewBandName = itemView.findViewById(R.id.text_view_band_name);
            textViewLocation = itemView.findViewById(R.id.text_view_location);

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
        void onItemClick(Concert concert);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
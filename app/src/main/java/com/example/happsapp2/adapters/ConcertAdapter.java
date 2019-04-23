package com.example.happsapp2.adapters;

import android.support.annotation.NonNull;
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

public class ConcertAdapter extends RecyclerView.Adapter<ConcertAdapter.ConcertHolder> {

    private List<Concert> concerts = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ConcertHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.concert_item, parent, false);
        return new ConcertHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConcertHolder concertHolder, int position) {
        Concert currentConcert = concerts.get(position);
        concertHolder.textViewBandName.setText(currentConcert.getBandName());
        concertHolder.textViewLocation.setText(currentConcert.getLocation());
        concertHolder.textViewConcertID.setText(String.valueOf(currentConcert.getConcertID()));
    }

    @Override
    public int getItemCount() {
        return concerts.size();
    }

    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
        notifyDataSetChanged();
    }

    public Concert getConcertAt(int position) {
        return concerts.get(position);
    }

    class ConcertHolder extends RecyclerView.ViewHolder {
        private TextView textViewBandName;
        private TextView textViewLocation;
        private TextView textViewConcertID;


        public ConcertHolder(View itemView) {
            super(itemView);
            textViewBandName = itemView.findViewById(R.id.text_view_band_name);
            textViewLocation = itemView.findViewById(R.id.text_view_location);
            textViewConcertID = itemView.findViewById(R.id.text_view_concertID);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(concerts.get(position));
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
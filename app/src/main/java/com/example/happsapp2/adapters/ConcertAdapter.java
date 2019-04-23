package com.example.happsapp2.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.happsapp2.R;
import com.example.happsapp2.models.Concert;

import java.util.ArrayList;
import java.util.List;

public class ConcertAdapter extends RecyclerView.Adapter<ConcertAdapter.ConcertHolder> {

    private List<Concert> concerts = new ArrayList<>();

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
        concertHolder.textViewTitle.setText(currentConcert.getTitle());
        concertHolder.textViewGenre.setText(currentConcert.getLocation());
    }

    @Override
    public int getItemCount() {
        return concerts.size();
    }

    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
        notifyDataSetChanged();
    }

    class ConcertHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewGenre;


        public ConcertHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_concert_name);
            textViewGenre = itemView.findViewById(R.id.text_view_concert_genre);

        }
    }

}
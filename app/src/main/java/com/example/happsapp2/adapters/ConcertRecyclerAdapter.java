package com.example.happsapp2.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.happsapp2.R;
import com.example.happsapp2.models.Category;
import com.example.happsapp2.models.Concert;


import java.util.ArrayList;
import java.util.List;

public class ConcertRecyclerAdapter extends RecyclerView.Adapter<ConcertRecyclerAdapter.ConcertHolder> {
    //private ArrayList<Concert> gConcerts;
    private List<Concert> gConcerts;
    private OnEventListener gOnEventListener;
    private Context gContext;
    

    @NonNull
    @Override
    public ConcertHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.concert_item, parent, false);
        return new ConcertHolder(view, gOnEventListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ConcertHolder holder, int position) {
        holder.title.setText(gConcerts.get(position).getBandName());
        holder.location.setText(gConcerts.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    
    public void setConcerts(List<Concert> concerts) {
        gConcerts = concerts;
        notifyDataSetChanged();
    }

    public class ConcertHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title;
        private TextView location;

        OnEventListener onEventListener;

        public ConcertHolder(@NonNull View itemView, OnEventListener onEventListener)  {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_band_name);
            location= itemView.findViewById(R.id.text_view_location);
            this.onEventListener = onEventListener;

            itemView.setOnClickListener(this);
        }

        public void bindView(int position) {
            title.setText("hello");
            location.setText("test");
        }

        @Override
        public void onClick(View v) {
            onEventListener.onEventClick(getAdapterPosition());
        }
    }

    public interface OnEventListener {
        void onEventClick(int position);
    }

}

















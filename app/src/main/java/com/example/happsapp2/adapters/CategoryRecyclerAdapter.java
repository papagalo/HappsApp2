package com.example.happsapp2.adapters;


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

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ConcertHolder> {
    //private ArrayList<Concert> gConcerts;
    private List<Concert> gConcerts;
    private List<Category> gCategories;
    private OnEventListener gOnEventListener;



    public CategoryRecyclerAdapter(ArrayList<Category> gCategory, OnEventListener onEventListener) {
        this.gOnEventListener = onEventListener;
        this.gCategories = gCategory;
    }

    @NonNull
    @Override
    public ConcertHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.concert_item, parent, false);
        return new ConcertHolder(view, gOnEventListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ConcertHolder holder, int position) {
        holder.title.setText(gConcerts.get(position).getTitle());
        holder.genre.setText(gConcerts.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return gConcerts.size();
    }
    
    public void setConcerts(List<Concert> concerts) {
        gConcerts = concerts;
        notifyDataSetChanged();
    }

    public class ConcertHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title;
        private TextView genre;

        OnEventListener onEventListener;

        public ConcertHolder(@NonNull View itemView, OnEventListener onEventListener)  {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_concert_name);
            genre = itemView.findViewById(R.id.text_view_concert_genre);
            this.onEventListener = onEventListener;

            itemView.setOnClickListener(this);
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

















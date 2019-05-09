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
import com.example.happsapp2.models.VideoGame;

public class VideoGameAdapter extends ListAdapter<VideoGame, VideoGameAdapter.VideoGameHolder> {

    private OnItemClickListener listener;

    public VideoGameAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<VideoGame> DIFF_CALLBACK = new DiffUtil.ItemCallback<VideoGame>() {
        @Override
        public boolean areItemsTheSame(@NonNull VideoGame videoGame, @NonNull VideoGame t1) {
            return videoGame.getVideoGameID() == t1.getVideoGameID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull VideoGame videoGame, @NonNull VideoGame t1) {
            return videoGame.getVgName().equals(t1.getVgName()) &&
                    videoGame.getGenre().equals(t1.getGenre()) &&
                    videoGame.getLocation().equals(t1.getLocation()) &&
                    videoGame.getStartTime().equals(t1.getStartTime()) &&
                    videoGame.getEndTime().equals(t1.getEndTime());
        }
    };

    @NonNull
    @Override
    public VideoGameHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_game_item, parent, false);
        return new VideoGameHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoGameHolder videoGameHolder, int position) {
        VideoGame currentVideoGame = getItem(position);
        videoGameHolder.textViewVgName.setText(currentVideoGame.getVgName());
        videoGameHolder.textViewLocation.setText(currentVideoGame.getLocation());
    }

    public VideoGame getVideoGameAt(int position) {
        return getItem(position);
    }

    class VideoGameHolder extends RecyclerView.ViewHolder {
        private TextView textViewVgName;
        private TextView textViewLocation;


        public VideoGameHolder(View itemView) {
            super(itemView);
            textViewVgName = itemView.findViewById(R.id.text_view_vg_name);
            textViewLocation = itemView.findViewById(R.id.text_view_vg_location);


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
        void onItemClick(VideoGame videoGame);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
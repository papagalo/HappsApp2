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
import com.example.happsapp2.models.BoardGame;

public class BoardGameAdapter extends ListAdapter<BoardGame, BoardGameAdapter.BoardGameHolder> {

    private OnItemClickListener listener;

    public BoardGameAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<BoardGame> DIFF_CALLBACK = new DiffUtil.ItemCallback<BoardGame>() {
        @Override
        public boolean areItemsTheSame(@NonNull BoardGame boardGame, @NonNull BoardGame t1) {
            return boardGame.getBoardGameID() == t1.getBoardGameID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull BoardGame boardGame, @NonNull BoardGame t1) {
            return boardGame.getBgName().equals(t1.getBgName()) &&
                    boardGame.getLocation().equals(t1.getLocation()) &&
                    boardGame.getStartTime().equals(t1.getStartTime()) &&
                    boardGame.getEndTime().equals(t1.getEndTime());
        }
    };

    @NonNull
    @Override
    public BoardGameHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.board_game_item, parent, false);
        return new BoardGameHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardGameHolder boardGameHolder, int position) {
        BoardGame currentBoardGame = getItem(position);
        boardGameHolder.textViewBoardGameName.setText(currentBoardGame.getBgName());
        boardGameHolder.textViewLocation.setText(currentBoardGame.getLocation());
    }

    public BoardGame getBoardGameAt(int position) {
        return getItem(position);
    }

    class BoardGameHolder extends RecyclerView.ViewHolder {
        private TextView textViewBoardGameName;
        private TextView textViewLocation;
        private TextView textViewBoardGameID;


        public BoardGameHolder(View itemView) {
            super(itemView);
            textViewBoardGameName = itemView.findViewById(R.id.text_view_board_game_name);
            textViewLocation = itemView.findViewById(R.id.text_view_board_game_location);

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
        void onItemClick(BoardGame boardGame);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
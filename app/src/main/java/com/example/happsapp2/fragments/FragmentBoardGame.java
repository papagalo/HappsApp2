package com.example.happsapp2.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchUIUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.happsapp2.AddEditBoardGameActivity;
import com.example.happsapp2.R;
import com.example.happsapp2.adapters.BoardGameAdapter;
import com.example.happsapp2.models.BoardGame;
import com.example.happsapp2.view_models.BoardGameViewModel;

import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

public class FragmentBoardGame extends Fragment{
    static final int ADD_BG_REQUEST = 1;
    static final int EDIT_BG_REQUEST = 2;
    private RecyclerView recyclerView;
    private BoardGameViewModel boardGameViewModel;
    private BoardGameAdapter adapter;

    public FragmentBoardGame() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.board_game_fragment,container,false  );

        recyclerView = v.findViewById(R.id.board_game_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setHasFixedSize(true);

        adapter = new BoardGameAdapter();
        recyclerView.setAdapter(adapter);

        boardGameViewModel = ViewModelProviders.of(getActivity()).get(BoardGameViewModel.class);
        boardGameViewModel.getAllBoardGames().observe(getActivity(), new Observer<List<BoardGame>>() {
            @Override
            public void onChanged(@Nullable List<BoardGame> boardGames) {
                adapter.submitList(boardGames);
            }
        });

        adapter.setOnItemClickListener(new BoardGameAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BoardGame boardGame) {
                Intent intent = new Intent(getActivity(), AddEditBoardGameActivity.class);

                intent.putExtra(AddEditBoardGameActivity.EXTRA_BG_NAME, boardGame.getBgName());
                intent.putExtra(AddEditBoardGameActivity.EXTRA_BG_LOCATION, boardGame.getLocation());
                intent.putExtra(AddEditBoardGameActivity.EXTRA_BG_START_TIME, boardGame.getStartTime());
                intent.putExtra(AddEditBoardGameActivity.EXTRA_BG_END_TIME, boardGame.getEndTime());
                intent.putExtra(AddEditBoardGameActivity.EXTRA_BG_ID, boardGame.getBoardGameID());

                startActivityForResult(intent, EDIT_BG_REQUEST);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == ADD_BG_REQUEST && resultCode == RESULT_OK) {
            String bgName = data.getStringExtra(AddEditBoardGameActivity.EXTRA_BG_NAME);
            String location = data.getStringExtra(AddEditBoardGameActivity.EXTRA_BG_LOCATION);
            String startTime = data.getStringExtra(AddEditBoardGameActivity.EXTRA_BG_START_TIME);
            String endTime = data.getStringExtra(AddEditBoardGameActivity.EXTRA_BG_END_TIME);

            BoardGame boardGame = new BoardGame(bgName, location, startTime, endTime);
            boardGameViewModel.insert(boardGame);

            Toast.makeText(getActivity(),"BoardGame Saved", Toast.LENGTH_SHORT).show();
        } else if(requestCode == EDIT_BG_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditBoardGameActivity.EXTRA_BG_ID, -1);

            if (id == -1) {
                Toast.makeText(getActivity(), "BoardGame can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String bgName = data.getStringExtra(AddEditBoardGameActivity.EXTRA_BG_NAME);
            String location = data.getStringExtra(AddEditBoardGameActivity.EXTRA_BG_LOCATION);
            String startTime = data.getStringExtra(AddEditBoardGameActivity.EXTRA_BG_START_TIME);
            String endTime = data.getStringExtra(AddEditBoardGameActivity.EXTRA_BG_END_TIME);

            BoardGame boardGame = new BoardGame(bgName, location, startTime, endTime);
            boardGame.setBoardGameID(id);
            boardGameViewModel.update(boardGame);
            Toast.makeText(getActivity(), "BoardGame updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity(), "BoardGame Not Saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.delete_all_events:
                boardGameViewModel.deleteAllBoardGames();
                Toast.makeText(getActivity(),"All BoardGames Deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

   /* @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }*/
}

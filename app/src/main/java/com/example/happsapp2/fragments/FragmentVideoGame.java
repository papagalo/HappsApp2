package com.example.happsapp2.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.happsapp2.AddEditVideoGameActivity;
import com.example.happsapp2.R;
import com.example.happsapp2.adapters.VideoGameAdapter;
import com.example.happsapp2.models.VideoGame;
import com.example.happsapp2.view_models.VideoGameViewModel;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class FragmentVideoGame extends Fragment {
    static final int ADD_NOTE_REQUEST = 1;
    static final int EDIT_NOTE_REQUEST = 2;
    private RecyclerView recyclerView;
    private VideoGameViewModel videoGameViewModel;
    private VideoGameAdapter adapter;

    public FragmentVideoGame() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.videogame_fragment,container,false  );

        recyclerView = v.findViewById(R.id.video_game_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new VideoGameAdapter();
        recyclerView.setAdapter(adapter);

        videoGameViewModel = ViewModelProviders.of(this).get(VideoGameViewModel.class);
        videoGameViewModel.getAllVideoGames().observe(this, new Observer<List<VideoGame>>() {
            @Override
            public void onChanged(@Nullable List<VideoGame> videoGames) {
                adapter.submitList(videoGames);
            }
        });

        adapter.setOnItemClickListener(new VideoGameAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(VideoGame videoGame) {
                Intent intent = new Intent(getActivity(), AddEditVideoGameActivity.class);

                intent.putExtra(AddEditVideoGameActivity.EXTRA_VG_NAME, videoGame.getVgName());
                intent.putExtra(AddEditVideoGameActivity.EXTRA_VG_GENRE, videoGame.getGenre());
                intent.putExtra(AddEditVideoGameActivity.EXTRA_VG_LOCATION, videoGame.getLocation());
                intent.putExtra(AddEditVideoGameActivity.EXTRA_VG_START_TIME, videoGame.getStartTime());
                intent.putExtra(AddEditVideoGameActivity.EXTRA_VG_END_TIME, videoGame.getEndTime());
                intent.putExtra(AddEditVideoGameActivity.EXTRA_VG_ID, videoGame.getVideoGameID());

                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String vgName = data.getStringExtra(AddEditVideoGameActivity.EXTRA_VG_NAME);
            String genre = data.getStringExtra(AddEditVideoGameActivity.EXTRA_VG_GENRE);
            String location = data.getStringExtra(AddEditVideoGameActivity.EXTRA_VG_LOCATION);
            String startTime = data.getStringExtra(AddEditVideoGameActivity.EXTRA_VG_START_TIME);
            String endTime = data.getStringExtra(AddEditVideoGameActivity.EXTRA_VG_END_TIME);

            VideoGame videoGame = new VideoGame(vgName, genre , location, startTime, endTime);
            videoGameViewModel.insert(videoGame);

            Toast.makeText(getActivity(),"VideoGame Saved", Toast.LENGTH_SHORT).show();
        } else if(requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditVideoGameActivity.EXTRA_VG_ID, -1);

            if (id == -1) {
                Toast.makeText(getActivity(), "VideoGame can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String vgName = data.getStringExtra(AddEditVideoGameActivity.EXTRA_VG_NAME);
            String genre = data.getStringExtra(AddEditVideoGameActivity.EXTRA_VG_GENRE);
            String location = data.getStringExtra(AddEditVideoGameActivity.EXTRA_VG_LOCATION);
            String startTime = data.getStringExtra(AddEditVideoGameActivity.EXTRA_VG_START_TIME);
            String endTime = data.getStringExtra(AddEditVideoGameActivity.EXTRA_VG_END_TIME);

            VideoGame videoGame = new VideoGame(vgName, genre, location, startTime, endTime);
            videoGame.setVideoGameID(id);
            videoGameViewModel.update(videoGame);
            Toast.makeText(getActivity(), "VideoGame updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity(), "VideoGame Not Saved", Toast.LENGTH_SHORT).show();
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
                videoGameViewModel.deleteAllVideoGames();
                Toast.makeText(getActivity(),"All VideoGames Deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

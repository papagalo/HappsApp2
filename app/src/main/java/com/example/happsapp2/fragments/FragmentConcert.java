package com.example.happsapp2.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.happsapp2.AddEditConcertActivity;
import com.example.happsapp2.R;
import com.example.happsapp2.adapters.ConcertAdapter;
import com.example.happsapp2.models.Concert;
import com.example.happsapp2.view_models.ConcertViewModel;

import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

public class FragmentConcert extends Fragment {
    static final int ADD_NOTE_REQUEST = 1;
    static final int EDIT_NOTE_REQUEST = 2;
    private RecyclerView recyclerView;
    private ConcertViewModel concertViewModel;
    private ConcertAdapter adapter;

    public FragmentConcert() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.concert_fragment,container,false  );

        recyclerView = v.findViewById(R.id.concert_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setHasFixedSize(true);

        adapter = new ConcertAdapter();
        recyclerView.setAdapter(adapter);

        concertViewModel = ViewModelProviders.of(getActivity()).get(ConcertViewModel.class);
        concertViewModel.getAllConcerts().observe(getActivity(), new Observer<List<Concert>>() {
            @Override
            public void onChanged(@Nullable List<Concert> concerts) {
                adapter.submitList(concerts);
            }
        });

        adapter.setOnItemClickListener(new ConcertAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Concert concert) {
                Intent intent = new Intent(getActivity(), AddEditConcertActivity.class);

                intent.putExtra(AddEditConcertActivity.EXTRA_BAND_NAME, concert.getBandName());
                intent.putExtra(AddEditConcertActivity.EXTRA_GENRE, concert.getGenre());
                intent.putExtra(AddEditConcertActivity.EXTRA_LOCATION, concert.getLocation());
                intent.putExtra(AddEditConcertActivity.EXTRA_START_TIME, concert.getStartTime());
                intent.putExtra(AddEditConcertActivity.EXTRA_END_TIME, concert.getEndTime());
                intent.putExtra(AddEditConcertActivity.EXTRA_ID, concert.getConcertID());

                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String bandName = data.getStringExtra(AddEditConcertActivity.EXTRA_BAND_NAME);
            String genre = data.getStringExtra(AddEditConcertActivity.EXTRA_GENRE);
            String location = data.getStringExtra(AddEditConcertActivity.EXTRA_LOCATION);
            String startTime = data.getStringExtra(AddEditConcertActivity.EXTRA_START_TIME);
            String endTime = data.getStringExtra(AddEditConcertActivity.EXTRA_END_TIME);

            Concert concert = new Concert(bandName, genre , location, startTime, endTime);
            concertViewModel.insert(concert);

            Toast.makeText(getActivity(),"Concert Saved", Toast.LENGTH_SHORT).show();
        } else if(requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditConcertActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getActivity(), "Concert can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String bandName = data.getStringExtra(AddEditConcertActivity.EXTRA_BAND_NAME);
            String genre = data.getStringExtra(AddEditConcertActivity.EXTRA_GENRE);
            String location = data.getStringExtra(AddEditConcertActivity.EXTRA_LOCATION);
            String startTime = data.getStringExtra(AddEditConcertActivity.EXTRA_START_TIME);
            String endTime = data.getStringExtra(AddEditConcertActivity.EXTRA_END_TIME);

            Concert concert = new Concert(bandName, genre, location, startTime, endTime);
            concert.setConcertID(id);
            concertViewModel.update(concert);
            Toast.makeText(getActivity(), "Concert updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity(), "Concert Not Saved", Toast.LENGTH_SHORT).show();
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
                concertViewModel.deleteAllConcerts();
                Toast.makeText(getActivity(),"All Concerts Deleted", Toast.LENGTH_SHORT).show();
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

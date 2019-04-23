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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.happsapp2.AddConcertActivity;
import com.example.happsapp2.HomeScreenActivity;
import com.example.happsapp2.R;
import com.example.happsapp2.adapters.ConcertAdapter;
import com.example.happsapp2.adapters.ConcertRecyclerAdapter;
import com.example.happsapp2.models.Concert;
import com.example.happsapp2.view_models.ConcertViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

public class FragmentConcert extends Fragment {
    static final int ADD_NOTE_REQUEST = 1;
    private List<Concert> concertList;
    private RecyclerView recyclerView;
    private ConcertViewModel concertViewModel;
    private ConcertAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    public FragmentConcert() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.concert_fragment,container,false  );

        recyclerView = v.findViewById(R.id.concert_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setHasFixedSize(true);

        FloatingActionButton buttonAddConcert = v.findViewById(R.id.button_add_Event);
        buttonAddConcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddConcertActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

        adapter = new ConcertAdapter();
        recyclerView.setAdapter(adapter);

        concertViewModel = ViewModelProviders.of(this).get(ConcertViewModel.class);
        concertViewModel.getAllConcerts().observe(this, new Observer<List<Concert>>() {
            @Override
            public void onChanged(@Nullable List<Concert> concerts) {
                Log.d(TAG, "onChanged: FRAGMENT");
                adapter.setConcerts(concerts);
            }
        });


        return v;


        /*//recyclerView = v.findViewById(R.id.concert_recycler_view);
        ConcertRecyclerAdapter recyclerAdapter = new ConcertRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);
        //final LinearLayoutManager linearLayoutManager = new LinearLayoutManager();

        //linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView concertRecyclerView = v.findViewById(R.id.concert_recycler_view);
        //concertRecyclerView.setLayoutManager(linearLayoutManager);
        concertRecyclerView.setHasFixedSize(true);

        final ConcertRecyclerAdapter concertAdapter = new ConcertRecyclerAdapter();
        gConcertViewModel = ViewModelProviders.of(this).get(ConcertViewModel.class);
        gConcertViewModel.getAllConcerts().observe(this, new Observer<List<Concert>>() {
            @Override
            public void onChanged(@Nullable List<Concert> concerts) {
                concertAdapter.setConcerts(concerts);
            }
        });
        concertRecyclerView.setAdapter(concertAdapter);*/


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddConcertActivity.EXTRA_TITLE);
            String genre = data.getStringExtra(AddConcertActivity.EXTRA_TITLE);

            Concert concert = new Concert(title, genre,"9:00 pm", "12:00 am");
            concertViewModel.insert(concert);

            Toast.makeText(getActivity(),"Concert Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Concert Not Saved", Toast.LENGTH_SHORT).show();
        }
    }
    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        concertList = new ArrayList<>();
        concertList.add(new Concert("The Fritz", "Murphy's",
                "7:00 pm", "9:00 pm"));
    }*/
}

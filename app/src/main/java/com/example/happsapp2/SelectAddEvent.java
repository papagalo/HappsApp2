package com.example.happsapp2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.happsapp2.adapters.BoardGameAdapter;
import com.example.happsapp2.models.BoardGame;
import com.example.happsapp2.view_models.BoardGameViewModel;

import java.util.List;

import static android.content.ContentValues.TAG;

public class SelectAddEvent extends AppCompatActivity {
    static final int ADD_BG_REQUEST = 1;
    private BoardGameViewModel boardGameViewModel;
    private Button addBoardGameButton;
    private Button addConcertButton;
    private Button addVideoGameButton;
    BoardGameAdapter bgAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_selector);
        bgAdapter = new BoardGameAdapter();
        Toast.makeText(this,"BoardGame On Create", Toast.LENGTH_SHORT).show();

        //View Models?
        boardGameViewModel = ViewModelProviders.of(this).get(BoardGameViewModel.class);
        boardGameViewModel.getAllBoardGames().observe(this, new Observer<List<BoardGame>>() {
            @Override
            public void onChanged(@Nullable List<BoardGame> boardGames) {
                Log.d(TAG, "onChanged: BG ACTIVITY");
                bgAdapter.submitList(boardGames);
            }
        });
        //Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        //Set up Buttons
        addBoardGameButton = findViewById(R.id.btn_add_board_game);
        addConcertButton   = findViewById(R.id.btn_add_concert);
        addVideoGameButton = findViewById(R.id.btn_add_video_game);

        addBoardGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBGActivity();
            }
        });

        addConcertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConcertActivity();
            }
        });

        addVideoGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVGActivity();
            }
        });

    }

    public void openBGActivity() {
        Intent intent = new Intent(getApplicationContext(), AddEditBoardGameActivity.class);
        startActivity(intent);
        //startActivityForResult(intent, ADD_BG_REQUEST);
        finish();
    }

    public void openConcertActivity() {
        Intent intent = new Intent(getApplicationContext(), AddEditConcertActivity.class);
        startActivity(intent);
        finish();
    }

    public void openVGActivity() {
        Intent intent = new Intent(getApplicationContext(), AddEditVideoGameActivity.class);
        startActivity(intent);
        finish();
    }



}

package com.example.happsapp2;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.happsapp2.R;
import com.example.happsapp2.models.BoardGame;
import com.example.happsapp2.view_models.BoardGameViewModel;

public class AddEditBoardGameActivity extends AppCompatActivity {
    public static final String EXTRA_BG_NAME =
            "com.example.happsapp2.EXTRA_BG_BAND_NAME";
    public static final String EXTRA_BG_LOCATION =
            "com.example.happsapp2.EXTRA_BG_LOCATION";
    public static final String EXTRA_BG_START_TIME =
            "com.example.happsapp2.EXTRA_BG_START_TIME";
    public static final String EXTRA_BG_END_TIME =
            "com.example.happsapp2.EXTRA_BG_END_TIME";
    public static final String EXTRA_BG_ID =
            "com.example.happsapp2.EXTRA_BG_ID";

    private BoardGameViewModel boardGameViewModel;
    private EditText editTextBoardGameName;
    private EditText editTextLocation;
    private EditText editTextStartTime;
    private EditText editTextEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_board_game);

        editTextBoardGameName  = findViewById(R.id.edit_text_board_game_name);
        editTextLocation  = findViewById(R.id.edit_text_board_game_location);
        editTextStartTime = findViewById(R.id.edit_text_board_game_start_time);
        editTextEndTime   = findViewById(R.id.edit_text_board_game_end_time);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_BG_ID)) {
            setTitle("Edit BoardGame");
            editTextBoardGameName.setText(intent.getStringExtra(EXTRA_BG_NAME));
            editTextLocation.setText(intent.getStringExtra(EXTRA_BG_LOCATION));
            editTextStartTime.setText(intent.getStringExtra(EXTRA_BG_START_TIME));
            editTextEndTime.setText(intent.getStringExtra(EXTRA_BG_END_TIME));
        } else {
            setTitle("Add BoardGame");
        }
    }

    private void saveBoardGame() {
        String bgName = editTextBoardGameName.getText().toString();
        String location = editTextLocation.getText().toString();
        String startTime = editTextStartTime.getText().toString();
        String endTime = editTextEndTime.getText().toString();

        if (bgName.trim().isEmpty() || location.trim().isEmpty()
            || startTime.trim().isEmpty() || endTime.trim().isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        boardGameViewModel = ViewModelProviders.of(this).get(BoardGameViewModel.class);
        BoardGame boardGame = new BoardGame(bgName, location, startTime, endTime);
        boardGameViewModel.insert(boardGame);

        Intent data = new Intent();
        data.putExtra(EXTRA_BG_NAME, bgName);
        data.putExtra(EXTRA_BG_LOCATION, location);
        data.putExtra(EXTRA_BG_START_TIME, startTime);
        data.putExtra(EXTRA_BG_END_TIME, endTime);

        int id = getIntent().getIntExtra(EXTRA_BG_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_BG_ID, id);
        }
        Toast.makeText(this, "SAVE BG RESULT_OK", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_board_game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_board_game_menu:
                saveBoardGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

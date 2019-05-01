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

import com.example.happsapp2.models.VideoGame;
import com.example.happsapp2.view_models.VideoGameViewModel;

public class AddEditVideoGameActivity extends AppCompatActivity {
    public static final String EXTRA_VG_NAME =
            "com.example.happsapp2.EXTRA_VG_NAME";
    public static final String EXTRA_VG_GENRE =
            "com.example.happsapp2.EXTRA_VG_GENRE";
    public static final String EXTRA_VG_LOCATION =
            "com.example.happsapp2.EXTRA_VG_LOCATION";
    public static final String EXTRA_VG_START_TIME =
            "com.example.happsapp2.EXTRA_VG_START_TIME";
    public static final String EXTRA_VG_END_TIME =
            "com.example.happsapp2.EXTRA_VG_END_TIME";
    public static final String EXTRA_VG_ID =
            "com.example.happsapp2.EXTRA_VG_ID";


    private EditText editTextVideoGameName;
    private EditText editTextGenre;
    private EditText editTextLocation;
    private EditText editTextStartTime;
    private EditText editTextEndTime;
    private VideoGameViewModel videoGameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video_game);

        editTextVideoGameName  = findViewById(R.id.edit_text_video_game_name);
        editTextGenre     = findViewById(R.id.edit_text_video_game_name);
        editTextLocation  = findViewById(R.id.edit_text_video_game_name);
        editTextStartTime = findViewById(R.id.edit_text_video_game_name);
        editTextEndTime   = findViewById(R.id.edit_text_video_game_name);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_VG_ID)) {
            setTitle("Edit VideoGame Meetup");
            editTextVideoGameName.setText(intent.getStringExtra(EXTRA_VG_NAME));
            editTextGenre.setText(intent.getStringExtra(EXTRA_VG_GENRE));
            editTextLocation.setText(intent.getStringExtra(EXTRA_VG_LOCATION));
            editTextStartTime.setText(intent.getStringExtra(EXTRA_VG_START_TIME));
            editTextEndTime.setText(intent.getStringExtra(EXTRA_VG_END_TIME));
        } else {
            setTitle("Add VideoGame Meetup");
        }
    }

    private void saveVideoGame() {
        String videoGameName = editTextVideoGameName.getText().toString();
        String genre = editTextGenre.getText().toString();
        String location = editTextLocation.getText().toString();
        String startTime = editTextStartTime.getText().toString();
        String endTime = editTextEndTime.getText().toString();

        if (videoGameName.trim().isEmpty() || genre.trim().isEmpty() ||
            location.trim().isEmpty() || startTime.trim().isEmpty() ||
            endTime.trim().isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        videoGameViewModel = ViewModelProviders.of(this).get(VideoGameViewModel.class);
        VideoGame videoGame = new VideoGame(videoGameName, genre, location, startTime, endTime);
        videoGameViewModel.insert(videoGame);

        Intent data = new Intent();
        data.putExtra(EXTRA_VG_NAME, videoGameName);
        data.putExtra(EXTRA_VG_GENRE, genre);
        data.putExtra(EXTRA_VG_LOCATION, location);
        data.putExtra(EXTRA_VG_START_TIME, startTime);
        data.putExtra(EXTRA_VG_END_TIME, endTime);

        int id = getIntent().getIntExtra(EXTRA_VG_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_VG_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_video_game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_video_game_menu:
                saveVideoGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

package com.example.happsapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditConcertActivity extends AppCompatActivity {
    public static final String EXTRA_BAND_NAME =
            "com.example.happsapp2.EXTRA_BAND_NAME";
    public static final String EXTRA_GENRE =
            "com.example.happsapp2.EXTRA_GENRE";
    public static final String EXTRA_LOCATION =
            "com.example.happsapp2.EXTRA_LOCATION";
    public static final String EXTRA_START_TIME =
            "com.example.happsapp2.EXTRA_START_TIME";
    public static final String EXTRA_END_TIME =
            "com.example.happsapp2.EXTRA_END_TIME";
    public static final String EXTRA_ID =
            "com.example.happsapp2.EXTRA_ID";


    private EditText editTextBandName;
    private EditText editTextGenre;
    private EditText editTextLocation;
    private EditText editTextStartTime;
    private EditText editTextEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_concert);

        editTextBandName  = findViewById(R.id.edit_text_band_name);
        editTextGenre     = findViewById(R.id.edit_text_genre);
        editTextLocation  = findViewById(R.id.edit_text_location);
        editTextStartTime = findViewById(R.id.edit_text_start_time);
        editTextEndTime   = findViewById(R.id.edit_text_end_time);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Concert");
            editTextBandName.setText(intent.getStringExtra(EXTRA_BAND_NAME));
            editTextGenre.setText(intent.getStringExtra(EXTRA_GENRE));
            editTextLocation.setText(intent.getStringExtra(EXTRA_LOCATION));
            editTextStartTime.setText(intent.getStringExtra(EXTRA_START_TIME));
            editTextEndTime.setText(intent.getStringExtra(EXTRA_END_TIME));
        } else {
            setTitle("Add Concert");
        }
    }

    private void saveConcert() {
        String bandName = editTextBandName.getText().toString();
        String genre = editTextGenre.getText().toString();
        String location = editTextLocation.getText().toString();
        String startTime = editTextStartTime.getText().toString();
        String endTime = editTextEndTime.getText().toString();

        if (bandName.trim().isEmpty() || genre.trim().isEmpty() ||
            location.trim().isEmpty() || startTime.trim().isEmpty() ||
            endTime.trim().isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_BAND_NAME, bandName);
        data.putExtra(EXTRA_GENRE, genre);
        data.putExtra(EXTRA_LOCATION, location);
        data.putExtra(EXTRA_START_TIME, startTime);
        data.putExtra(EXTRA_END_TIME, endTime);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_event_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_concert_menu:
                saveConcert();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

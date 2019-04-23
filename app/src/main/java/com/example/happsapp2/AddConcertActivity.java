package com.example.happsapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddConcertActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE =
            "com.example.happsapp2.EXTRA_TITLE";
    public static final String EXTRA_TITLE2 =
            "com.example.happsapp2.EXTRA_TITLE2";

    private EditText editTextTitle;
    private EditText editTextGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_concert);

        editTextGenre = findViewById(R.id.edit_text_genre);
        editTextTitle = findViewById(R.id.edit_text_title);

        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        setTitle("Add Concert");
    }

    private void saveConcert() {
        String title = editTextTitle.getText().toString();
        String genre = editTextGenre.getText().toString();

        if(title.trim().isEmpty() || genre.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a Title and A Genre", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(Intent.EXTRA_TITLE, genre);
        data.putExtra(Intent.EXTRA_TITLE, title);

        setResult(RESULT_OK, data);
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_event_menu, menu);
        return true;
    }

    public boolean onCreateOptionsMenu(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_concert:
                saveConcert();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

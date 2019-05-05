package com.example.happsapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private TextView textViewUserName;
    private TextView textViewFirstName;
    private TextView textViewLastName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_activity);

        textViewFirstName = findViewById(R.id.text_view_first_name);
        textViewLastName = findViewById(R.id.text_view_last_name);
        textViewUserName = findViewById(R.id.text_view_user_name);

        Intent nav_intent = getIntent();
        textViewUserName.setText(nav_intent.getStringExtra("current_user_username"));
        textViewFirstName.setText(nav_intent.getStringExtra("current_user_firstname"));
        textViewLastName.setText(nav_intent.getStringExtra("current_user_lastname"));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
    }
}

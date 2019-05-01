package com.example.happsapp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.darwindeveloper.horizontalscrollmenulibrary.custom_views.HorizontalScrollMenuView;
import com.darwindeveloper.horizontalscrollmenulibrary.extras.MenuItem;

public class HorizontalBarActivity extends AppCompatActivity {

   /* HorizontalScrollMenuView gMenu;
    TextView gTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

        gMenu = (HorizontalScrollMenuView)findViewById(R.id.menu);
        gTextView = (TextView)findViewById(R.id.category_name_txt);

        initMenu();
    }

    private void initMenu() {
        gMenu.addItem("Music",R.drawable.ic_music_note);
        gMenu.addItem("BoardGames",R.drawable.dice);
        gMenu.addItem("VideoGames",R.drawable.ic_videogames);

        gMenu.setOnHSMenuClickListener(new HorizontalScrollMenuView.OnHSMenuClickListener() {
            @Override
            public void onHSMClick(MenuItem menuItem, int position) {
                Toast.makeText(HorizontalBarActivity.this, ""+menuItem.getText(),Toast.LENGTH_SHORT).show();
                gTextView.setText(menuItem.getText());
            }
        });
    }*/

}

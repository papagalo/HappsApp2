package com.example.happsapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.happsapp2.models.Category;
import com.example.happsapp2.persistence.CategoryDB.CategoryRepository;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "EventActivity";

    //ui components
    private EditText gEditText;
    private TextView gViewName;
    private ImageButton gBackArrow;
    private RelativeLayout gBackArrowContainer;

    //variables
    private boolean gIsNewCategory;
    private Category gInitialCategory;
    private CategoryRepository gCategoryRepository;

    private void setListeners() {
        gBackArrow.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        gEditText = findViewById(R.id.event_text);
        gViewName = findViewById(R.id.event_text_name);
        gBackArrowContainer = findViewById(R.id.back_arrow_container);
        gBackArrow = findViewById(R.id.toolbar_back_arrow);
        gCategoryRepository = new CategoryRepository(this);

        if(getIncomingIntent()) {
            //this means it is a new item (Go to Edit mode)
            setNewEventProperties();
        }
        else {
            //this means it is not a new item (View Mode)
            setEventProperties();
        }

        setListeners();

        if (getIntent().hasExtra("selected_category")) {
            Category category = getIntent().getParcelableExtra("selected_category");
            Log.d(TAG, "onCreate: " + category.toString());
        }
        
    }

    private Boolean getIncomingIntent() {
        if(getIntent().hasExtra("selected_category")) {
            gInitialCategory = getIntent().getParcelableExtra("selected_category");
            Log.d(TAG, "getIncomingIntent: " + gInitialCategory.toString());

            gIsNewCategory = false;
            return false;
        }
        gIsNewCategory = true;
        return true;
    }

    private void saveChanges() {
        if(gIsNewCategory) {
            saveNewCategory();
        }
        else {

        }
    }

    private void saveNewCategory() {
        gCategoryRepository.insertCategoryTask(gInitialCategory);
    }

    private void setEventProperties() {
        gViewName.setText(gInitialCategory.getTitle());
        gEditText.setText(gInitialCategory.getContent());
        gIsNewCategory = false;
    }

    private void setNewEventProperties() {
        gViewName.setText("Concert Name");
        gEditText.setText("Concert Name");
        gIsNewCategory = true;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.toolbar_back_arrow:{
                finish();
                break;
            }
        }
    }
}

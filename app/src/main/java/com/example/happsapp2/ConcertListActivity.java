package com.example.happsapp2;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.happsapp2.adapters.CategoryRecyclerAdapter;
import com.example.happsapp2.adapters.ConcertRecyclerAdapter;
import com.example.happsapp2.models.Category;
import com.example.happsapp2.persistence.CategoryDB.CategoryRepository;
import com.example.happsapp2.persistence.MainRepository;
import com.example.happsapp2.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class ConcertListActivity extends AppCompatActivity implements ConcertRecyclerAdapter.OnEventListener {

    private static final String TAG = "ConcertListActivity";

    // ui components
    private RecyclerView gRecyclerView;

    // vars
    private ArrayList<Category> gCategories = new ArrayList<>();
    private ConcertRecyclerAdapter gCategoryRecyclerAdapter;
    private MainRepository gMainRepository;
    //private CategoryRepository gCategoryRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        gRecyclerView = findViewById(R.id.recyclerView);
        //gCategoryRepository = new CategoryRepository(this);

        //initRecyclerView();
        //retrieveCategories();
        insertFakeCategories();

        setSupportActionBar((Toolbar)findViewById(R.id.happs_toolbar));
        setTitle("hApps");
    }

  private void retrieveCategories() {
        gMainRepository.retrieveCategoryTask().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                if(gCategories.size() > 0) {
                    gCategories.clear();
                }
                if(categories != null) {
                    gCategories.addAll(categories);
                }
                gCategoryRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }


    private void insertFakeCategories() {
        for (int i = 0; i < 100; i++) {
            Category category = new Category();
            category.setTitle("Category #" + i);
            category.setContent("content #: " + i);
            gCategories.add(category);
        }
        gCategoryRecyclerAdapter.notifyDataSetChanged();
    }

   /* private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        gRecyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        gRecyclerView.addItemDecoration(itemDecorator);
        gCategoryRecyclerAdapter = new CategoryRecyclerAdapter(gCategories, );
        gRecyclerView.setAdapter(gCategoryRecyclerAdapter);
    }*/


    @Override
    public void onEventClick(int position) {
        Log.d(TAG, "onEventClick: clicked " + position);
        Intent intent = new Intent(this, EventActivity.class);
        intent.putExtra("selected_category", gCategories.get(position));
        startActivity(intent);
    }
}



















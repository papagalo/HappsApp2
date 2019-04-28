package com.example.happsapp2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;



import com.example.happsapp2.adapters.ViewPagerAdapter;
import com.example.happsapp2.fragments.FragmentBoardGame;
import com.example.happsapp2.fragments.FragmentConcert;
import com.example.happsapp2.fragments.FragmentOutdoor;
import com.example.happsapp2.fragments.FragmentVideoGame;
import com.example.happsapp2.models.Concert;
import com.example.happsapp2.models.VideoGame;
import com.example.happsapp2.view_models.ConcertViewModel;
import com.example.happsapp2.view_models.VideoGameViewModel;

import java.util.List;


public class HomeScreenActivity extends AppCompatActivity {

    private TabLayout gTabLayout;
    private ViewPager gViewPager;
    private ViewPagerAdapter gViewPagerAdapter;
    private ConcertViewModel gConcertViewModel;
    private VideoGameViewModel gVideoGameViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gVideoGameViewModel = ViewModelProviders.of(this).get(VideoGameViewModel.class);
        gVideoGameViewModel.getAllVideoGames().observe(this, new Observer<List<VideoGame>>() {
           @Override
           public void onChanged(@Nullable List<VideoGame> videoGames) {
               Toast.makeText(HomeScreenActivity.this,"VIDEOGAMES!!",Toast.LENGTH_SHORT).show();
           }
        });


        gConcertViewModel = ViewModelProviders.of(this).get(ConcertViewModel.class);
        gConcertViewModel.getAllConcerts().observe(this, new Observer<List<Concert>>() {
            @Override
            public void onChanged(@Nullable List<Concert> concerts) {
                //Test for change in db
                Toast.makeText(HomeScreenActivity.this, "onChanged",Toast.LENGTH_SHORT).show();
                //This is where the RecyclerView will be updated
            }
        });



        gTabLayout = findViewById(R.id.tabLayout_id);
        gViewPager = findViewById(R.id.viewPager_id);
        gViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        gViewPagerAdapter.addFragment(new FragmentBoardGame(),"BoardGame");
        gViewPagerAdapter.addFragment(new FragmentConcert(),"Concert");
        gViewPagerAdapter.addFragment(new FragmentVideoGame(),"VideoGame");
        gViewPagerAdapter.addFragment(new FragmentOutdoor(),"Outdoor");

        gViewPager.setAdapter(gViewPagerAdapter);
        gTabLayout.setupWithViewPager(gViewPager);

    }
}

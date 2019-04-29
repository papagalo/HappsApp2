package com.example.happsapp2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
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

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private ConcertViewModel mConcertViewModel;
    private VideoGameViewModel mVideoGameViewModel;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        /*mVideoGameViewModel = ViewModelProviders.of(this).get(VideoGameViewModel.class);
        mVideoGameViewModel.getAllVideoGames().observe(this, new Observer<List<VideoGame>>() {
           @Override
           public void onChanged(@Nullable List<VideoGame> videoGames) {
               Toast.makeText(HomeScreenActivity.this,"VIDEOGAMES!!",Toast.LENGTH_SHORT).show();
           }
        });

        mConcertViewModel = ViewModelProviders.of(this).get(ConcertViewModel.class);
        mConcertViewModel.getAllConcerts().observe(this, new Observer<List<Concert>>() {
            @Override
            public void onChanged(@Nullable List<Concert> concerts) {
                //Test for change in db
                Toast.makeText(HomeScreenActivity.this, "onChanged",Toast.LENGTH_SHORT).show();
                //This is where the RecyclerView will be updated
            }
        });*/


        mTabLayout = findViewById(R.id.tabLayout_id2);
        mViewPager = findViewById(R.id.viewPager_id);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        mViewPagerAdapter.addFragment(new FragmentBoardGame(),"BoardGame");
        mViewPagerAdapter.addFragment(new FragmentConcert(),"Concert");
        mViewPagerAdapter.addFragment(new FragmentVideoGame(),"VideoGame");
        //mViewPagerAdapter.addFragment(new FragmentOutdoor(),"Outdoor");

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}

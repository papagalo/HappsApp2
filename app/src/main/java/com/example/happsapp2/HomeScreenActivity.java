package com.example.happsapp2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


import com.example.happsapp2.adapters.ConcertAdapter;
import com.example.happsapp2.adapters.ViewPagerAdapter;
import com.example.happsapp2.fragments.FragmentBoardGame;
import com.example.happsapp2.fragments.FragmentConcert;
import com.example.happsapp2.fragments.FragmentOutdoor;
import com.example.happsapp2.fragments.FragmentVideoGame;
import com.example.happsapp2.models.Concert;
import com.example.happsapp2.view_models.ConcertViewModel;

import java.util.List;


public class HomeScreenActivity extends AppCompatActivity {

    private TabLayout gTabLayout;
    private ViewPager gViewPager;
    private ViewPagerAdapter gViewPagerAdapter;
    private ConcertViewModel gConcertViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main_activity);

        /*RecyclerView recyclerView = findViewById(R.id.concert_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
*/
        /*ConcertAdapter adapter = new ConcertAdapter();
        recyclerView.setAdapter(adapter);*/
        //final ConcertRecyclerAdapter concertAdapter = new ConcertRecyclerAdapter();
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


        /*FragmentConcert concertFragment = new FragmentConcert();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.new_main_act, concertFragment);
        fragmentTransaction.commit();*/
    }
}

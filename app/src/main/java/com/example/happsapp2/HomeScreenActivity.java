package com.example.happsapp2;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.example.happsapp2.adapters.ViewPagerAdapter;
import com.example.happsapp2.fragments.FragmentBoardGame;
import com.example.happsapp2.fragments.FragmentConcert;
import com.example.happsapp2.fragments.FragmentVideoGame;


public class HomeScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private DrawerLayout mDrawerLayout;
    static final int ADD_BG_REQUEST = 1;

    private String userName;
    private String lastName;
    private String firstName;
    private String passWord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Intent nav_intent = getIntent();
        userName = nav_intent.getStringExtra("current_userName");
        lastName = nav_intent.getStringExtra("current_last_name");
        firstName = nav_intent.getStringExtra("current_first_name");
        passWord = nav_intent.getStringExtra("current_passWord");

        mTabLayout = findViewById(R.id.tabLayout_id2);
        mViewPager = findViewById(R.id.viewPager_id);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        mViewPagerAdapter.addFragment(new FragmentBoardGame(),"BoardGame");
        mViewPagerAdapter.addFragment(new FragmentConcert(),"Concert");
        mViewPagerAdapter.addFragment(new FragmentVideoGame(),"VideoGame");

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_home:
                /*Intent intent = new Intent(this, HomeScreenActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/
                break;
            case R.id.nav_profile:
                Intent userIntent = new Intent(this, ProfileActivity.class);
                userIntent.putExtra("current_user_username", userName);
                userIntent.putExtra("current_user_lastname", lastName);
                userIntent.putExtra("current_user_firstname", firstName);
                userIntent.putExtra("current_user_password", passWord);
                startActivity(userIntent);
                break;
            case R.id.nav_add_event:
                Intent intent = new Intent(this, SelectAddEvent.class);
                startActivity(intent);
                break;
            case R.id.nav_my_events:
                Intent myEventsIntent = new Intent(this, MyEventsActivity.class);
                startActivity(myEventsIntent);
                break;
            case R.id.nav_logout:
                Intent logout_intent = new Intent(this, LoginActivity.class);
                startActivity(logout_intent);
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
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

package com.example.taha.laws.userinterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.taha.laws.R;

public class PlantsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants);

        // pager object calling
        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.plant_pager);
        pager.setAdapter(pagerAdapter);
        // custom Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // navigation bar
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        }

    // close the navigation bar when back button pressed
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //  clicks on nav items
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;
        switch (id) {
            case R.id.nav_community:
                intent = new Intent(this, PlantsActivity.class); // change this to community activity
                startActivity(intent);
                break;
            case R.id.nav_plants:
                intent = new Intent(this, PlantsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_garden:
                intent = new Intent(this, MyGardenActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                // logout
                break;
            case R.id.nav_settings:
                intent = new Intent(this, PlantsActivity.class); // change this to Settings activity
                startActivity(intent);
                break;
            default:
                // return the current activity
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true ;
    }


    // inner class for display fragments
    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // count for displayed fragments
        @Override
        public int getCount() {
            return 1;
        }

        // for item it deal with fragments items
        @Override
        public Fragment getItem(int position) {
            return new PlantsFragment();
        }

        // for page title
        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getText(R.string.plants_title);
        }
    }
}
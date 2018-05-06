package com.example.taha.laws.userinterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
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
import com.example.taha.laws.data.Garden;
import com.example.taha.laws.data.plants;

public class MyGardenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_garden);

        // pager object calling
        MyGardenActivity.SectionsPagerAdapter pagerAdapter = new MyGardenActivity.SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager pager = findViewById(R.id.my_garden_pager);
        pager.setAdapter(pagerAdapter);

        //Attach the ViewPager to the TabLayout
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);


        // custom Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // navigation bar
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    // close the navigation bar when back button pressed
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
                intent = new Intent(this, Garden.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                // logout
                break;
            case R.id.nav_settings:
                intent = new Intent(this, plants.class); // change this to Settings activity
                startActivity(intent);
                break;
            default:
                // return the current activity
                intent = new Intent(this, this.getClass());
                startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return true ;
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // count for displayed fragments
        @Override
        public int getCount() {
            return 2;
        }

        // for item it deal with
        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0 :
                    return new PlantedFragment();

                case 1 :
                    return new HarvestedFragment();
            }

            return null ;
        }

        // for page title
        @Override
        public CharSequence getPageTitle(int position) {

         switch (position)
         {
             case 0 :
                 return getResources().getText(R.string.planted_fragment);
             case 1 :
                 return getResources().getText(R.string.harvested_fragment);
         }
           return null ;
        }
    }
}

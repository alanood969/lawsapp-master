package com.example.taha.laws.userinterface;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.taha.laws.R;

public class CommunityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // pager object calling
        CommunityActivity.SectionsPagerAdapter pagerAdapter = new CommunityActivity.SectionsPagerAdapter(getSupportFragmentManager());

        setContentView(R.layout.activity_community);


        ViewPager pager = findViewById(R.id.community_pager);
        pager.setAdapter(pagerAdapter);

        //Attach the ViewPager to the TabLayout
        TabLayout tabLayout = findViewById(R.id.tabCommunity);
        tabLayout.setupWithViewPager(pager);
    }


    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // count for displayed fragments
        @Override
        public int getCount() {
            return 4;
        }

        // for item it deal with
        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0 :
                    return new AllPostsFragment();

                case 1 :
                    return new ProblemFragment();

                case 2 :
                    return new QuestionFragment();

                case 3 :
                    return new RecommendFragment();
            }

            return null ;
        }

        // for page title
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position)
            {
                case 0 :
                return getResources().getText(R.string.allposts_fragment);
                case 1 :
                    return getResources().getText(R.string.problem_fragment);
                case 2 :
                    return getResources().getText(R.string.question_fragment);
                case 3 :
                    return getResources().getText(R.string.recommend_fragment);
            }
            return null ;
        }
    }
}

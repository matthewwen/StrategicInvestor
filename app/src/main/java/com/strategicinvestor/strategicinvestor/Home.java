package com.strategicinvestor.strategicinvestor;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.strategicinvestor.strategicinvestor.algolia.Search;
import com.strategicinvestor.strategicinvestor.fragment.FragmentPageAdapter;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager pager = findViewById(R.id.home_vp);
        FragmentPagerAdapter pagerAdapter = new FragmentPageAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        //The Tab Layout
        TabLayout tabLayout = findViewById(R.id.home_tab);
        int[] color = {getResources().getColor(R.color.home_tab_indicator_color),
                getResources().getColor(R.color.home_tab_selected_text_color),
                getResources().getColor(R.color.home_tab_not_selected_text_color)};
        tabLayout.setSelectedTabIndicatorColor(color[0]);
        tabLayout.setTabTextColors(color[2], color[1]);

        tabLayout.setupWithViewPager(pager);

        Search.algoliaQuery("Apple");

    }
}

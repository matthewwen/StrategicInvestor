package com.strategicinvestor.strategicinvestor;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.strategicinvestor.strategicinvestor.algolia.Search;
import com.strategicinvestor.strategicinvestor.fragment.FragmentPageAdapter;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    public static ArrayList<String> company_names = new ArrayList<>();
    public static ArrayList<String> company_ticks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TinyDB tinyDB = new TinyDB(Home.this);

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

        //Floating Action Button
        FloatingActionButton button = findViewById(R.id.home_fb);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, AddCompany.class);
            startActivity(intent);
        });

    }
}

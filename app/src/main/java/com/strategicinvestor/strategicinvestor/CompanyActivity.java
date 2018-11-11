package com.strategicinvestor.strategicinvestor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        LineChart chart = (LineChart) findViewById(R.id.chart);

        ArrayList<Float> prices = new ArrayList<>();

        //YourData[] dataObjects = ...;

        List<Entry> entries = new ArrayList<>();
        Collections.reverse(entries);

        for (int i = 0; i < prices.size(); i++) {

            // turn your data into Entry objects
            entries.add(new Entry(prices.get(i), (float)(i)));
        }
/*
        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        //dataSet.setColor(...);
        //dataSet.setValueTextColor(...);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
        */
    }

}

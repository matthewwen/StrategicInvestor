package com.strategicinvestor.strategicinvestor;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ResultRiskArbitrageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_risk_arbitrage);

        String tick1 = "";
        String tick2 = "";
        if (getIntent() != null){
            tick1 = getIntent().getStringExtra("TICK1");
            tick2 = getIntent().getStringExtra("TICK2");
        }

        ArrayList<Double> val1 = new ArrayList<>();
        ArrayList<Double> val2 = new ArrayList<>();

        ////////////////////////
        val1.add(1.0);
        val1.add(2.0);
        val1.add(3.0);
        val1.add(4.0);
        val1.add(5.0);
        val1.add(6.0);
        val1.add(7.0);
        val1.add(8.0);
        val1.add(9.0);
        val1.add(10.0);

        /////////////////////////
        val2.add(10.0);
        val2.add(9.0);
        val2.add(8.0);
        val2.add(7.0);
        val2.add(6.0);
        val2.add(5.0);
        val2.add(4.0);
        val2.add(3.0);
        val2.add(2.0);
        val2.add(1.0);

        /////////////////////////
        LineChart chart1 = findViewById(R.id.arbitragelinechart1);
        XAxis x1 = chart1.getXAxis();
        x1.setEnabled(false);
        YAxis y1 = chart1.getAxisLeft();
        y1.setLabelCount(6, false);
        y1.setTextColor(Color.WHITE);
        y1.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y1.setDrawGridLines(false);
        y1.setAxisLineColor(Color.WHITE);
        chart1.getAxisRight().setEnabled(false);

        List<Entry> entries1 = new ArrayList<>();
        for (int i = 0; i < val1.size(); i++) {
            // turn your data into Entry objects
            entries1.add(new Entry((float)(i), (float)val1.get(i).doubleValue()));
        }
        LineDataSet dataSet = new LineDataSet(entries1, "Stock Price"); // add entries to dataset
        dataSet.setColor(R.color.colorPrimary);
        dataSet.setCircleColor(R.color.home_tab_selected_text_color);

        LineData lineData1 = new LineData(dataSet);
        lineData1.setDrawValues(false);

        chart1.setData(lineData1);
        chart1.invalidate(); // refresh

        ///////////////////////////////////////////////////////////

        LineChart chart2 = findViewById(R.id.arbitragelinechart2);
        XAxis x2 = chart2.getXAxis();
        x2.setEnabled(false);
        YAxis y2 = chart2.getAxisLeft();
        y2.setLabelCount(6, false);
        y2.setTextColor(Color.WHITE);
        y2.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y2.setDrawGridLines(false);
        y2.setAxisLineColor(Color.WHITE);
        chart2.getAxisRight().setEnabled(false);

        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < val2.size(); i++) {
            // turn your data into Entry objects
            entries.add(new Entry((float)(i), (float)val2.get(i).doubleValue()));
        }
        LineDataSet dataSet2 = new LineDataSet(entries, "Stock Price"); // add entries to dataset
        dataSet2.setColor(R.color.colorPrimary);
        dataSet2.setCircleColor(R.color.home_tab_selected_text_color);

        LineData lineData2 = new LineData(dataSet2);
        lineData2.setDrawValues(false);

        chart2.setData(lineData2);
        chart2.invalidate(); // refresh

        ///////////////////////////////////
        TextView textView = findViewById(R.id.possibility_merge_tv);
        textView.setText(String.valueOf(15) + "%");
    }
}

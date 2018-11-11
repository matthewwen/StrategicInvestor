package com.strategicinvestor.strategicinvestor;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
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

import static com.strategicinvestor.strategicinvestor.intrinio.PriceIntrinio.fetchPrice90;

public class ResultRiskArbitrageActivity extends AppCompatActivity {

    ArrayList<Double> tick1Prices;
    ArrayList<Double> tick2Prices;

    String tick1;
    String tick2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_risk_arbitrage);

        tick1Prices = new ArrayList<>();
        tick2Prices = new ArrayList<>();
        tick1 = "";
        tick2 = "";
        if (getIntent() != null){
            tick1 = getIntent().getStringExtra("TICK1");
            tick2 = getIntent().getStringExtra("TICK2");
        }

        TextView textView = findViewById(R.id.rra_tick1);
        TextView textView1 = findViewById(R.id.rra_tick2);
        textView.setText(tick1);
        textView1.setText(tick2);

        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, Void> mAsnyc = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                tick1Prices = fetchPrice90(tick1);
                tick2Prices = fetchPrice90(tick2);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
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
                List<Entry> entries2 = new ArrayList<>();
                int i = 0;
                for(int j = tick1Prices.size() - 1; j >=0; j--) {
                    entries1.add(new Entry((float) ++i, (float)tick1Prices.get(j).doubleValue()));
                }

                LineDataSet dataSet = new LineDataSet(entries1, "Stock Price Acquirer"); // add entries to dataset
                dataSet.setColor(Color.GREEN);
                dataSet.setDrawCircles(false);
                dataSet.setCircleColor(Color.GREEN);

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

                int idx = 0;
                for(int k = tick2Prices.size() - 1; k >=0; k--) {
                    entries2.add(new Entry((float) ++idx, (float)tick2Prices.get(k).doubleValue()));
                }

                LineDataSet dataSet3 = new LineDataSet(entries2, "Stock Price Target"); // add entries to dataset
                dataSet3.setColor(Color.RED);
                dataSet3.setDrawCircles(false);
                dataSet.setCircleColor(Color.RED);

                LineData lineData3 = new LineData(dataSet3);
                lineData3.setDrawValues(false);

                chart2.setData(lineData3);
                chart2.invalidate(); // refresh

                ///////////////////////////////////
                TextView textView = findViewById(R.id.possibility_merge_tv);
                textView.setText(String.valueOf(15) + "%");
            }
        };
        mAsnyc.execute(); 


    }
}

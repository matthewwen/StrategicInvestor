package com.strategicinvestor.strategicinvestor;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

//import com.github.mikephil.charting.charts.LineChart;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.data.LineData;
//import com.github.mikephil.charting.data.LineDataSet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.strategicinvestor.strategicinvestor.intrinio.PriceIntrinio.fetchOther;
import static com.strategicinvestor.strategicinvestor.intrinio.PriceIntrinio.fetchPrice90;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class CompanyActivity extends AppCompatActivity {

    TextView tickerSym;
    TextView openPrice;
    TextView closePrice;
    TextView highPrice;
    TextView lowPrice;
    TextView volStock;
    TextView dividendStock;

    ArrayList<Double> prices;
    ArrayList<Double> otherData;

    String tick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        tick = "";
        if (getIntent() != null)
        {
            tick = getIntent().getStringExtra("TICK_VALUE");
        }

        tickerSym = findViewById(R.id.ticker_sym);
        openPrice = findViewById(R.id.open_price);
        closePrice = findViewById(R.id.close_price);
        highPrice = findViewById(R.id.high_price);
        lowPrice = findViewById(R.id.low_price);
        volStock = findViewById(R.id.volume_of_stock);
        dividendStock = findViewById(R.id.dividend_of_stock);
        tickerSym.setText(tick);

        prices = new ArrayList<>();
        otherData = new ArrayList<>();
        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, Void> mAsync = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                prices = fetchPrice90(tick);
                otherData = fetchOther(tick);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                String priceAtOpen = "$" + String.valueOf(otherData.get(0));
                String priceHigh = "$" + String.valueOf(otherData.get(1));
                String priceLow = "$" + String.valueOf(otherData.get(2));
                String priceAtClose = "$" + String.valueOf(otherData.get(3));
                openPrice.setText(priceAtOpen);
                highPrice.setText(priceHigh);
                lowPrice.setText(priceLow);
                closePrice.setText(priceAtClose);
                volStock.setText(String.valueOf(otherData.get(4)));
                dividendStock.setText(String.valueOf(otherData.get(5)));

                LineChart chart = (LineChart) findViewById(R.id.chart);
                //chart.setViewPortOffsets(0, 0, 0, 0);
                //chart.setBackgroundColor(Color.rgb(104, 241, 175));
                //chart.getDescription().setEnabled(false);
                //chart.setDrawGridBackground(false);
                //chart.setMaxHighlightDistance(300);
                //chart.getLegend().setEnabled(false);
                XAxis x = chart.getXAxis();
                x.setEnabled(false);

                YAxis y = chart.getAxisLeft();
                //y.setTypeface(tfLight);
                y.setLabelCount(6, false);
                y.setTextColor(Color.WHITE);
                y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
                y.setDrawGridLines(false);
                y.setAxisLineColor(Color.WHITE);

                chart.getAxisRight().setEnabled(false);
                chart.getAxisLeft().setEnabled(true);

                //chart.animateXY(2000, 2000);

                List<Entry> entries = new ArrayList<>();
                //for (int i = 0; i < prices.size(); i++) {
                    // turn your data into Entry objects
                    //float xVal = (float)prices.size() - i;
                //    entries.add(new Entry((float)i, (float)prices.get(i).doubleValue()));
                //}
                int i = 0;
                for(int j = prices.size() - 1; j >=0; j--) {
                    entries.add(new Entry((float) ++i, (float)prices.get(j).doubleValue()));
                }
                //Collections.reverse(entries);
                LineDataSet dataSet = new LineDataSet(entries, "Stock Price"); // add entries to dataset
                dataSet.setColor(R.color.colorPrimary);
                dataSet.setDrawCircles(false);
                dataSet.setCircleColor(R.color.home_tab_selected_text_color);
                //dataSet.setColor(...);
                //dataSet.setValueTextColor(...);

                LineData lineData = new LineData(dataSet);
                //lineData.setValueTextColor(Color.WHITE);
                lineData.setDrawValues(false);

                chart.setData(lineData);
                chart.invalidate(); // refresh
            }
        };

        mAsync.execute();
    }

}

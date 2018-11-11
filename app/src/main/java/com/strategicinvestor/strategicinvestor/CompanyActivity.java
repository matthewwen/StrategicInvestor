package com.strategicinvestor.strategicinvestor;

import android.annotation.SuppressLint;
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

//import com.github.mikephil.charting.charts.LineChart;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.data.LineData;
//import com.github.mikephil.charting.data.LineDataSet;
//
//import java.util.ArrayList;
//import java.util.List;

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
                openPrice.setText(String.valueOf(otherData.get(0)));
                highPrice.setText(String.valueOf(otherData.get(1)));
                lowPrice.setText(String.valueOf(otherData.get(2)));
                closePrice.setText(String.valueOf(otherData.get(3)));
                volStock.setText(String.valueOf(otherData.get(4)));
                dividendStock.setText(String.valueOf(otherData.get(5)));
            }
        };

        mAsync.execute();



//        LineChart chart = (LineChart) findViewById(R.id.chart);
//
//        //YourData[] dataObjects = ...;
//
//        //YourData[] dataObjects = ...;
//
//        List<Entry> entries = new ArrayList<>();
//        Collections.reverse(entries);



//        for (int i = 0; i < prices.size(); i++) {
//
//            // turn your data into Entry objects
//            entries.add(new Entry((float)prices.get(i).doubleValue(), (float)(i)));
//        }
//
//        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
//        //dataSet.setColor(...);
//        //dataSet.setValueTextColor(...);
//
//        LineData lineData = new LineData(dataSet);
//        chart.setData(lineData);
//        chart.invalidate(); // refresh

    }

}

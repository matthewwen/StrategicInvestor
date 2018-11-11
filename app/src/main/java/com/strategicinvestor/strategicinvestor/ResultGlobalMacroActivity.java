package com.strategicinvestor.strategicinvestor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ResultGlobalMacroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_global_macro);

        String tick1 = "";
        if (getIntent() != null){
            tick1 = getIntent().getStringExtra("TICK1");
        }
    }
}

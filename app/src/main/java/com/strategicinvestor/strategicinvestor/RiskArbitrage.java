package com.strategicinvestor.strategicinvestor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RiskArbitrage extends AppCompatActivity {

    public String add_company_name = "AAPL";

    public TextView company_field_1;
    public TextView company_field_2;

    boolean selected2;
    boolean selected1;

    String tick1;
    String tick2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_arbitrage);

        tick1 = "Select a Company";
        selected1 = false;
        tick2 = "Select a Company";
        selected2 = false;
        if (getIntent() != null){
            if (getIntent().getBooleanExtra("SELECT1", false)) {
                selected1 = true;
                tick1 = getIntent().getStringExtra("TICK1");
            }
            if (getIntent().getBooleanExtra("SELECT2", false)){
                selected2 = true;
                tick2 = getIntent().getStringExtra("TICK2");
            }
        }

        company_field_1 = findViewById(R.id.editText1);
        company_field_1.setText(tick1);
        company_field_2 = findViewById(R.id.editText2);
        company_field_2.setText(tick2);

        Button btnLog1 = (Button)findViewById(R.id.btnLog1);
        btnLog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentManual = new Intent(RiskArbitrage.this, SelectCompanyRiskArbitrage.class);
                intentManual.putExtra("ACQ", 1);
                intentManual.putExtra("SELECT1", selected1);
                intentManual.putExtra("SELECT2", selected2);
                if (selected1){
                    intentManual.putExtra("TICK1", tick1);
                }
                if (selected2){
                    intentManual.putExtra("TICK2", tick2);
                }
                startActivity(intentManual);
                finish();
            }
        } );

        Button btnLog2 = (Button)findViewById(R.id.btnLog2);
        btnLog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentManual = new Intent(RiskArbitrage.this, SelectCompanyRiskArbitrage.class);
                intentManual.putExtra("ACQ", 2);
                intentManual.putExtra("SELECT1", selected1);
                intentManual.putExtra("SELECT2", selected2);
                if (selected1){
                    intentManual.putExtra("TICK1", tick1);
                }
                if (selected2){
                    intentManual.putExtra("TICK2", tick2);
                }
                startActivity(intentManual);
                finish();
            }
        } );

        Button showAnaylis = findViewById(R.id.btnDone);
        showAnaylis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected1 && selected2){
                    Intent intent = new Intent(RiskArbitrage.this, ResultRiskArbitrageActivity.class);
                    intent.putExtra("TICK1", tick1);
                    intent.putExtra("TICK2", tick2);
                    startActivity(intent);
                }
            }
        });
    }


}

package com.strategicinvestor.strategicinvestor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RiskArbitrage extends AppCompatActivity {

    public String add_company_name = "AAPL";

    public static EditText company_field_1;
    public static EditText company_field_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_arbitrage);

        company_field_1 = (EditText) findViewById(R.id.editText1);
        company_field_2 = (EditText) findViewById(R.id.editText2);

        Button btnLog1 = (Button)findViewById(R.id.btnLog1);
        btnLog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentManual = new Intent(RiskArbitrage.this, SelectCompany.class);
                startActivity(intentManual);
                finish();
            }
        } );

        Button btnLog2 = (Button)findViewById(R.id.btnLog2);
        btnLog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentManual = new Intent(RiskArbitrage.this, SelectCompany.class);
                startActivity(intentManual);
                finish();
            }
        } );
    }


}

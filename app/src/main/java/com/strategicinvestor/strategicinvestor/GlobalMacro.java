package com.strategicinvestor.strategicinvestor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GlobalMacro extends AppCompatActivity {

    public TextView company_field_1;

    boolean selected1;

    String tick1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_macro);

        tick1 = "Select a Company";
        selected1 = false;
        if (getIntent() != null){
            if (getIntent().getBooleanExtra("SELECT1", false)) {
                selected1 = true;
                tick1 = getIntent().getStringExtra("TICK1");
            }
        }

        company_field_1 = findViewById(R.id.editText1GM);
        company_field_1.setText(tick1);

        Button btnLog1 = (Button)findViewById(R.id.btnLog1GM);
        btnLog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentManual = new Intent(GlobalMacro.this, SelectCompanyGlobalMacro.class);
                intentManual.putExtra("SELECT1", selected1);
                if (selected1){
                    intentManual.putExtra("TICK1", tick1);
                }
                startActivity(intentManual);
                finish();
            }
        } );

        Button showAnaylis = findViewById(R.id.btnDoneGM);
        showAnaylis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected1){
                    Intent intent = new Intent(GlobalMacro.this, ResultGlobalMacroActivity.class);
                    intent.putExtra("TICK1", tick1);
                    startActivity(intent);
                }
            }
        });
    }

}

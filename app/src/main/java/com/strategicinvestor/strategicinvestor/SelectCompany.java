package com.strategicinvestor.strategicinvestor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.strategicinvestor.strategicinvestor.adapter.SelectCompanyAdapter;
import com.strategicinvestor.strategicinvestor.fragment.CompaniesFragment;

public class SelectCompany extends AppCompatActivity implements SelectCompanyAdapter.SelectCompany{

    boolean selected2;
    boolean selected1;

    String tick1;
    String tick2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_company);

        int option = 1;
        if (getIntent() != null){
            option = getIntent().getIntExtra("ACQ", 1);
            selected1 = getIntent().getBooleanExtra("SELECT1", false);
            selected2 = getIntent().getBooleanExtra("SELECT2", false);
            tick1 = "";
            tick2 = "";
            if (selected1){
                tick1 = getIntent().getStringExtra("TICK1");
            }
            if (selected2){
                tick2 = getIntent().getStringExtra("TICK2");
            }
        }

        RecyclerView recyclerView = findViewById(R.id.select_company_rv);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        SelectCompanyAdapter adapter = new SelectCompanyAdapter(CompaniesFragment.allCompany(), this, option);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void companyTapped(String tick, int option) {
        Intent intent = new Intent(this, RiskArbitrage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("SELECT1", selected1);
        intent.putExtra("SELECT2", selected2);
        intent.putExtra("TICK1", tick1);
        intent.putExtra("TICK2", tick2);

        if (option == 1){
            intent.putExtra("TICK1", tick);
            intent.putExtra("SELECT1", true);

        }else {
            intent.putExtra("TICK2", tick);
            intent.putExtra("SELECT2", true);
        }

        startActivity(intent);

        finish();

    }
}

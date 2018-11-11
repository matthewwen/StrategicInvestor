package com.strategicinvestor.strategicinvestor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.strategicinvestor.strategicinvestor.adapter.SelectCompanyAdapter;
import com.strategicinvestor.strategicinvestor.fragment.CompaniesFragment;

public class SelectCompanyGlobalMacro extends AppCompatActivity implements SelectCompanyAdapter.SelectCompany{

    boolean selected1;

    String tick1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_company_global_macro);

        int option = 1;
        if (getIntent() != null){
            selected1 = getIntent().getBooleanExtra("SELECT1", false);
            tick1 = "";
            if (selected1){
                tick1 = getIntent().getStringExtra("TICK1");
            }

        }

        RecyclerView recyclerView = findViewById(R.id.select_company_gb_rv);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        SelectCompanyAdapter adapter = new SelectCompanyAdapter(CompaniesFragment.allCompany(), this, option);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void companyTapped(String tick, int i) {
        Intent intent = new Intent(this, GlobalMacro.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("SELECT1", true);
        intent.putExtra("TICK1", tick);

        startActivity(intent);

        finish();
    }
}

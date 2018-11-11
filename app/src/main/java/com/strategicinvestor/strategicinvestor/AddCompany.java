package com.strategicinvestor.strategicinvestor;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.strategicinvestor.strategicinvestor.adapter.SearchCompanyAdapter;
import com.strategicinvestor.strategicinvestor.algolia.Search;
import com.strategicinvestor.strategicinvestor.object.Company;

import java.util.ArrayList;

public class AddCompany extends AppCompatActivity implements Search.Listener, SearchCompanyAdapter.SavedTick{

    EditText editText;
    SearchCompanyAdapter adapter;
    String query;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);

        editText = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.search_load_rv);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new SearchCompanyAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.search:
            {
                Log.v(AddCompany.class.getSimpleName(), "Tap Here");
                Search.algoliaQuery(editText.getText().toString(), this);
            }
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public void onResultRecieve(ArrayList<Company> allCompany) {
        adapter.setAllCompany(allCompany);
    }

    @Override
    public void saveData(String tick, String name) {
        Home.company_names.add(name);
        Home.company_ticks.add(tick);
        TinyDB tinyDB = new TinyDB(AddCompany.this);
        tinyDB.putListString("theNames", Home.company_names);
        tinyDB.putListString("theTicks", Home.company_ticks);

        Log.v(AddCompany.class.getSimpleName(), "Save Data");
    }

    @Override
    public void removeData(String tick, String name) {
        Home.company_names.remove(name);
        Home.company_ticks.remove(tick);
        TinyDB tinyDB = new TinyDB(AddCompany.this);
        tinyDB.putListString("theNames", Home.company_names);
        tinyDB.putListString("theTicks", Home.company_ticks);

        Log.v(AddCompany.class.getSimpleName(), "Remove Data");
    }
}

package com.strategicinvestor.strategicinvestor.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strategicinvestor.strategicinvestor.CompanyActivity;
import com.strategicinvestor.strategicinvestor.Home;
import com.strategicinvestor.strategicinvestor.R;
import com.strategicinvestor.strategicinvestor.TinyDB;
import com.strategicinvestor.strategicinvestor.adapter.UserCompanyAdapter;
import com.strategicinvestor.strategicinvestor.intrinio.PriceIntrinio;
import com.strategicinvestor.strategicinvestor.object.Company;

import java.util.ArrayList;

import static com.strategicinvestor.strategicinvestor.intrinio.PriceIntrinio.fetchPrice90;

public class CompaniesFragment extends Fragment implements UserCompanyAdapter.UserTick{

    public static ArrayList<String> company_names = new ArrayList<>();
    public static ArrayList<String> company_ticks = new ArrayList<>();
    private RecyclerView recyclerView;

    public CompaniesFragment() {
        // Required empty public constructor
    }

    public static CompaniesFragment newInstance() {
        return new CompaniesFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_companies, container, false);

        recyclerView = view.findViewById(R.id.recycle_view_companies);
        update();

        return view;
    }

    private void update(){
        TinyDB tinyDB = new TinyDB(getContext());
        company_names = tinyDB.getListString("theNames");
        company_ticks = tinyDB.getListString("theTicks");
        final ArrayList<Company> allCompany = new ArrayList<>();
        for (int i = 0; i < company_names.size(); i++){
            Company company = new Company(company_names.get(i), company_ticks.get(i), 0, true);
            allCompany.add(company);
        }

        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 0; i < allCompany.size(); i++)
                {
                    double price = PriceIntrinio.fetchPrice1(allCompany.get(i).getTicker());
                    Log.v("Hello", "Ticker: " + allCompany.get(i).getTicker() + "," + String.valueOf(price));
                    allCompany.get(i).setPrice(price);
                    ArrayList<Double> prices90 = fetchPrice90(allCompany.get(i).getTicker());
                    if(prices90.get(0) < prices90.get(1)) {
                        allCompany.get(i).setColor(-1);
                    } else if(prices90.get(0) > prices90.get(1)) {
                        allCompany.get(i).setColor(1);
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(manager);
                final UserCompanyAdapter adapter = new UserCompanyAdapter(allCompany, CompaniesFragment.this);
                recyclerView.setAdapter(adapter);
            }
        };
        asyncTask.execute();
    }


    @Override
    public void onResume() {
        super.onResume();
        update();
    }

    @Override
    public void passTick(String tick) {
        Intent intent = new Intent(getContext(), CompanyActivity.class);
        intent.putExtra("TICK_VALUE", tick);
        startActivity(intent);
    }

    public static boolean haveTick(String tick)
    {
        for (int i = 0; i < company_ticks.size(); i++){
            if (tick.equals(company_ticks.get(i))){
                return true;
            }
        }

        return false;
    }

    public static ArrayList<Company> allCompany(){
        ArrayList<Company> arrayList = new ArrayList<>();
        for (int i = 0; i <company_ticks.size(); i++){
            Company company = new Company(company_names.get(i), company_ticks.get(i), 0.0, false);
            arrayList.add(company);
        }

        return arrayList;
    }
}

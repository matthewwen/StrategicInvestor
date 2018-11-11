package com.strategicinvestor.strategicinvestor.fragment;

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

import com.strategicinvestor.strategicinvestor.R;
import com.strategicinvestor.strategicinvestor.adapter.UserCompanyAdapter;
import com.strategicinvestor.strategicinvestor.intrinio.PriceIntrinio;
import com.strategicinvestor.strategicinvestor.object.Company;

import java.util.ArrayList;

public class CompaniesFragment extends Fragment {

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
        Company company = new Company("Apple","AAPL",  0 ,false);
        Company company1 = new Company( "Google","GOOGL", 0, false);
        Company company2 = new Company( "Tesla","TSLA", 0, false);
        final ArrayList<Company> allCompany = new ArrayList<>();
        allCompany.add(company);
        allCompany.add(company1);
        allCompany.add(company2);

        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 0; i < allCompany.size(); i++)
                {
                    double price = PriceIntrinio.fetchPrice1(allCompany.get(i).getTicker());
                    Log.v("Hello", String.valueOf(price));
                    allCompany.get(i).setPrice(price);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                RecyclerView recyclerView = view.findViewById(R.id.recycle_view_companies);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(manager);
                final UserCompanyAdapter adapter = new UserCompanyAdapter(allCompany);
                recyclerView.setAdapter(adapter);
            }
        };

        asyncTask.execute();


        return view;
    }
}

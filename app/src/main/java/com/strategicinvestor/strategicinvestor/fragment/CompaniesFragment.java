package com.strategicinvestor.strategicinvestor.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strategicinvestor.strategicinvestor.R;
import com.strategicinvestor.strategicinvestor.adapter.UserCompanyAdapter;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Company company = new Company("APPL", "Apple", 200);
        Company company1 = new Company("GOOGL", "Google", 150);
        Company company2 = new Company("TLSA", "Tesla", 20);
        ArrayList<Company> allCompany = new ArrayList<>();
        allCompany.add(company);
        allCompany.add(company1);
        allCompany.add(company2);

        UserCompanyAdapter adapter = new UserCompanyAdapter(allCompany);

        RecyclerView recyclerView = getView().findViewById(R.id.recycle_view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_companies, container, false);
    }
}

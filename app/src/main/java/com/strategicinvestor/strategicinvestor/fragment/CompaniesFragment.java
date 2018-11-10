package com.strategicinvestor.strategicinvestor.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_companies, container, false);
        Company company = new Company("APPL", "Apple", 200 ,false);
        Company company1 = new Company("GOOGL", "Google", 150, false);
        Company company2 = new Company("TLSA", "Tesla", 20, false);
        ArrayList<Company> allCompany = new ArrayList<>();
        allCompany.add(company);
        allCompany.add(company1);
        allCompany.add(company2);

        UserCompanyAdapter adapter = new UserCompanyAdapter(allCompany);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view_companies);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}

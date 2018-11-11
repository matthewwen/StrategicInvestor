package com.strategicinvestor.strategicinvestor.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strategicinvestor.strategicinvestor.R;
import com.strategicinvestor.strategicinvestor.RiskArbitrage;
import com.strategicinvestor.strategicinvestor.adapter.StrategiesAdapter;
import com.strategicinvestor.strategicinvestor.object.Company;

import java.util.ArrayList;

public class StrategiesFragment extends Fragment implements StrategiesAdapter.ChooseStratergy {

    public StrategiesFragment() {
        // Required empty public constructor
    }

    public static StrategiesFragment newInstance() {
        StrategiesFragment fragment = new StrategiesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_strategies, container, false);

        ArrayList<String> allStrategies = new ArrayList<>();
        allStrategies.add("Risk Arbitrage");
        allStrategies.add("Global Macro");
        allStrategies.add("Momentum Trading");


        StrategiesAdapter adapter = new StrategiesAdapter(allStrategies, this);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view_strategies);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void selectStrat(int pos) {
        switch (pos)
        {
            case 0: {
                Intent intent = new Intent(getContext(), RiskArbitrage.class);
                startActivity(intent);
                break;
            }
        }
    }
}

package com.strategicinvestor.strategicinvestor.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strategicinvestor.strategicinvestor.R;

public class StrategiesFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_strategies, container, false);
    }
}

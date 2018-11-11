package com.strategicinvestor.strategicinvestor.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.strategicinvestor.strategicinvestor.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PersonalFragment extends Fragment {

    public PersonalFragment() {
        // Required empty public constructor
    }

    public static PersonalFragment newInstance() {
        return new PersonalFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        capitalOneDeposit(12000);
        return view;
    }

    public void capitalOneDeposit(Integer deposit_amount) {

    }
}

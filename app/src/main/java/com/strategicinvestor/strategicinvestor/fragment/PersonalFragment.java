package com.strategicinvestor.strategicinvestor.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.strategicinvestor.strategicinvestor.Deposit;
import com.strategicinvestor.strategicinvestor.Home;
import com.strategicinvestor.strategicinvestor.R;
import com.strategicinvestor.strategicinvestor.Withdraw;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PersonalFragment extends Fragment {

    public static Integer balance = 1610;

    public static TextView balance_view;

    public PersonalFragment() {
        // Required empty public constructor
    }

    public static PersonalFragment newInstance() {
        return new PersonalFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        balance_view.setText(balance.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        balance_view = view.findViewById(R.id.textView12);
        balance_view.setText(balance.toString());

        Button btnWithdraw = view.findViewById(R.id.btnWithdraw);
        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentWithdraw = new Intent(getContext(), Withdraw.class);
                balance = Integer.parseInt(balance_view.getText().toString());
                startActivity(intentWithdraw);
            }

        } );

        Button btnDeposit = view.findViewById(R.id.btnDeposit);
        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDeposit = new Intent(getContext(), Deposit.class);
                balance = Integer.parseInt(balance_view.getText().toString());
                startActivity(intentDeposit);
            }

        } );
        return view;
    }
}

package com.strategicinvestor.strategicinvestor.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.constants.TransactionMedium;
import com.reimaginebanking.api.nessieandroidsdk.models.Deposit;
import com.reimaginebanking.api.nessieandroidsdk.models.PostResponse;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;
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
        NessieClient client = NessieClient.getInstance("d3647dccce6ddbbb8366ddbc5f747710");
        SimpleDateFormat formatL = new SimpleDateFormat("yyyy-mm-dd", Locale.US);
        String currDate = formatL.format(new Date());

        Deposit deposit = new Deposit.Builder()
                .amount(deposit_amount)
                .description("DEPOSIT")
                .transactionDate(currDate)
                .medium(TransactionMedium.BALANCE)
                .build();

        client.DEPOSIT.createDeposit("12345", deposit, new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                PostResponse<Deposit> response = (PostResponse<Deposit>) result;
                Deposit deposit = response.getObjectCreated();
                System.out.println("Deposit made");
                System.out.println(deposit.toString());
            }

            @Override
            public void onFailure(NessieError error) {
                System.out.println("Failed");
            }
        });
    }
}

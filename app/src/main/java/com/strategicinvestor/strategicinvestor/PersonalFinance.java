package com.strategicinvestor.strategicinvestor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.constants.TransactionMedium;
import com.reimaginebanking.api.nessieandroidsdk.models.Deposit;
import com.reimaginebanking.api.nessieandroidsdk.models.PostResponse;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PersonalFinance extends AppCompatActivity {
    NessieClient client = NessieClient.getInstance("d3647dccce6ddbbb8366ddbc5f747710");

    SimpleDateFormat formatL = new SimpleDateFormat("yyyy-mm-dd", Locale.US);
    public String currDate = formatL.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_finance);
        capitalOneDeposit(1200);
    }

    public void capitalOneDeposit(Integer deposit_amount) {
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

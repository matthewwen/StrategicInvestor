package com.strategicinvestor.strategicinvestor.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.strategicinvestor.strategicinvestor.R;
import com.strategicinvestor.strategicinvestor.fragment.StrategiesFragment;
import com.strategicinvestor.strategicinvestor.object.Company;

import java.util.ArrayList;

public class UserCompanyAdapter extends RecyclerView.Adapter<UserCompanyAdapter.UserCompanyViewModel> {

    private ArrayList<Company> allCompany;
    private UserTick userTick;

    public UserCompanyAdapter(ArrayList<Company> allCompany, UserTick userTick)
    {
        this.allCompany = allCompany;
        this.userTick = userTick;
    }

    @NonNull
    @Override
    public UserCompanyViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.companies_element_rv, viewGroup, false);
        return new UserCompanyViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCompanyViewModel userCompanyViewModel, int i) {
        userCompanyViewModel.tickerTV.setText(allCompany.get(i).getTicker());
        userCompanyViewModel.nameTV.setText(allCompany.get(i).getName());
        userCompanyViewModel.priceTV.setText(String.valueOf(allCompany.get(i).getPrice()));
        if(allCompany.get(i).getColor() >= 1) {
            userCompanyViewModel.priceTV.setTextColor(Color.GREEN);
        } else if(allCompany.get(i).getColor() <= 1) {
            userCompanyViewModel.priceTV.setTextColor(Color.RED);
        }
        userCompanyViewModel.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userTick.passTick(allCompany.get(i).getTicker());
            }
        });
    }

    @Override
    public int getItemCount() {
        return allCompany.size();
    }

    class UserCompanyViewModel extends RecyclerView.ViewHolder{

        TextView tickerTV;
        TextView nameTV;
        TextView priceTV;
        View view;

        UserCompanyViewModel(@NonNull View itemView) {
            super(itemView);
            tickerTV = itemView.findViewById(R.id.ce_ticker_tv);
            nameTV = itemView.findViewById(R.id.ce_name_tv);
            priceTV = itemView.findViewById(R.id.ce_price_tv);
            view = itemView;
        }
    }

    public interface UserTick{
        void passTick(String tick);
    }
}

package com.strategicinvestor.strategicinvestor.adapter;

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

    public UserCompanyAdapter(ArrayList<Company> allCompany)
    {
        this.allCompany = allCompany;
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
    }

    @Override
    public int getItemCount() {
        return allCompany.size();
    }

    class UserCompanyViewModel extends RecyclerView.ViewHolder{

        TextView tickerTV;
        TextView nameTV;
        TextView priceTV;

        UserCompanyViewModel(@NonNull View itemView) {
            super(itemView);
            tickerTV = itemView.findViewById(R.id.ce_ticker_tv);
            nameTV = itemView.findViewById(R.id.ce_name_tv);
            priceTV = itemView.findViewById(R.id.ce_price_tv);
        }
    }
}

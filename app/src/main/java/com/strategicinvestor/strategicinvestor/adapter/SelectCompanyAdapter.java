package com.strategicinvestor.strategicinvestor.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.strategicinvestor.strategicinvestor.R;
import com.strategicinvestor.strategicinvestor.object.Company;

import java.util.ArrayList;

public class SelectCompanyAdapter extends RecyclerView.Adapter<SelectCompanyAdapter.SelectViewModel>{

    private ArrayList<Company> allList;
    private SelectCompany selectCompany;
    private int option;


    public SelectCompanyAdapter(ArrayList<Company> allList, SelectCompany selectCompany, int option){
        this.allList = allList; this.selectCompany = selectCompany; this.option = option;
    }

    @NonNull
    @Override
    public SelectCompanyAdapter.SelectViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.select_company_rv, viewGroup, false);
        return new SelectViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectViewModel selectViewModel, int i) {
        selectViewModel.textView.setText(allList.get(i).getName());
        selectViewModel.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCompany.companyTapped(allList.get(i).getTicker(), option);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allList.size();
    }

    public class SelectViewModel extends RecyclerView.ViewHolder {

        TextView textView;
        View view;

        public SelectViewModel(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.sc_name_tv);
            this.view = itemView;
        }
    }

    public interface SelectCompany{
        void companyTapped(String tick, int i);
    }
}

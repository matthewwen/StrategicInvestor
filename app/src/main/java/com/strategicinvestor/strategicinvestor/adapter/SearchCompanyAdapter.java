package com.strategicinvestor.strategicinvestor.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.strategicinvestor.strategicinvestor.R;
import com.strategicinvestor.strategicinvestor.object.Company;

import java.util.ArrayList;

public class SearchCompanyAdapter extends RecyclerView.Adapter<SearchCompanyAdapter.SearchCompanyViewModel> {

    private ArrayList<Company> allCompany;

    public SearchCompanyAdapter(ArrayList<Company> allCompany)
    {
        this.allCompany = allCompany;
    }

    @NonNull
    @Override
    public SearchCompanyViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_element_rv, viewGroup, false);
        return new SearchCompanyViewModel(view);
}

    @Override
    public void onBindViewHolder(@NonNull SearchCompanyViewModel searchCompanyViewModel, int i) {
        searchCompanyViewModel.nameTV.setText(allCompany.get(i).getName());
        searchCompanyViewModel.onWaitlist.setChecked(allCompany.get(i).getWaitlist());
    }

    @Override
    public int getItemCount() {
        return allCompany.size();
    }

    class SearchCompanyViewModel extends RecyclerView.ViewHolder{

        TextView nameTV;
        CheckBox onWaitlist;

        SearchCompanyViewModel(@NonNull View itemView) {
            super(itemView);
            onWaitlist = itemView.findViewById(R.id.se_save_cb);
            nameTV = itemView.findViewById(R.id.se_name_tv);
        }
    }
}

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
    SavedTick savedTick;

    public SearchCompanyAdapter(ArrayList<Company> allCompany, SavedTick savedTick)
    {
        this.allCompany = allCompany;
        this.savedTick = savedTick;
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
        searchCompanyViewModel.onWaitlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(allCompany.get(i).getWaitlist())) {
                    allCompany.get(i).setWaitList(true);
                    savedTick.saveData(allCompany.get(i).getTicker(), allCompany.get(i).getName());
                }else{
                    allCompany.get(i).setWaitList(false);
                    savedTick.removeData(allCompany.get(i).getTicker(), allCompany.get(i).getName());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return allCompany.size();
    }

    public void setAllCompany(ArrayList<Company> allCompany)
    {
        this.allCompany = allCompany;
        notifyDataSetChanged();
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

    public interface SavedTick{
        void saveData(String tick, String name);
        void removeData(String tick, String name);
    }
}

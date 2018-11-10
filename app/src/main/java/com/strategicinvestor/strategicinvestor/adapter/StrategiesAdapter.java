package com.strategicinvestor.strategicinvestor.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.strategicinvestor.strategicinvestor.R;

import java.util.ArrayList;

public class StrategiesAdapter extends RecyclerView.Adapter<StrategiesAdapter.StrategiesViewModel> {

    private ArrayList<String> allStrategies;

    public StrategiesAdapter(ArrayList<String> allStrategies)
    {
        this.allStrategies = allStrategies;
    }

    @NonNull
    @Override
    public StrategiesViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stratergies_element_rv, viewGroup, false);
        return new StrategiesViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StrategiesViewModel strategiesViewModel, int i) {
        strategiesViewModel.strategy.setText(allStrategies.get(i));
    }

    @Override
    public int getItemCount() {
        return allStrategies.size();
    }

    class StrategiesViewModel extends RecyclerView.ViewHolder{

        TextView strategy;

        StrategiesViewModel(@NonNull View itemView) {
            super(itemView);
            strategy = itemView.findViewById(R.id.ste_name_tv);
        }
    }
}
